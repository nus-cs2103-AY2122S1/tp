package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.ANOTHER_EVENT;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT_2;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT_COPY_DIFFERENT_TIME;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT_DEFAULT_TIME_AND_COMPLETION;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT_SPECIFIED_TIME_AND_COMPLETION;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT_SPECIFIED_TIME_AND_COMPLETION_COPY;
import static seedu.address.testutil.TypicalParticipants.ALEX;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.participant.Participant;

public class EventTest {
    private Event testEvent;

    @BeforeEach
    void init() {
        testEvent = new Event(new EventName("Testing"), new EventDate("2021-09-18"),
                new EventTime("1000"));
    }

    @Test
    public void getParticipants() {
        assertEquals(testEvent.getParticipants().size(), 0);
        testEvent.getParticipants().add(ALEX);
        List<Participant> expectedParticipants = new ArrayList<>(List.of(ALEX));
        assertEquals(testEvent.getParticipants(), expectedParticipants);
        assertEquals(testEvent.getParticipants().size(), 1);
    }

    @Test
    public void addParticipant() {
        testEvent.addParticipant(ALEX);
        List<Participant> expectedParticipants = new ArrayList<>(List.of(ALEX));
        assertEquals(testEvent.getParticipants(), expectedParticipants);
        assertEquals(testEvent.getParticipants().size(), 1);
    }

    @Test
    public void removeParticipant() {
        testEvent.addParticipant(ALEX);
        testEvent.getParticipants().remove(ALEX);
        List<Participant> expectedParticipants = new ArrayList<>();
        assertEquals(testEvent.getParticipants(), expectedParticipants);
        assertEquals(testEvent.getParticipants().size(), 0);
    }

    @Test
    public void hasParticipant() {
        testEvent.addParticipant(ALEX);
        assertTrue(testEvent.hasParticipant(ALEX));
        testEvent.removeParticipant(ALEX);
        assertFalse(testEvent.hasParticipant(ALEX));
    }

    @Test
    public void markAsDone() {
        assertFalse(testEvent.getIsDone());
        Event doneEvent = testEvent.markAsDone();
        assertTrue(doneEvent.getIsDone());
    }

    @Test
    public void isSameEvent() {
        // same name
        assertTrue(SAMPLE_EVENT.isSameEvent(SAMPLE_EVENT));
        assertTrue(SAMPLE_EVENT.isSameEvent(SAMPLE_EVENT_COPY_DIFFERENT_TIME));
    }

    @Test
    public void testEquals() {
        // same name but different other fields
        assertFalse(SAMPLE_EVENT.equals(ANOTHER_EVENT));

        // same Event
        assertTrue(SAMPLE_EVENT.equals(SAMPLE_EVENT));

        // different Event, identical details
        assertTrue(SAMPLE_EVENT_SPECIFIED_TIME_AND_COMPLETION
                .equals(SAMPLE_EVENT_SPECIFIED_TIME_AND_COMPLETION_COPY));

        // null -> returns false
        assertFalse(SAMPLE_EVENT.equals(null));

        // TODO: If implementing edit Event, can add more testcases.
    }

    @Test
    public void testHashCode() {
        // same Event -> returns same hashcode
        assertEquals(SAMPLE_EVENT.hashCode(), SAMPLE_EVENT.hashCode());

        // different Event -> returns different hashcode
        assertNotEquals(SAMPLE_EVENT.hashCode(), SAMPLE_EVENT_2.hashCode());

        // same EventName, different other details
        assertNotEquals(SAMPLE_EVENT_SPECIFIED_TIME_AND_COMPLETION.hashCode(),
                SAMPLE_EVENT_SPECIFIED_TIME_AND_COMPLETION_COPY.hashCode());

    }

    @Test
    public void testToString() {
        Event firstTestEvent = SAMPLE_EVENT_SPECIFIED_TIME_AND_COMPLETION;
        Event secondTestEvent = SAMPLE_EVENT_DEFAULT_TIME_AND_COMPLETION;

        final StringBuilder firstBuilder = new StringBuilder();
        firstBuilder.append("[").append((firstTestEvent.getIsDone() ? "X" : " ")).append("] ")
                .append(firstTestEvent.getNameString()).append(" (at: ")
                .append(firstTestEvent.getDateString()).append(" ")
                .append(firstTestEvent.getTimeString())
                .append(") ")
                .append("Total Participants: ").append(firstTestEvent.getParticipants().size());

        final StringBuilder secondBuilder = new StringBuilder();
        secondBuilder.append("[").append((secondTestEvent.getIsDone() ? "X" : " ")).append("] ")
                .append(secondTestEvent.getNameString()).append(" (at: ")
                .append(secondTestEvent.getDateString()).append(" ")
                .append(secondTestEvent.getTimeString())
                .append(") ")
                .append("Total Participants: ").append(secondTestEvent.getParticipants().size());

        assertEquals(firstTestEvent.toString(), firstBuilder.toString());
        assertEquals(secondTestEvent.toString(), secondBuilder.toString());
    }
}
