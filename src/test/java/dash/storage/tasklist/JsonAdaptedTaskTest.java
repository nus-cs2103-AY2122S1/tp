package dash.storage.tasklist;

import static dash.storage.tasklist.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import dash.commons.exceptions.IllegalValueException;
import dash.model.task.TaskDescription;
import dash.storage.JsonAdaptedTag;
import dash.testutil.Assert;
import dash.testutil.TypicalTasks;

class JsonAdaptedTaskTest {

    private static final String INVALID_TASKDESCRIPTION = "";
    private static final String INVALID_TAG = "#lecture";

    private static final String VALID_TASKDESCRIPTION = "Watch ST2334 Lecture 9";
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalTasks.ASSIGNMENT.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validTaskDetails_returnTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(TypicalTasks.ASSIGNMENT);
        assertEquals(TypicalTasks.ASSIGNMENT, task.toModelType());
    }

    @Test
    public void toModelType_invalidTaskDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(INVALID_TASKDESCRIPTION, VALID_TAGS);
        String expectedMessage = TaskDescription.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_TAGS);
        String expectedMessage = MISSING_FIELD_MESSAGE_FORMAT;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task = new JsonAdaptedTask(VALID_TASKDESCRIPTION, invalidTags);
        Assert.assertThrows(IllegalValueException.class, task::toModelType);
    }

}
