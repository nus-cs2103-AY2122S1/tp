package seedu.unify.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.unify.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.unify.testutil.Assert.assertThrows;
import static seedu.unify.testutil.TypicalTasks.MATH_ASSIGNMENT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.unify.commons.exceptions.IllegalValueException;
import seedu.unify.model.task.Date;
import seedu.unify.model.task.Name;
import seedu.unify.model.task.Time;

public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TIME = "+23:11";
    private static final String INVALID_DATE = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_STATE = "FINISHED";
    private static final String INVALID_PRIORITY = "urgent";

    private static final String VALID_NAME = MATH_ASSIGNMENT.getName().toString();
    private static final String VALID_TIME = MATH_ASSIGNMENT.getTime().toString();
    private static final String VALID_DATE = MATH_ASSIGNMENT.getDate().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = MATH_ASSIGNMENT.getTags()
            .stream().map(JsonAdaptedTag::new).collect(Collectors.toList());
    private static final String VALID_STATE = MATH_ASSIGNMENT.getState().toString();
    private static final String VALID_PRIORITY = MATH_ASSIGNMENT.getPriority().toString();

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(MATH_ASSIGNMENT);
        assertEquals(MATH_ASSIGNMENT, task.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_NAME, VALID_TIME, VALID_DATE, VALID_STATE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(null, VALID_TIME, VALID_DATE, VALID_STATE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, INVALID_TIME, VALID_DATE, VALID_STATE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, null, VALID_DATE, VALID_STATE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_TIME, INVALID_DATE, VALID_STATE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_TIME, null, VALID_STATE, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_TIME, VALID_DATE, VALID_STATE, VALID_PRIORITY, invalidTags);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

    @Test
    public void toModelType_invalidState_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_TIME, VALID_DATE, INVALID_STATE, VALID_PRIORITY, VALID_TAGS);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

    @Test
    public void toModelType_invalidPRiority_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_NAME, VALID_TIME, VALID_DATE, INVALID_STATE, INVALID_PRIORITY, VALID_TAGS);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

}
