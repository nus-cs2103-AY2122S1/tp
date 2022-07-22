package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailureWithoutException;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteCommand.MESSAGE_DELETE_STUDENTS_FAILURE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_TENTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getAddressBookWithTypicalStudents(), new UserPrefs());

    @Test
    public void execute_outOfBoundIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(List.of(outOfBoundIndex));

        String expectedMessage = String.format(MESSAGE_DELETE_STUDENTS_FAILURE,
                List.of(outOfBoundIndex.getOneBased()));

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        List<Index> outOfBoundIndex = List.<Index>of(INDEX_SECOND, INDEX_THIRD);

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
        String expectedMessage = String.format(MESSAGE_DELETE_STUDENTS_FAILURE,
                outOfBoundIndex.stream().map(x -> x.getOneBased()).collect(Collectors.toList()));

        assertCommandFailure(deleteCommand, expectedModel, expectedMessage);
    }

    @Test
    public void execute_deleteOneStudent_success() {
        Student studentToDelete = model.getStudent(INDEX_FIRST);
        DeleteCommand deleteCommand = new DeleteCommand(List.of(INDEX_FIRST));
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENTS_SUCCESS,
                List.of(studentToDelete.getNameString()));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteValidWithInvalidStudent_failure() {
        Student firstStudent = model.getStudent(INDEX_FIRST);

        DeleteCommand deleteCommand = new DeleteCommand(List.of(INDEX_TENTH, INDEX_FIRST));
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENTS_SUCCESS,
                List.of(firstStudent.getNameString()))
                + String.format(MESSAGE_DELETE_STUDENTS_FAILURE, List.of(INDEX_TENTH.getOneBased()));

        ModelManager expectedModel = new ModelManager(getAddressBookWithTypicalStudents(), new UserPrefs());
        assertCommandFailureWithoutException(deleteCommand, expectedModel, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(List.of(INDEX_THIRD, INDEX_FIRST));
        DeleteCommand deleteSecondCommand = new DeleteCommand(List.of(INDEX_FOURTH, INDEX_THIRD));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values and order -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(List.of(INDEX_THIRD, INDEX_FIRST));

        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different students -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        //same values and different order -> returns false
        DeleteCommand secondCommandCopy = new DeleteCommand(List.of(INDEX_FIRST, INDEX_THIRD));

        assertFalse(deleteSecondCommand.equals(secondCommandCopy));
    }

    /**
     * Updates {@code model}'s filtered list to show no classes.
     */
    private void showNoClass(Model model) {
        model.updateFilteredTuitionList(p -> false);
        assertTrue(model.getFilteredTuitionList().isEmpty());
    }
}
