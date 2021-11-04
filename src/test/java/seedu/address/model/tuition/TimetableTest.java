package seedu.address.model.tuition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalClasses.addTypicalClassesToAddressBook;
import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.ui.infopage.TimetableInfoPage;

class TimetableTest {
    private static Model model = new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()),
            new UserPrefs());
    private static ObservableList<TuitionClass> tuitionClasses = UniqueTuitionList.getMostRecentTuitionClasses();
    private static TimetableInfoPage infoPage;
    private static Timetable timetable;

    @BeforeAll
    public static void setUp_javaFX_runtime() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(0);
        Platform.startup(() -> {
            countDownLatch.countDown();
        });
        countDownLatch.await(3, TimeUnit.SECONDS);
        infoPage = new TimetableInfoPage(tuitionClasses, null);
        timetable = new Timetable(tuitionClasses, null, infoPage);
    }

    @Test
    void parseTime_success() {
        timetable.parseTime(tuitionClasses);
        assertEquals(9, timetable.getStart());
        assertEquals(17, timetable.getEnd());
        assertEquals((17 - 9 + 1) * 6, timetable.getTotalRows());
    }

    @Test
    public void testFontSize_success() {
        TuitionClass tuitionClass = tuitionClasses.get(2);
        Label fontNegative = timetable.getLabel("fontNegative", -1, 2, tuitionClass);
        Label fontOne = timetable.getLabel("font1", 1, 2, tuitionClass);
        Label fontTwo = timetable.getLabel("font2", 2, 2, tuitionClass);
        Label fontFive = timetable.getLabel("font5", 5, 2, tuitionClass);

        //font size == 1 -> do not show details of tuition class
        assertEquals("", fontOne.getText());
        //font size == 2 -> only show tuition class name
        assertEquals(tuitionClass.getNameString(), fontTwo.getText());
        //other font size -> show message (which is supposed to be the name and time of tuition class)
        assertEquals("font5", fontFive.getText());
        assertEquals("fontNegative", fontNegative.getText());
    }

    @Test
    public void testGetFontSize_success() {
        //span < 3 -> font size = 1
        assertEquals(1, timetable.getFontSize(-5));
        assertEquals(1, timetable.getFontSize(2));
        //span <= 4 -> font size = 2
        assertEquals(2, timetable.getFontSize(3));
        assertEquals(2, timetable.getFontSize(4));
        //span < 6 -> font size = 6
        assertEquals(6, timetable.getFontSize(5));
        //span < 9 -> font size = 7
        assertEquals(7, timetable.getFontSize(6));
        //span >= 9 -> default font size = 8
        assertEquals(8, timetable.getFontSize(9));
    }

    @Test
    public void testTimetable_insertSlot_success() {
        timetable.parseTime(tuitionClasses);
        timetable.insertSlot();
        Node node = timetable.getFirstLabel();
        timetable.showTimetable();
        assertEquals("Math\n11:00-14:00", ((Label) node).getText());
    }

    @Test
    void testSetTable_success() {
        TimetableInfoPage timetableInfoPage = new TimetableInfoPage(tuitionClasses, null);

        //check timetable has correct number of rows
        int rows = timetableInfoPage.getNumRows();
        assertEquals(59, rows);
        //check timetable has correct number of columns
        int cols = timetableInfoPage.getNumCols();
        assertEquals(8, cols);
    }
}
