package dash.logic.commands.taskcommand;

import static dash.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import dash.model.AddressBook;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserPrefs;
import dash.model.task.TaskList;
import dash.testutil.TypicalTasks;

class ClearTaskCommandTest {

    @Test
    public void execute_emptyTaskList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearTaskCommand(), model, ClearTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTaskList_success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), TypicalTasks.getTypicalTaskList());
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), TypicalTasks.getTypicalTaskList());
        expectedModel.setTaskList(new TaskList());

        assertCommandSuccess(new ClearTaskCommand(), model, ClearTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
