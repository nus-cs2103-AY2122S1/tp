package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.EVENT_STUB;
import static seedu.address.testutil.TypicalEvents.ANOTHER_EVENT_STUB;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void getParticipants() {
        // TODO: Modify and update as we can add participants into events
        assertEquals(EVENT_STUB.getParticipants().size(), 0);
    }

    @Test
    public void markAsDone() {
        assertFalse(EVENT_STUB.getIsDone());
        EVENT_STUB.markAsDone();
        assertTrue(EVENT_STUB.getIsDone());
    }

    @Test
    public void isSameEvent() {
        // same name
        assertTrue(EVENT_STUB.isSameEvent(ANOTHER_EVENT_STUB));
    }

    @Test
    void testEquals() {
        // same name but different other fields
        assertFalse(EVENT_STUB.equals(ANOTHER_EVENT_STUB));

        // same Event
        assertTrue(EVENT_STUB.equals(EVENT_STUB));

        // null -> returns false
        assertFalse(EVENT_STUB.equals(null));

        // TODO: If implementing edit Event, can add more testcases.
    }

}