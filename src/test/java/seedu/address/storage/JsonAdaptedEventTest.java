package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.EVENT_STUB;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;

public class JsonAdaptedEventTest {

    private static final String INVALID_NAME = "R@ce for Hum@nity";
    private static final String INVALID_DATE = "201-9-1";
    private static final String INVALID_TIME = "999";

    private static final String VALID_NAME = "Race for Humanity";
    private static final String VALID_IS_DONE = "Completed";
    private static final String VALID_DATE = "2021-09-18";
    private static final String VALID_TIME = "2359";
    // TODO: To be updated once addParticipants are implemented
    private static final List<JsonAdaptedParticipant> VALID_PARTICIPANTS = EVENT_STUB.getParticipants().stream()
            .map(JsonAdaptedParticipant::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(EVENT_STUB);
        assertEquals(EVENT_STUB, event.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_NAME, VALID_IS_DONE, VALID_DATE, VALID_TIME, VALID_PARTICIPANTS);
        String expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(null, VALID_IS_DONE, VALID_DATE, VALID_TIME, VALID_PARTICIPANTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_IS_DONE, INVALID_DATE, VALID_TIME, VALID_PARTICIPANTS);
        String expectedMessage = EventDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_IS_DONE, null, VALID_TIME, VALID_PARTICIPANTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_IS_DONE, VALID_DATE, INVALID_TIME, VALID_PARTICIPANTS);
        String expectedMessage = EventTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_IS_DONE, VALID_DATE, null, VALID_PARTICIPANTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }
}
