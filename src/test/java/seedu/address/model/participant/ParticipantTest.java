package seedu.address.model.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ANOTHER_EVENT;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT;
import static seedu.address.testutil.TypicalParticipants.ALEX;
import static seedu.address.testutil.TypicalParticipants.BERNICE;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.Event;
import seedu.address.testutil.ParticipantBuilder;

public class ParticipantTest {

    @Test
    public void isSamePersonTest() {
        assertTrue(ALEX.isSameParticipant(ALEX));
        assertTrue(BERNICE.isSameParticipant(BERNICE));
        assertFalse(ALEX.isSameParticipant(BERNICE));
        assertFalse(BERNICE.isSameParticipant(ALEX));
    }

    @Test
    public void withBirthDateTest() {
        Participant alexP = new ParticipantBuilder().withBirthDate(2000, 8, 4).build();
        Participant bobP = new ParticipantBuilder().build();
        assertEquals("2000-08-04", alexP.getBirthDateString());
        assertEquals("N/A", bobP.getBirthDateString());
    }

    @Test
    public void addEventTest() {
        Participant alexP = new ParticipantBuilder().build();
        assertTrue(alexP.getNotes().isEmpty());
        alexP.addEvent(SAMPLE_EVENT);
        alexP.addEvent(ANOTHER_EVENT);
        assertFalse(alexP.getEvents().isEmpty());
        ArrayList<Event> expectedEvents = new ArrayList<>(List.of(SAMPLE_EVENT, ANOTHER_EVENT));
        assertEquals(alexP.getEvents(), expectedEvents);
    }

    @Test
    public void removeEventTest() {
        Participant alexP = new ParticipantBuilder().build();
        assertTrue(alexP.getEvents().isEmpty());
        alexP.addEvent(SAMPLE_EVENT);
        alexP.addEvent(ANOTHER_EVENT);
        alexP.removeEvent(ANOTHER_EVENT);
        ArrayList<Event> expectedEvents = new ArrayList<>(List.of(SAMPLE_EVENT));
        assertEquals(alexP.getEvents(), expectedEvents);
        alexP.removeEvent(SAMPLE_EVENT);
        assertTrue(alexP.getEvents().isEmpty());
    }

    @Test
    public void addNoteTest() {
        Participant alexP = new ParticipantBuilder().build();
        assertTrue(alexP.getNotes().isEmpty());
        alexP.addNote(new Note("Alex has allergy to pollen", Note.Importance.HIGH));
        alexP.addNote(new Note("Alex is vegetarian", Note.Importance.VERY_HIGH));
        assertFalse(alexP.getNotes().isEmpty());
        ArrayList<String> expectedNotes = new ArrayList<>(List.of("Importance[HIGH] Alex has allergy to pollen",
                "Importance[VERY_HIGH] Alex is vegetarian"));
        assertTrue(alexP.getNotes().stream().map(Object::toString).allMatch(expectedNotes::contains));
    }

    @Test
    public void removeNoteTest() {
        Participant alexP = new ParticipantBuilder().withBirthDate(2000, 8, 4).build();
        assertTrue(alexP.getNotes().isEmpty());
        alexP.addNote(new Note("Alex has allergy to pollen", Note.Importance.HIGH));
        alexP.addNote(new Note("Alex is vegetarian", Note.Importance.VERY_HIGH));
        alexP.removeNote(new Note("Alex has allergy to pollen", Note.Importance.HIGH));
        assertEquals("[Importance[VERY_HIGH] Alex is vegetarian]",
                alexP.getNotes().toString());
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Participant alexP = new ParticipantBuilder().withBirthDate(2000, 8, 4).build();
        assertThrows(UnsupportedOperationException.class, () -> alexP.getTags().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> alexP.getNotes().remove(0));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Participant alexP = new ParticipantBuilder().withBirthDate(2000, 8, 4).build();

        Participant alexPCopy = new ParticipantBuilder().withBirthDate(2000, 8, 4).build();

        Participant bobP = new ParticipantBuilder(BERNICE).build();

        assertTrue(alexP.equals(alexPCopy));

        // same object -> returns true
        assertTrue(alexP.equals(alexP));

        // null -> returns false
        assertFalse(alexP.equals(null));

        // different type -> returns false
        assertFalse(alexP.equals(5));

        // different person -> returns false
        assertFalse(alexP.equals(bobP));

        // different note -> returns false
        Participant editedAlex = new ParticipantBuilder(alexP).withNotes(Set.of(new Note("She is vegan",
                Note.Importance.VERY_HIGH))).build();
        assertFalse(alexP.equals(editedAlex));

    }
}
