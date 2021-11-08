package dash.logic.commands.taskcommand;

import static dash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.model.AddressBook;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserInputList;
import dash.model.UserPrefs;
import dash.model.task.Task;
import dash.model.task.TaskList;
import dash.testutil.Assert;
import dash.testutil.TaskBuilder;

public class ClearDoneTaskCommandTest {

    private TaskList taskList = new TaskList();
    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), taskList,
            new UserInputList());

    @Test
    public void execute_clearDoneTaskUnfilteredList_success() {
        Task taskToAdd = new TaskBuilder().withTaskDescription("stuff").withCompletionStatus(true).build();
        model.getTaskList().add(taskToAdd);

        ClearDoneTaskCommand clearDoneTaskCommand = new ClearDoneTaskCommand();

        String expectedMessage = ClearDoneTaskCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                new TaskList(model.getTaskList()), new UserInputList());
        expectedModel.setTaskList(new TaskList());

        assertCommandSuccess(clearDoneTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearDoneTaskUnfilteredList_throwsNullPointerException() {
        ClearDoneTaskCommand clearDoneTaskCommand = new ClearDoneTaskCommand();

        Assert.assertThrows(NullPointerException.class, () -> clearDoneTaskCommand.execute(null));
    }

    @Test
    public void equals() {
        ClearDoneTaskCommand clearDoneTaskCommand = new ClearDoneTaskCommand();

        // same object -> returns true
        assertTrue(clearDoneTaskCommand.equals(clearDoneTaskCommand));

        // different types -> returns false
        assertFalse(clearDoneTaskCommand.equals(1));

        // null -> returns false
        assertFalse(clearDoneTaskCommand.equals(null));
    }
}
