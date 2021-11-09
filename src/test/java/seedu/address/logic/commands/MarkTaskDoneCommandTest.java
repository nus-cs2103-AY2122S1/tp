package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_1;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MarkTaskDoneCommand.MESSAGE_MODULE_NOT_FOUND;
import static seedu.address.logic.commands.MarkTaskDoneCommand.MESSAGE_STUDENT_NOT_FOUND;
import static seedu.address.logic.commands.MarkTaskDoneCommand.MESSAGE_TASK_NOT_FOUND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_1;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;
import seedu.address.testutil.TypicalModules;

class MarkTaskDoneCommandTest {
    private TeachingAssistantBuddy testBuddy = TypicalModules.getTypicalBuddy();
    private Model model = new ModelManager(testBuddy, new UserPrefs());

    private final ModuleName moduleName = new ModuleName(MODULE_NAME_0);
    private final ModuleName moduleName1 = new ModuleName(MODULE_NAME_1);
    private final ModuleName notPresentModuleName = new ModuleName("CS1101S");

    private final StudentId studentId = new StudentId(VALID_STUDENT_ID_AMY);
    private final StudentId studentId1 = new StudentId(VALID_STUDENT_ID_BOB);
    private final StudentId notPresentStudentId = new StudentId("A0000000Z");

    private final TaskId taskId = new TaskId(VALID_TASK_ID_0);
    private final TaskId taskId1 = new TaskId(VALID_TASK_ID_1);
    private final TaskId notPresentTaskId = new TaskId("T101");

    @Test
    public void constructor_nullModuleName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkTaskDoneCommand(null, studentId, taskId));
    }

    @Test
    public void constructor_nullStudentId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkTaskDoneCommand(moduleName, null, taskId));
    }

    @Test
    public void constructor_nullTaskId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkTaskDoneCommand(moduleName, studentId, null));
    }

    @Test
    public void execute_taskMarkedAsDone_success() {
        ModuleName validModuleName = new ModuleName(MODULE_NAME_0);
        TaskId validTaskIdToMarkDone = new TaskId(VALID_TASK_ID_0);
        StudentId validStudentId = new StudentId(VALID_STUDENT_ID_AMY);
        TaskName validTaskName = new TaskName("Assignment 1");
        TaskDeadline validTaskDeadline = new TaskDeadline("2021-12-31");

        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(validModuleName, validStudentId,
                validTaskIdToMarkDone);

        String expectedMessage = String.format(MarkTaskDoneCommand.MESSAGE_SUCCESS, validTaskIdToMarkDone,
                validStudentId);

        Model expectedModel = new ModelManager(new TeachingAssistantBuddy(TypicalModules.getTypicalBuddy()),
                new UserPrefs());

        Task taskDone = new Task(validModuleName, validTaskIdToMarkDone, validTaskName, validTaskDeadline,
                true);

        Task taskToMark = expectedModel.getFilteredModuleList().get(0).getFilteredStudentList().get(0)
                .getTaskList().getTask(0);

        expectedModel.getFilteredModuleList().get(0).getFilteredStudentList().get(0)
                .getTaskList().setTask(taskToMark, taskDone);
        assertCommandSuccess(markTaskDoneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_moduleNotPresent_throwsCommandException() {
        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(notPresentModuleName, studentId, taskId);
        assertCommandFailure(markTaskDoneCommand, model, MESSAGE_MODULE_NOT_FOUND);
    }

    @Test
    public void execute_studentNotPresent_throwsCommandException() {
        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(moduleName, notPresentStudentId, taskId);
        assertCommandFailure(markTaskDoneCommand, model, MESSAGE_STUDENT_NOT_FOUND);
    }

    @Test
    public void execute_taskNotPresent_throwsCommandException() {
        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(moduleName, studentId, notPresentTaskId);
        assertCommandFailure(markTaskDoneCommand, model, MESSAGE_TASK_NOT_FOUND);
    }

    @Test
    public void equals() {
        MarkTaskDoneCommand markTaskDoneCommand1 = new MarkTaskDoneCommand(moduleName, studentId, taskId);

        // same object -> returns true
        assertTrue(markTaskDoneCommand1.equals(markTaskDoneCommand1));

        // same values -> returns true
        assertTrue(markTaskDoneCommand1.equals(new MarkTaskDoneCommand(moduleName, studentId, taskId)));

        // different types -> returns false
        assertFalse(markTaskDoneCommand1.equals(moduleName));

        // null -> returns false
        assertFalse(markTaskDoneCommand1.equals(null));

        // different module name -> returns false
        assertFalse(markTaskDoneCommand1.equals(new MarkTaskDoneCommand(moduleName1, studentId, taskId)));

        // different student ID -> returns false
        assertFalse(markTaskDoneCommand1.equals(new MarkTaskDoneCommand(moduleName, studentId1, taskId)));

        // different task ID -> returns false
        assertFalse(markTaskDoneCommand1.equals(new MarkTaskDoneCommand(moduleName, studentId, taskId1)));
    }
}
