package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_EVENT_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.RUMBLING;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Name;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.event.EventDate;
import seedu.address.testutil.EventBuilder;

public class JsonAdaptedEventTest {
    private static final String INVALID_NAME = "@sumo wrestling";
    private static final String INVALID_DATE = "40/13/1010";

    private static final String VALID_NAME = RUMBLING.getName().toString();
    private static final String VALID_DATE = RUMBLING.getDate().toString();
    private static final List<JsonAdaptedMember> VALID_PARTICIPANTS = RUMBLING.getParticipants().stream()
            .map(JsonAdaptedMember::new)
            .collect(Collectors.toList());
    private static final List<Boolean> VALID_ATTENDANCE = new ArrayList<>(RUMBLING.getMap().values());

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(RUMBLING);
        assertEquals(RUMBLING, event.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_NAME, VALID_DATE, VALID_PARTICIPANTS, VALID_ATTENDANCE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(null, VALID_DATE, VALID_PARTICIPANTS, VALID_ATTENDANCE);
        String expectedMessage = String.format(MISSING_FIELD_EVENT_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, INVALID_DATE, VALID_PARTICIPANTS, VALID_ATTENDANCE);
        String expectedMessage = EventDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, null, VALID_PARTICIPANTS, VALID_ATTENDANCE);
        String expectedMessage = String.format(MISSING_FIELD_EVENT_FORMAT, "date");
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void constructor_test() {
        JsonAdaptedEvent jsonAdaptedEvent = new JsonAdaptedEvent(VALID_NAME, VALID_DATE, VALID_PARTICIPANTS,
                VALID_ATTENDANCE);
        try {
            Event event = jsonAdaptedEvent.toModelType();
            Event expectedEvent = new EventBuilder(RUMBLING).build();
            assertEquals(expectedEvent, event);
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }

}
