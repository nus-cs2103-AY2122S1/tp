package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_0;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_0;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.INVALID_MODULE_NAME;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalStudents.AMY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Student;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TypicalModules;



/**
 * Contains integration tests (interaction with the Model) and unit tests for EditStudentCommand.
 */
public class EditTaskCommandTest {

    private Model model = new ModelManager(TypicalModules.getTypicalBuddy(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        Task editedTask0 = new TaskBuilder().withName(VALID_TASK_NAME_0).withId(VALID_TASK_ID_0)
                .withDeadline(VALID_TASK_DEADLINE_1).build();
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask0).build();
        EditTaskCommand editCommand = new EditTaskCommand(moduleName, descriptor);

        String expectedMessage = String.format(Messages.MESSAGE_EDIT_TASK_SUCCESS, VALID_TASK_ID_0);

        Model expectedModel = new ModelManager(new TeachingAssistantBuddy(model.getBuddy()), new UserPrefs());
        Module expectedModule = expectedModel.getFilteredModuleList().get(0);
        // edits the task list of expected module using the editedTask0
        expectedModule.getTaskList().setTask(expectedModule.getTaskList().getTask(0), editedTask0);
        // edits the task list of all students in the expected module using the editedTask0
        for (Student student : expectedModule.getStudentList()) {
            student.getTaskList().setTask(expectedModule.getTaskList().getTask(0), editedTask0);
        }
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Student editedAmy = new StudentBuilder().withName(VALID_NAME_BOB).withStudentId(VALID_STUDENT_ID_AMY)
                .withEmail(VALID_EMAIL_AMY).withTeleHandle(VALID_TELE_HANDLE_AMY).build();
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_AMY)
                .withName(VALID_NAME_BOB).build();
        EditStudentCommand editCommand = new EditStudentCommand(moduleName, descriptor);

        String expectedMessage = String.format(Messages.MESSAGE_EDIT_STUDENT_SUCCESS, VALID_STUDENT_ID_AMY);

        Model expectedModel = new ModelManager(new TeachingAssistantBuddy(model.getBuddy()), new UserPrefs());
        Module expectedModule = expectedModel.getFilteredModuleList().get(0);
        expectedModule.setStudent(expectedModule.getFilteredStudentList().get(0), editedAmy);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_AMY)
                .build();
        EditStudentCommand editCommand = new EditStudentCommand(moduleName, descriptor);

        String expectedMessage = String.format(Messages.MESSAGE_EDIT_STUDENT_SUCCESS, VALID_STUDENT_ID_AMY);

        Model expectedModel = new ModelManager(new TeachingAssistantBuddy(model.getBuddy()), new UserPrefs());
        Module expectedModule = expectedModel.getFilteredModuleList().get(0);
        expectedModule.setStudent(expectedModule.getFilteredStudentList().get(0), AMY);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_studentNotFound_failure() {
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId("A1234567A").build();
        EditStudentCommand editCommand = new EditStudentCommand(moduleName, descriptor);
        assertCommandFailure(editCommand, model, String.format(Messages.MESSAGE_STUDENT_NOT_FOUND, "A1234567A"));
    }

    @Test
    public void execute_moduleNotFound_failure() {
        ModuleName moduleName = new ModuleName(INVALID_MODULE_NAME);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_AMY)
                .build();
        EditStudentCommand editCommand = new EditStudentCommand(moduleName, descriptor);

        assertCommandFailure(editCommand, model, String.format(Messages.MESSAGE_MODULE_NAME_NOT_FOUND,
                INVALID_MODULE_NAME));
    }

    @Test
    public void equals() {
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENT_ID_AMY)
                .withName(VALID_NAME_AMY).withEmail(VALID_EMAIL_AMY).withTeleHandle(VALID_TELE_HANDLE_AMY).build();
        ModuleName moduleName = new ModuleName(MODULE_NAME_0);
        final EditStudentCommand standardCommand = new EditStudentCommand(moduleName, descriptor);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(descriptor);
        EditStudentCommand commandWithSameValues = new EditStudentCommand(moduleName, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // invalid descriptor student Id -> returns false
        EditStudentDescriptor descriptor2 = new EditStudentDescriptorBuilder().withStudentId("A1234567A")
                .withName(VALID_NAME_AMY).withEmail(VALID_EMAIL_AMY).withTeleHandle(VALID_TELE_HANDLE_AMY).build();
        assertFalse(standardCommand.equals(new EditStudentCommand(moduleName, descriptor2)));

        // different moduleName -> returns false
        assertFalse(standardCommand.equals(new EditStudentCommand(new ModuleName(INVALID_MODULE_NAME), descriptor)));
    }

}
