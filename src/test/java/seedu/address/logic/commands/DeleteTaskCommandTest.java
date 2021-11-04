package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_1;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalTasks.getTypicalTasksForModule;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.testutil.TypicalModules;

public class DeleteTaskCommandTest {
    private TeachingAssistantBuddy testBuddy = TypicalModules.getTypicalBuddy();
    private Model model = new ModelManager(testBuddy, new UserPrefs());

    @Test
    public void execute_validModuleNameAndTaskId_success() {
        TaskId validTaskIdToDelete = new TaskId(VALID_TASK_ID_0);
        ModuleName validModuleName = new ModuleName(MODULE_NAME_0);

        Task taskToDelete = getTypicalTasksForModule(MODULE_NAME_0).get(0);

        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(validTaskIdToDelete, validModuleName);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, validTaskIdToDelete);

        Model expectedModel = new ModelManager();
        for (Module module : model.getFilteredModuleList()) {
            UniqueTaskList uniqueTaskList = module.getTaskList();
            ModuleName moduleName = new ModuleName(module.getName().toString());
            Module mod = new Module(moduleName);
            mod.setTaskList(uniqueTaskList);
            expectedModel.addModule(mod);
        }
        expectedModel.getFilteredModuleList().get(0).deleteTask(taskToDelete);
        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_taskIdNotFound_throwsCommandException() {
        TaskId invalidTaskId = new TaskId("T100");
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(invalidTaskId,
                new ModuleName(MODULE_NAME_0));
        String expectedMessage = String.format(Messages.MESSAGE_TASK_NOT_FOUND, invalidTaskId);
        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteTaskCommand standardDeleteCommand = new DeleteTaskCommand(new TaskId(VALID_TASK_ID_0),
                new ModuleName(MODULE_NAME_0));
        DeleteTaskCommand differentTaskIdCommand = new DeleteTaskCommand(new TaskId(VALID_TASK_ID_1),
                new ModuleName(MODULE_NAME_0));

        //same object -> returns true
        assertTrue(standardDeleteCommand.equals(standardDeleteCommand));

        //same values -> returns true
        DeleteTaskCommand standardDeleteCommandCopy = new DeleteTaskCommand(new TaskId(VALID_TASK_ID_0),
                new ModuleName(MODULE_NAME_0));
        assertTrue(standardDeleteCommand.equals(standardDeleteCommandCopy));

        //null -> returns false
        assertFalse(standardDeleteCommand.equals(null));

        //different type -> returns false
        assertFalse(standardDeleteCommand.equals(1));

        //different taskId -> returns false
        assertFalse(standardDeleteCommand.equals(differentTaskIdCommand));
    }
}
