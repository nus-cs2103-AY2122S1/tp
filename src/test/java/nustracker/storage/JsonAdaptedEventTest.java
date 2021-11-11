package nustracker.storage;

import static nustracker.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static nustracker.testutil.Assert.assertThrows;
import static nustracker.testutil.TypicalEvents.ORIENTATION;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nustracker.commons.exceptions.IllegalValueException;
import nustracker.model.event.EventDate;
import nustracker.model.event.EventName;
import nustracker.model.event.EventTime;

class JsonAdaptedEventTest {

    private static final String INVALID_EVENTNAME = "R@chel";
    private static final String INVALID_EVENTDATE = "123-456-789";
    private static final String INVALID_EVENTTIME = "9988";
    private static final String INVALID_PARTICIPANT = "#friend";
    private static final String INVALID_BLACKLIST = "e123456";

    private static final String VALID_EVENTNAME = ORIENTATION.getName().toString();
    private static final String VALID_EVENTDATE = ORIENTATION.getDate().getEventDate();
    private static final String VALID_EVENTTIME = ORIENTATION.getTime().getEventTime();
    private static final List<JsonAdaptedParticipant> VALID_PARTICIPANTS = ORIENTATION.getParticipants().stream()
            .map(JsonAdaptedParticipant::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedParticipant> VALID_BLACKLIST = ORIENTATION.getBlacklist().stream()
            .map(JsonAdaptedParticipant::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(ORIENTATION);
        Assertions.assertEquals(ORIENTATION, event.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_EVENTNAME, VALID_EVENTDATE, VALID_EVENTTIME,
                        VALID_PARTICIPANTS, VALID_BLACKLIST);
        String expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(
                null, VALID_EVENTDATE, VALID_EVENTTIME, VALID_PARTICIPANTS, VALID_BLACKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_EVENTNAME, INVALID_EVENTDATE, VALID_EVENTTIME,
                        VALID_PARTICIPANTS, VALID_BLACKLIST);
        String expectedMessage = EventDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(
                VALID_EVENTNAME, null, VALID_EVENTTIME, VALID_PARTICIPANTS, VALID_BLACKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_EVENTNAME, VALID_EVENTDATE, INVALID_EVENTTIME,
                        VALID_PARTICIPANTS, VALID_BLACKLIST);
        String expectedMessage = EventTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(
                VALID_EVENTNAME, VALID_EVENTDATE, null, VALID_PARTICIPANTS, VALID_BLACKLIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidParticipants_throwsIllegalValueException() {
        List<JsonAdaptedParticipant> invalidParticipants = new ArrayList<>(VALID_PARTICIPANTS);
        invalidParticipants.add(new JsonAdaptedParticipant(INVALID_PARTICIPANT));
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_EVENTNAME, VALID_EVENTDATE, VALID_EVENTTIME,
                        invalidParticipants, VALID_BLACKLIST);
        assertThrows(IllegalValueException.class, event::toModelType);
    }

    @Test
    public void toModelType_invalidBlacklist_throwsIllegalValueException() {
        List<JsonAdaptedParticipant> invalidBlacklist = new ArrayList<>(VALID_PARTICIPANTS);
        invalidBlacklist.add(new JsonAdaptedParticipant(INVALID_BLACKLIST));
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_EVENTNAME, VALID_EVENTDATE, VALID_EVENTTIME,
                        VALID_PARTICIPANTS, invalidBlacklist);
        assertThrows(IllegalValueException.class, event::toModelType);
    }

}
