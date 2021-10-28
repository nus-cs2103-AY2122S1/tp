package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.TUTORIAL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskType;

public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "Tutori@l";
    private static final String INVALID_TASK_DATE = "2000 12 12";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = TUTORIAL.getName().toString();
    private static final String VALID_TASK_DATE = TUTORIAL.getDate().toString();
    private static final TaskType VALID_TASK_TYPE = TaskType.DEADLINE;
    private static final Task.Priority VALID_PRIORITY = TUTORIAL.getPriority();
    private static final boolean VALID_STATUS = TUTORIAL.checkIsDone();
    private static final String VALID_DESCRIPTION = TUTORIAL.getDescription();
    private static final List<JsonAdaptedTag> VALID_TAGS = TUTORIAL.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(TUTORIAL);
        assertEquals(TUTORIAL, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_NAME, VALID_TASK_DATE, VALID_TAGS, VALID_STATUS, VALID_TASK_TYPE,
                        VALID_PRIORITY, VALID_DESCRIPTION);
        String expectedMessage = TaskName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskName_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_TASK_DATE, VALID_TAGS, VALID_STATUS, VALID_TASK_TYPE,
                VALID_PRIORITY, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, INVALID_TASK_DATE, VALID_TAGS, VALID_STATUS, VALID_TASK_TYPE,
                        VALID_PRIORITY, VALID_DESCRIPTION);
        String expectedMessage = TaskDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskDate_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_NAME, null, VALID_TAGS, VALID_STATUS, VALID_TASK_TYPE,
                VALID_PRIORITY, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskType_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_TASK_DATE, VALID_TAGS, VALID_STATUS, null,
                        VALID_PRIORITY, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_TASK_DATE, VALID_TAGS, VALID_STATUS, VALID_TASK_TYPE,
                        null, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Task.Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_TASK_DATE, invalidTags, VALID_STATUS, VALID_TASK_TYPE,
                        VALID_PRIORITY, VALID_DESCRIPTION);
        assertThrows(IllegalValueException.class, task::toModelType);
    }
}
