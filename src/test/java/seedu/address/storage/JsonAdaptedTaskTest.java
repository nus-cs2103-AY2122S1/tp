package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_TASK_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.PROJECT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Name;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskDeadline;
import seedu.address.testutil.TaskBuilder;

class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "@read books";
    private static final String INVALID_DEADLINE = "50/50/2000 26:62";

    private static final String VALID_NAME = PROJECT.getName().toString();
    private static final String VALID_DEADLINE = PROJECT.getTaskDeadline().toString();

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(PROJECT);
        assertEquals(PROJECT, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(INVALID_NAME, false, VALID_DEADLINE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, false, VALID_DEADLINE);
        String expectedMessage = String.format(MISSING_FIELD_TASK_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, false, INVALID_DEADLINE);
        String expectedMessage = TaskDeadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, false, null);
        String expectedMessage = String.format(MISSING_FIELD_TASK_FORMAT, TaskDeadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void contructor_test() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(VALID_NAME, false, VALID_DEADLINE);
        try {
            Task task = jsonAdaptedTask.toModelType();
            Task expectedTask = new TaskBuilder(PROJECT).build();
            assertEquals(expectedTask, task);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }
}
