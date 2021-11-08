package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalAddressBookObjects.ALICE;
import static seedu.address.testutil.TypicalAddressBookObjects.BENSON;
import static seedu.address.testutil.TypicalAddressBookObjects.CARL;
import static seedu.address.testutil.TypicalAddressBookObjects.DANIEL;
import static seedu.address.testutil.TypicalAddressBookObjects.ELLE;
import static seedu.address.testutil.TypicalAddressBookObjects.FIONA;
import static seedu.address.testutil.TypicalAddressBookObjects.GEORGE;
import static seedu.address.testutil.TypicalAddressBookObjects.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class ClearStudentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_massDelete_success() {

        ClearStudentCommand clearCommand = new ClearStudentCommand();

        String expectedMessage = String.format(ClearStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, 7);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudent(FIONA);
        expectedModel.deleteStudent(GEORGE);
        expectedModel.deleteStudent(ALICE);
        expectedModel.deleteStudent(BENSON);
        expectedModel.deleteStudent(CARL);
        expectedModel.deleteStudent(DANIEL);
        expectedModel.deleteStudent(ELLE);

        assertCommandSuccess(clearCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        ClearStudentCommand clearCommand = new ClearStudentCommand();

        String expectedMessage = String.format(ClearStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, 1);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(clearCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredStudentList(p -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
    }
}
