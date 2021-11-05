package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Student;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TypicalModules;

public class AddTaskCommandTest {
    private TeachingAssistantBuddy testBuddy = TypicalModules.getTypicalBuddy();
    private Model model = new ModelManager(testBuddy, new UserPrefs());

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, null));
    }

    @Test
    public void execute_taskAcceptedByModule_addSuccessful() {
        Task validTask = new TaskBuilder().withId("T99").withName("TaskName").build();
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        AddTaskCommand addTaskCommand = new AddTaskCommand(moduleName, validTask);

        String expectedMessage = String.format(MESSAGE_ADD_TASK_SUCCESS, validTask.getTaskId());
        Model expectedModel = new ModelManager(new TeachingAssistantBuddy(TypicalModules.getTypicalBuddy()),
                new UserPrefs());
        ObservableList<Module> expectedModuleList = expectedModel.getFilteredModuleList();
        Module expectedModule = expectedModuleList.get(0);
        UniqueTaskList expectedTaskList = expectedModule.getTaskList();
        expectedTaskList.add(validTask);
        for (Student student : expectedModule.getFilteredStudentList()) {
            student.getTaskList().add(validTask);
        }
        assertCommandSuccess(addTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        Task duplicateTask = new TaskBuilder().withId("T1").build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(moduleName, duplicateTask);

        assertThrows(CommandException.class, AddTaskCommand.MESSAGE_DUPLICATE_TASK, () ->
                addTaskCommand.execute(model));
    }

    @Test
    public void equals() {
        Task task1 = new TaskBuilder().withId("T1").build();
        Task task2 = new TaskBuilder().withId("T2").build();
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        AddTaskCommand addTask1Command = new AddTaskCommand(moduleName, task1);
        AddTaskCommand addTask2Command = new AddTaskCommand(moduleName, task2);

        // same object -> returns true
        assertTrue(addTask1Command.equals(addTask1Command));

        // same values -> returns true
        AddCommand addTask1CommandCopy = new AddTaskCommand(moduleName, task1);
        assertTrue(addTask1Command.equals(addTask1CommandCopy));

        // different types -> returns false
        assertFalse(addTask1Command.equals(1));

        // null -> returns false
        assertFalse(addTask1Command.equals(null));

        // different tasks -> returns false
        assertFalse(addTask1Command.equals(addTask2Command));
    }
}
