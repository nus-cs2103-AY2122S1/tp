package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.SEW;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Date;
import seedu.address.model.Label;
import seedu.address.model.tag.TaskTag;


public class JsonAdaptedTaskTest {
    private static final String INVALID_LABEL = "TAS$%$#";
    private static final String INVALID_DATE = "28@nextmonth";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_IS_DONE = "true and false";


    private static final String VALID_LABEL = SEW.getLabel().toString();
    private static final String VALID_DATE = SEW.getDate().toString();
    private static final String VALID_TAG = SEW.getTaskTag().toString();
    private static final String VALID_IS_DONE = Boolean.toString(SEW.getIsDone());

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(SEW);
        assertEquals(SEW, task.toModelType());
    }

    @Test
    public void toModelType_invalidLabel_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(INVALID_LABEL, VALID_DATE, VALID_TAG, VALID_IS_DONE);
        String expectedMessage = Label.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullLabel_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(null, VALID_DATE, VALID_TAG, VALID_IS_DONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Label.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_LABEL, INVALID_DATE, VALID_TAG, VALID_IS_DONE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_LABEL, null, VALID_TAG, INVALID_IS_DONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskTagIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_LABEL, VALID_DATE, INVALID_TAG, VALID_IS_DONE);
        String expectedMessage = TaskTag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullTaskTag_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_LABEL, VALID_DATE, null, VALID_IS_DONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskTag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidIsDone_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_LABEL, VALID_DATE, VALID_TAG, INVALID_IS_DONE);
        String expectedMessage = Label.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullIsDone_throwsIllegalValueException() {
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_LABEL, VALID_DATE, VALID_TAG, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "IsDone");
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }
}
