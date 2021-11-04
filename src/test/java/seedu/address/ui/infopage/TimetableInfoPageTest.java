package seedu.address.ui.infopage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalClasses.addTypicalClassesToAddressBook;
import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.model.tuition.UniqueTuitionList;

class TimetableInfoPageTest {
    private static Model model = new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()),
            new UserPrefs());
    private static ObservableList<TuitionClass> tuitionClasses = UniqueTuitionList.getMostRecentTuitionClasses();

    @BeforeAll
    public static void setUp_javaFX_runtime() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(0);
        Platform.startup(() -> {
            countDownLatch.countDown();
        });
        countDownLatch.await(3, TimeUnit.SECONDS);
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
