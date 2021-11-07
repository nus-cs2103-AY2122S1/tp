package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_DINNER;
import static seedu.address.testutil.TypicalEvents.DINNER_EVENT;
import static seedu.address.testutil.TypicalEvents.PARTY_EVENT;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

class EventTest {
    @Test
    public void isSameEvent() {
        // equivalence partitions testing
        // same object -> returns true
        assertTrue(PARTY_EVENT.isSameEvent(PARTY_EVENT));

        // null -> returns false
        assertFalse(PARTY_EVENT.isSameEvent(null));

        // same name, all other attributes different -> returns true
        Event editedEventAttendee = new EventBuilder(PARTY_EVENT).withPerson(ALICE).build();
        assertTrue(PARTY_EVENT.isSameEvent(editedEventAttendee));

        // different name, all other attributes same -> returns false
        Event editedEventName = new EventBuilder(PARTY_EVENT).withEventName("HAHA Name").build();
        assertFalse(PARTY_EVENT.isSameEvent(editedEventName));

        // different name casing, all other attributes same -> return false
        Event lowerCaseEvent = new EventBuilder(PARTY_EVENT)
                .withEventName(VALID_EVENT_NAME_DINNER.toLowerCase()).build();
        assertFalse(PARTY_EVENT.isSameEvent(lowerCaseEvent));

        // event name has trailing spaces, all other attributes same -> returns false
        String eventNameWithTrailingSpaces = VALID_EVENT_NAME_DINNER + " ";
        editedEventName = new EventBuilder(PARTY_EVENT).withEventName(eventNameWithTrailingSpaces).build();
        assertFalse(PARTY_EVENT.isSameEvent(editedEventName));
    }

    @Test
    public void equals() {
        // equivalence partitions testing
        // same values -> returns true
        Event partyEventCopy = new EventBuilder(PARTY_EVENT).build();
        assertTrue(PARTY_EVENT.equals(partyEventCopy));

        // same object -> returns true
        assertTrue(PARTY_EVENT.equals(PARTY_EVENT));

        // null -> returns false
        assertFalse(PARTY_EVENT.equals(null));

        // different type -> returns false
        assertFalse(PARTY_EVENT.equals(5));

        // different event -> returns false
        assertFalse(PARTY_EVENT.equals(DINNER_EVENT));

        // different eventName -> returns false
        Event editedPartyEvent = new EventBuilder(PARTY_EVENT).withEventName(VALID_EVENT_NAME_DINNER).build();
        assertFalse(PARTY_EVENT.equals(editedPartyEvent));

        // event with attendee
        Event firstAttendeeEvent = new EventBuilder(PARTY_EVENT).withPerson(ALICE).build();
        assertFalse(PARTY_EVENT.equals(firstAttendeeEvent));

        // event with same attendee
        Event firstAttendeeEventCopy = new EventBuilder(PARTY_EVENT).withPerson(ALICE).build();
        assertTrue(firstAttendeeEvent.equals(firstAttendeeEventCopy));

        // event with different attendee
        Event secondAttendeeEvent = new EventBuilder(PARTY_EVENT).withPerson(BOB).build();
        assertFalse(firstAttendeeEvent.equals(secondAttendeeEvent));
    }
}
