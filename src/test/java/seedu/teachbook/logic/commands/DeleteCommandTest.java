package seedu.teachbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.teachbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.teachbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.teachbook.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.teachbook.testutil.TypicalIndices.INDEX_FIRST_PERSON;
import static seedu.teachbook.testutil.TypicalIndices.INDEX_SECOND_PERSON;
import static seedu.teachbook.testutil.TypicalStudents.getTypicalTeachBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.teachbook.commons.core.Messages;
import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.ModelManager;
import seedu.teachbook.model.UserPrefs;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalTeachBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        DeleteCommand deleteCommand = new DeleteCommand(List.of(INDEX_FIRST_PERSON));

        ModelManager expectedModel = new ModelManager(model.getTeachBook(), new UserPrefs());
        Student studentToDelete = expectedModel.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());

        expectedModel.deleteStudent(studentToDelete);
        expectedModel.commitTeachBook();

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, true, false);

        assertCommandSuccess(deleteCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(List.of(outOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        DeleteCommand deleteCommand = new DeleteCommand(List.of(INDEX_FIRST_PERSON));

        Model expectedModel = new ModelManager(model.getTeachBook(), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_FIRST_PERSON); // add a predicate for versioned teachbook
        Student studentToDelete = expectedModel.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        expectedModel.deleteStudent(studentToDelete);
        expectedModel.commitTeachBook();
        showNoStudent(expectedModel); // empty filtered student list

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, false, true, false);

        assertCommandSuccess(deleteCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of the student list of the currently selected class
        Class currentlySelectedClass = model.getTeachBook().getClassList()
                .get(model.getCurrentlySelectedClassIndex().getZeroBased());
        assertTrue(outOfBoundIndex.getZeroBased() < currentlySelectedClass.getClassSize());

        DeleteCommand deleteCommand = new DeleteCommand(List.of(outOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(List.of(INDEX_FIRST_PERSON));
        DeleteCommand deleteSecondCommand = new DeleteCommand(List.of(INDEX_SECOND_PERSON));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(List.of(INDEX_FIRST_PERSON));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStudent(Model model) {
        model.updateFilteredStudentList(p -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
    }
}
