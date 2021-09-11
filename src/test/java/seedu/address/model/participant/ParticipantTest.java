package seedu.address.model.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.particpant.Note;
import seedu.address.model.particpant.Participant;
import seedu.address.model.person.Person;

public class ParticipantTest {

    @Test
    public void isSamePersonTest() {
        Participant aliceP = new Participant(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(),
                ALICE.getTags());
        Participant bobP = new Participant(BOB.getName(), BOB.getPhone(), BOB.getEmail(), BOB.getAddress(),
                BOB.getTags());
        assertTrue(aliceP.isSamePerson(ALICE));
        assertFalse(aliceP.isSamePerson(bobP));
        assertFalse(aliceP.isSamePerson(BOB));
    }

    @Test
    public void withBirthDateTest() {
        Participant aliceP = new Participant(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(),
                ALICE.getTags()).withBirthDate(2000, 8, 4);
        Participant bobP = new Participant(BOB.getName(), BOB.getPhone(), BOB.getEmail(), BOB.getAddress(),
                BOB.getTags());
        assertEquals("2000-08-04", aliceP.getBirthDate().toString());
        assertEquals("N/A", bobP.getBirthDate().toString());
    }

    @Test
    public void addNoteTest() {
        Participant aliceP = new Participant(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(),
                ALICE.getTags()).withBirthDate(2000, 8, 4);
        assertTrue(aliceP.getNotes().isEmpty());
        aliceP.addNote(new Note("Alice has allergy to pollen", Note.Importance.HIGH));
        aliceP.addNote(new Note("Alice is vegetarian", Note.Importance.VERY_HIGH));
        assertFalse(aliceP.getNotes().isEmpty());
        assertEquals("[Importance[HIGH] Alice has allergy to pollen, Importance[VERY_HIGH] Alice is vegetarian]",
                aliceP.getNotes().toString());
    }

    @Test
    public void removeNoteTest() {
        Participant aliceP = new Participant(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(),
                ALICE.getTags()).withBirthDate(2000, 8, 4);
        assertTrue(aliceP.getNotes().isEmpty());
        aliceP.addNote(new Note("Alice has allergy to pollen", Note.Importance.HIGH));
        aliceP.addNote(new Note("Alice is vegetarian", Note.Importance.VERY_HIGH));
        aliceP.removeNote(new Note("Alice has allergy to pollen", Note.Importance.HIGH));
        assertEquals("[Importance[VERY_HIGH] Alice is vegetarian]",
                aliceP.getNotes().toString());
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Participant aliceP = new Participant(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(),
                ALICE.getTags()).withBirthDate(2000, 8, 4);
        assertThrows(UnsupportedOperationException.class, () -> aliceP.getTags().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> aliceP.getNotes().remove(0));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Participant aliceP = new Participant(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(), ALICE.getAddress(),
                ALICE.getTags());

        Participant alicePCopy = new Participant(ALICE.getName(), ALICE.getPhone(), ALICE.getEmail(),
                ALICE.getAddress(), ALICE.getTags());

        Participant bobP = new Participant(BOB.getName(), BOB.getPhone(), BOB.getEmail(), BOB.getAddress(),
                BOB.getTags());

        assertTrue(aliceP.equals(alicePCopy));

        // same object -> returns true
        assertTrue(aliceP.equals(aliceP));

        // null -> returns false
        assertFalse(aliceP.equals(null));

        // different type -> returns false
        assertFalse(aliceP.equals(5));

        // different person -> returns false
        assertFalse(aliceP.equals(bobP));

        // different note -> returns false
        Person editedAlice = aliceP.withNotes(Set.of(new Note("She is vegan", Note.Importance.VERY_HIGH)));
        assertFalse(aliceP.equals(editedAlice));

        // different nextOfKins -> returns false
        editedAlice = aliceP.withNextOfKins(new ArrayList(List.of(BOB)));
        assertFalse(aliceP.equals(editedAlice));

    }
}
