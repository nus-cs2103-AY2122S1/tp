package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.ANOTHER_EVENT;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT_COPY_DIFFERENT_TIME;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void getParticipants() {
        // TODO: Modify and update as we can add participants into events
        assertEquals(SAMPLE_EVENT.getParticipants().size(), 0);
    }

    @Test
    public void markAsDone() {
        assertFalse(SAMPLE_EVENT.getIsDone());
        Event doneEvent = SAMPLE_EVENT.markAsDone();
        assertTrue(doneEvent.getIsDone());
    }

    @Test
    public void isSameEvent() {
        // same name
        assertTrue(SAMPLE_EVENT.isSameEvent(SAMPLE_EVENT_COPY_DIFFERENT_TIME));
    }

    @Test
    void testEquals() {
        // same name but different other fields
        assertFalse(SAMPLE_EVENT.equals(ANOTHER_EVENT));

        // same Event
        assertTrue(SAMPLE_EVENT.equals(SAMPLE_EVENT));

        // null -> returns false
        assertFalse(SAMPLE_EVENT.equals(null));

        // TODO: If implementing edit Event, can add more testcases.
    }
}
