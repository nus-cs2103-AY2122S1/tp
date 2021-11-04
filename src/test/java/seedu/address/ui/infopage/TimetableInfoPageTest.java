package seedu.address.ui.infopage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalClasses.addTypicalClassesToAddressBook;
import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tuition.TuitionClass;
import seedu.address.model.tuition.UniqueTuitionList;

class TimetableInfoPageTest extends JavafxTest {
    private static Model model = new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()),
            new UserPrefs());
    private static ObservableList<TuitionClass> tuitionClasses = UniqueTuitionList.getMostRecentTuitionClasses();
    private static TimetableInfoPage timetableInfoPage = new TimetableInfoPage(tuitionClasses, null);

    @Test
    void testSetTable_success() {
        //check timetable has correct number of rows
        int rows = timetableInfoPage.getNumRows();
        assertEquals(59, rows);
        //check timetable has correct number of columns
        int cols = timetableInfoPage.getNumCols();
        assertEquals(8, cols);
    }
}
