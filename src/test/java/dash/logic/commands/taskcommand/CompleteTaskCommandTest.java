package dash.logic.commands.taskcommand;

import static dash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.logic.commands.exceptions.CommandException;
import dash.model.AddressBook;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserInputList;
import dash.model.UserPrefs;
import dash.model.task.Task;
import dash.model.task.TaskList;
import dash.testutil.Assert;
import dash.testutil.TaskBuilder;
import dash.testutil.TypicalIndexes;

public class CompleteTaskCommandTest {

    private TaskList taskList = new TaskList();
    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), taskList,
            new UserInputList());

    @Test
    public void constructor_nullCompleteTask_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new CompleteTaskCommand(null));
    }

    @Test
    public void execute_completeTaskUnfilteredList_success() {
        Task taskToAdd = new TaskBuilder().withTaskDescription("stuff").withCompletionStatus(false).build();
        model.getTaskList().add(taskToAdd);

        Task task = model.getTaskList().getObservableTaskList().get(0);
        Task editedTask = new TaskBuilder(task).withCompletionStatus(true).build();
        CompleteTaskCommand completeTaskCommand = new CompleteTaskCommand(TypicalIndexes.INDEX_FIRST);

        String expectedMessage = String.format(CompleteTaskCommand.MESSAGE_COMPLETE_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                new TaskList(model.getTaskList()), new UserInputList());
        expectedModel.setTask(0, editedTask);

        assertCommandSuccess(completeTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_completeTaskUnfilteredList_throwsCommandException() {
        Task taskToAdd = new TaskBuilder().withTaskDescription("stuff").withCompletionStatus(false).build();
        model.getTaskList().add(taskToAdd);

        CompleteTaskCommand completeTaskCommand = new CompleteTaskCommand(TypicalIndexes.INDEX_THIRD);

        Assert.assertThrows(CommandException.class, () -> completeTaskCommand.execute(model));
    }

    @Test
    public void equals() {
        CompleteTaskCommand completeFirstCommand = new CompleteTaskCommand(TypicalIndexes.INDEX_FIRST);
        CompleteTaskCommand completeSecondCommand = new CompleteTaskCommand(TypicalIndexes.INDEX_SECOND);

        // same object -> returns true
        assertTrue(completeFirstCommand.equals(completeFirstCommand));

        // same values -> returns true
        CompleteTaskCommand tagFirstCommandCopy = new CompleteTaskCommand(TypicalIndexes.INDEX_FIRST);
        assertTrue(completeFirstCommand.equals(tagFirstCommandCopy));

        // different types -> returns false
        assertFalse(completeSecondCommand.equals(1));

        // null -> returns false
        assertFalse(completeFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(completeFirstCommand.equals(completeSecondCommand));
    }
}
