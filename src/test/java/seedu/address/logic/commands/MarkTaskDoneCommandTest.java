package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBookWithTasks;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkTaskDoneCommand}.
 */
public class MarkTaskDoneCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithTasks(), new UserPrefs());

    // Pass
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToMarkAsDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(MarkTaskDoneCommand.MESSAGE_MARK_TASK_DONE_SUCCESS, taskToMarkAsDone);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.completeTask(taskToMarkAsDone);

        assertCommandSuccess(markTaskDoneCommand, model, expectedMessage, expectedModel);
    }

    // Pass
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(outOfBoundIndex);

        assertCommandFailure(markTaskDoneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    // Pass
    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToMarkAsDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(MarkTaskDoneCommand.MESSAGE_MARK_TASK_DONE_SUCCESS, taskToMarkAsDone);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.completeTask(taskToMarkAsDone);
        showNoTask(expectedModel);

        assertCommandSuccess(markTaskDoneCommand, model, expectedMessage, expectedModel);
    }

    // Pass
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(outOfBoundIndex);

        assertCommandFailure(markTaskDoneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    // Pass
    @Test
    public void equals() {
        MarkTaskDoneCommand markFirstTaskDoneCommand = new MarkTaskDoneCommand(INDEX_FIRST_TASK);
        MarkTaskDoneCommand markSecondTaskDoneCommand = new MarkTaskDoneCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(markFirstTaskDoneCommand.equals(markFirstTaskDoneCommand));

        // same values -> returns true
        MarkTaskDoneCommand markFirstTaskDoneCommandCopy = new MarkTaskDoneCommand(INDEX_FIRST_TASK);
        assertTrue(markFirstTaskDoneCommand.equals(markFirstTaskDoneCommandCopy));

        // different types -> returns false
        assertFalse(markFirstTaskDoneCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstTaskDoneCommand.equals(null));

        // different task -> returns false
        assertFalse(markFirstTaskDoneCommand.equals(markSecondTaskDoneCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
