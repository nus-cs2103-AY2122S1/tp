package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.participant.Participant;
import seedu.address.model.participant.ParticipantId;

public class JsonAdaptedEventTest {

    private static final String INVALID_NAME = "R@ce for Hum@nity";
    private static final String INVALID_DATE = "201-13-1";
    private static final String INVALID_TIME = "999";

    private static final String VALID_NAME = "Race for Humanity";
    private static final String VALID_IS_DONE = "Completed";
    private static final String VALID_DATE = "2021-09-18";
    private static final String VALID_TIME = "2359";
    // TODO: To be updated once addParticipants are implemented
    private static final List<String> VALID_PARTICIPANT_IDS = SAMPLE_EVENT.getParticipants().stream()
            .map(Participant::getParticipantId).map(ParticipantId::toString)
            .collect(Collectors.toList());
    private static final List<Participant> participants = List.of();

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(SAMPLE_EVENT);
        assertEquals(SAMPLE_EVENT, event.toModelType(participants));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_NAME, VALID_IS_DONE, VALID_DATE, VALID_TIME, VALID_PARTICIPANT_IDS);
        String expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> event.toModelType(participants));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(null, VALID_IS_DONE, VALID_DATE, VALID_TIME, VALID_PARTICIPANT_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> event.toModelType(participants));
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_IS_DONE, INVALID_DATE, VALID_TIME, VALID_PARTICIPANT_IDS);
        String expectedMessage = EventDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> event.toModelType(participants));
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_IS_DONE, null, VALID_TIME, VALID_PARTICIPANT_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> event.toModelType(participants));
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_IS_DONE, VALID_DATE, INVALID_TIME, VALID_PARTICIPANT_IDS);
        String expectedMessage = EventTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> event.toModelType(participants));
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, VALID_IS_DONE, VALID_DATE, null, VALID_PARTICIPANT_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> event.toModelType(participants));
    }
}
