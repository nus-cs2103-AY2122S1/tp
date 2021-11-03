package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedTask.TASK_MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TASK1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleName;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;

public class JsonAdaptedTaskTest {
    private static final String INVALID_MODULE_NAME = "1000";
    private static final String INVALID_TASK_ID = "1";
    private static final String INVALID_TASK_NAME = "@ssignment 1";
    private static final String INVALID_TASK_DEADLINE = "date";

    private static final String VALID_MODULE_NAME = TASK1.getModuleNameString();
    private static final String VALID_TASK_ID = TASK1.getTaskId().value;
    private static final String VALID_TASK_NAME = TASK1.getTaskName().taskName;
    private static final String VALID_TASK_DEADLINE = TASK1.getTaskDeadline().value;

    @Test
    public void toModelType_invalidModuleName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_MODULE_NAME, VALID_TASK_ID, VALID_TASK_NAME, VALID_TASK_DEADLINE, false);
        String expectedMessage = ModuleName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullModuleName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(null, VALID_TASK_ID, VALID_TASK_NAME, VALID_TASK_DEADLINE, false);
        String expectedMessage = String.format(TASK_MISSING_FIELD_MESSAGE_FORMAT, ModuleName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskId_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_MODULE_NAME, INVALID_TASK_ID, VALID_TASK_NAME, VALID_TASK_DEADLINE, false);
        String expectedMessage = TaskId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskId_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_MODULE_NAME, null, VALID_TASK_NAME, VALID_TASK_DEADLINE, false);
        String expectedMessage = String.format(TASK_MISSING_FIELD_MESSAGE_FORMAT, TaskId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_MODULE_NAME, VALID_TASK_ID, INVALID_TASK_NAME, VALID_TASK_DEADLINE, false);
        String expectedMessage = TaskName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_MODULE_NAME, VALID_TASK_ID, null, VALID_TASK_DEADLINE, false);
        String expectedMessage = String.format(TASK_MISSING_FIELD_MESSAGE_FORMAT, TaskName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_MODULE_NAME, VALID_TASK_ID, VALID_TASK_NAME, INVALID_TASK_DEADLINE, false);
        String expectedMessage = TaskDeadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_MODULE_NAME, VALID_TASK_ID, VALID_TASK_NAME, null, false);
        String expectedMessage = String.format(TASK_MISSING_FIELD_MESSAGE_FORMAT, TaskDeadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
