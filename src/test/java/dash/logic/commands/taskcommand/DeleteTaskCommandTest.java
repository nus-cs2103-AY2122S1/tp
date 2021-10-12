package dash.logic.commands.taskcommand;

import static dash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.commons.core.Messages;
import dash.commons.core.index.Index;
import dash.logic.commands.CommandTestUtil;
import dash.model.AddressBook;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserPrefs;
import dash.model.task.Task;
import dash.testutil.TypicalIndexes;
import dash.testutil.TypicalTasks;

class DeleteTaskCommandTest {

    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), TypicalTasks.getTypicalTaskList());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToDelete = model.getFilteredTaskList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(TypicalIndexes.INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), model.getTaskList());
        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteTaskCommand, model,
                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showTaskAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Task taskToDelete = model.getFilteredTaskList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(TypicalIndexes.INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), model.getTaskList());
        expectedModel.deleteTask(taskToDelete);
        showNoTask(expectedModel);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showTaskAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getObservableTaskList().size());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteTaskCommand, model,
                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstCommand = new DeleteTaskCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        DeleteTaskCommand deleteSecondCommand = new DeleteTaskCommand(TypicalIndexes.INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTaskCommand deleteFirstCommandCopy = new DeleteTaskCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no tasks.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
