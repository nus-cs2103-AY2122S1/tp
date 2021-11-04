package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Student;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalTasks;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditTaskCommand.
 */
public class EditTaskCommandTest {

    private static final String DIFFERENT_MODULE_NAME = "CS2105";

    private Model model = new ModelManager(TypicalModules.getTypicalBuddy(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        Task editedTask0 = new TaskBuilder().withName(VALID_TASK_NAME_0).withId(VALID_TASK_ID_0)
                .withDeadline(VALID_TASK_DEADLINE_1).build();
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask0).build();
        EditTaskCommand editCommand = new EditTaskCommand(moduleName, descriptor);

        String expectedMessage = String.format(Messages.MESSAGE_EDIT_TASK_SUCCESS, VALID_TASK_ID_0);

        Model expectedModel = new ModelManager(new TeachingAssistantBuddy(TypicalModules.getTypicalBuddy()),
                new UserPrefs());
        Module expectedModule = expectedModel.getFilteredModuleList().get(0);
        // edits the task list of all students in the expected module using the editedTask0
        for (Student student : expectedModule.getStudentList()) {
            student.getTaskList().setTask(expectedModule.getTaskList().getTask(0), editedTask0);
        }
        // edits the task list of expected module using the editedTask0
        expectedModule.getTaskList().setTask(expectedModule.getTaskList().getTask(0), editedTask0);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Task editedTask0 = new TaskBuilder().withName(VALID_TASK_NAME_1).withId(VALID_TASK_ID_0)
                .withDeadline(VALID_TASK_DEADLINE_0).build();
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskId(VALID_TASK_ID_0)
                .withTaskName(VALID_TASK_NAME_1).build();
        EditTaskCommand editCommand = new EditTaskCommand(moduleName, descriptor);

        String expectedMessage = String.format(Messages.MESSAGE_EDIT_TASK_SUCCESS, VALID_TASK_ID_0);

        Model expectedModel = new ModelManager(new TeachingAssistantBuddy(TypicalModules.getTypicalBuddy()),
                new UserPrefs());
        Module expectedModule = expectedModel.getFilteredModuleList().get(0);
        // edits the task list of all students in the expected module using the editedTask0
        for (Student student : expectedModule.getStudentList()) {
            student.getTaskList().setTask(expectedModule.getTaskList()
                    .asModifiableObservableList().get(0), editedTask0);
        }
        // edits the task list of expected module using the editedTask0
        expectedModule.getTaskList().setTask(expectedModule.getTaskList().getTask(0), editedTask0);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskId(VALID_TASK_ID_0)
                .build();
        EditTaskCommand editCommand = new EditTaskCommand(moduleName, descriptor);

        String expectedMessage = String.format(Messages.MESSAGE_EDIT_TASK_SUCCESS, VALID_TASK_ID_0);

        Model expectedModel = new ModelManager(new TeachingAssistantBuddy(TypicalModules.getTypicalBuddy()),
                new UserPrefs());
        Module expectedModule = expectedModel.getFilteredModuleList().get(0);
        // edits the task list of all students in the expected module
        for (Student student : expectedModule.getStudentList()) {
            student.getTaskList().setTask(expectedModule.getTaskList().getTask(0),
                    TypicalTasks.getTypicalTasksForModule(MODULE_NAME_0).get(0));
        }
        // edits the task list of expected module
        expectedModule.getTaskList().setTask(expectedModule.getTaskList().getTask(0),
                TypicalTasks.getTypicalTasksForModule(MODULE_NAME_0).get(0));
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_taskNotFound_failure() {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskId("T12345").build();
        EditTaskCommand editCommand = new EditTaskCommand(moduleName, descriptor);
        assertCommandFailure(editCommand, model, String.format(Messages.MESSAGE_TASK_NOT_FOUND, "T12345"));
    }

    @Test
    public void execute_moduleNotFound_failure() {
        ModuleName moduleName = new ModuleName(DIFFERENT_MODULE_NAME);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskId(VALID_TASK_ID_0)
                .build();
        EditTaskCommand editCommand = new EditTaskCommand(moduleName, descriptor);
        assertCommandFailure(editCommand, model, String.format(Messages.MESSAGE_MODULE_NAME_NOT_FOUND,
                DIFFERENT_MODULE_NAME));
    }

    @Test
    public void equals() {
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskId(VALID_TASK_ID_0)
                .withTaskName(VALID_TASK_NAME_0).withTaskDeadline(VALID_TASK_DEADLINE_0).build();
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        final EditTaskCommand standardCommand = new EditTaskCommand(moduleName, descriptor);

        // same values -> returns true
        EditTaskDescriptor copyDescriptor = new EditTaskDescriptor(descriptor);
        EditTaskCommand commandWithSameValues = new EditTaskCommand(moduleName, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different edit task descriptor -> return false
        EditTaskDescriptor descriptor2 = new EditTaskDescriptorBuilder().withTaskId(VALID_TASK_ID_1)
                .withTaskName(VALID_TASK_NAME_1).withTaskDeadline(VALID_TASK_DEADLINE_1).build();
        assertFalse(standardCommand.equals(new EditTaskCommand(moduleName, descriptor2)));

        // different moduleName -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(new ModuleName(DIFFERENT_MODULE_NAME), descriptor)));
    }

}
