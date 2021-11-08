package seedu.address.model.tuition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalClasses.addTypicalClassesToAddressBook;
import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.ui.ResultDisplay;
import seedu.address.ui.infopage.TimetableInfoPage;

/**
 * Test class for Timetable class.
 */
class TimetableTest {
    private static Model model = new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()),
            new UserPrefs());
    private static ObservableList<TuitionClass> tuitionClasses = UniqueTuitionList.getMostRecentTuitionClasses();
    private static TimetableStub timetable;

    @BeforeAll
    public static void setUp_classes() {
        timetable = new TimetableStub(tuitionClasses, null, null);
    }

    @Test
    void parseTime_success() {
        timetable.parseTime(tuitionClasses);
        timetable.insertSlot();
        assertEquals(9, timetable.getStart());
        assertEquals(17, timetable.getEnd());
        assertEquals((17 - 9 + 1) * 6, timetable.getTotalRows());
    }

    @Test
    public void testFontSize_success() {
        TuitionClass tuitionClass = tuitionClasses.get(2);
        String message = tuitionClass.getNameString() + " " + tuitionClass.getTimeslot();
        Label fontNegative = timetable.getLabel("fontNegative", -1, 2, tuitionClass);
        Label fontOne = timetable.getLabel("font1", 1, 2, tuitionClass);
        Label fontTwo = timetable.getLabel("font2", 2, 2, tuitionClass);
        Label fontFive = timetable.getLabel("font5", 5, 2, tuitionClass);
        ArrayList<String> notshown = timetable.getNotShown();

        //font size == 1 -> do not show details of tuition class
        assertEquals(message, notshown.get(0).toString());
        //font size == 2 -> only show tuition class name
        assertEquals(message, notshown.get(1).toString());
        //other font size -> show message (which is supposed to be the name and time of tuition class)
        assertEquals(2, notshown.size());
    }

    @Test
    public void testGetColor_success() {
        String oddColor = timetable.getColor(1);
        String evenColor = timetable.getColor(0);
        assertEquals(oddColor, Timetable.COLOR_ODD);
        assertEquals(evenColor, Timetable.COLOR_EVEN);
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

    /**
     * Stub for Timetable class.
     */
    static class TimetableStub extends Timetable {

        /**
         * Constructor of timetable.
         *
         * @param tuitionClasses    tuition classes to be shown on the timetable.
         * @param resultDisplay     display tuition classes not shown, if any.
         * @param timetableInfoPage the UI component responsible for showing the timetable.
         */
        public TimetableStub(ObservableList<TuitionClass> tuitionClasses, ResultDisplay resultDisplay,
                         TimetableInfoPage timetableInfoPage) {
            super(tuitionClasses, resultDisplay, timetableInfoPage);
        }

        /**
         * Returns null when a Label is needed as tests run in headless mode.
         * @param message message to shown on the label.
         * @return null.
         */
        @Override
        public Label produceLabel(String message) {
            return null;
        }
    }
}
