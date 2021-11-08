package seedu.smartnus.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_4;
import static seedu.smartnus.testutil.TypicalNotes.CS2100_NOTE;
import static seedu.smartnus.testutil.TypicalNotes.CS2103T_NOTE;

import org.junit.jupiter.api.Test;

import seedu.smartnus.testutil.NoteBuilder;

public class NoteTest {

    @Test
    public void isSameNote() {
        // same object -> returns true
        assertTrue(CS2100_NOTE.isSameNote(CS2100_NOTE));

        // null -> returns false
        assertFalse(CS2100_NOTE.isSameNote(null));

        // same name, all other attributes different -> returns true
        Note editedAlice = new NoteBuilder(CS2103T_NOTE).build();
        assertTrue(CS2103T_NOTE.isSameNote(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new NoteBuilder(CS2103T_NOTE).build();
        assertFalse(CS2100_NOTE.isSameNote(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Note editedBob = new NoteBuilder(CS2103T_NOTE).withName(VALID_QUESTION_4.toLowerCase()).build();
        assertFalse(CS2103T_NOTE.isSameNote(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_QUESTION_4 + " ";
        editedBob = new NoteBuilder(CS2103T_NOTE).withName(nameWithTrailingSpaces).build();
        assertFalse(CS2103T_NOTE.isSameNote(editedBob));
    }

    @Test
    public void hashCode_test() {
        Note cs2103tNote = new NoteBuilder(CS2103T_NOTE).build();
        Note cs2103tCopy = new NoteBuilder(CS2103T_NOTE).build();

        assertEquals(cs2103tNote.hashCode(), cs2103tCopy.hashCode());

        Note cs2100Note = new NoteBuilder(CS2100_NOTE).build();
        assertNotEquals(cs2103tNote.hashCode(), cs2100Note.hashCode());
    }

    @Test
    public void equals() {
        assertEquals(CS2100_NOTE, CS2100_NOTE);
        assertNotEquals(CS2100_NOTE, CS2103T_NOTE);

        //different classes, returns false
        assertNotEquals(CS2100_NOTE, 2);
    }
}
