package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.BUY_GROCERIES;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedTaskTest {

    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_TIMESTAMP = "1999-06-23";
    private static final String INVALID_CONTACT = "@jeff";

    private static final String VALID_TITLE = BUY_GROCERIES.getTitle();
    private static final String VALID_DESCRIPTION = BUY_GROCERIES.getDescription().get();
    private static final String VALID_TIMESTAMP = BUY_GROCERIES.getTimestamp().get().toString();
    private static final String VALID_ISDONE = BUY_GROCERIES.isDone() ? "Done" : "Not Done";
    private static final List<JsonAdaptedTag> VALID_TAGS = BUY_GROCERIES.getTags().stream()
            .map(JsonAdaptedTag::new).collect(Collectors.toList());
    private static final List<JsonAdaptedContact> VALID_CONTACTS = BUY_GROCERIES.getContacts().stream()
            .map(JsonAdaptedContact::new).collect(Collectors.toList());


    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(BUY_GROCERIES);
        assertEquals(BUY_GROCERIES.getTitle(), task.toModelType().getTitle());
    }

    @Test
    public void toModelType_nulTaskTitle_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DESCRIPTION,
                VALID_TIMESTAMP, VALID_ISDONE, VALID_TAGS, VALID_CONTACTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "title");
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TITLE, VALID_DESCRIPTION, VALID_TIMESTAMP,
                        VALID_ISDONE, invalidTags, VALID_CONTACTS);
        assertThrows(IllegalValueException.class, task::toModelType);
    }

    @Test
    public void toModelType_invalidTaskContacts_throwsIllegalValueException() {
        List<JsonAdaptedContact> invalidContacts = new ArrayList<>(VALID_CONTACTS);
        invalidContacts.add(new JsonAdaptedContact(INVALID_CONTACT, false));
        JsonAdaptedTask task =
                new JsonAdaptedTask(VALID_TITLE, VALID_DESCRIPTION, VALID_TIMESTAMP,
                        VALID_ISDONE, VALID_TAGS, invalidContacts);
        assertThrows(IllegalValueException.class, task::toModelType);
    }
}
