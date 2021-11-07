package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalNote.TYPICAL_STUDENT_NOTE;
import static seedu.address.testutil.TypicalNote.TYPICAL_STUDENT_NOTE_ALT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.NoteBuilder;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Note noteCopy = new NoteBuilder(TYPICAL_STUDENT_NOTE).build();
        assertTrue(TYPICAL_STUDENT_NOTE.equals(noteCopy));

        // same object -> returns true
        assertTrue(TYPICAL_STUDENT_NOTE.equals(TYPICAL_STUDENT_NOTE));

        // null -> returns false
        assertFalse(TYPICAL_STUDENT_NOTE.equals(null));

        // different type -> returns false
        assertFalse(TYPICAL_STUDENT_NOTE.equals(5));

        // different note -> returns false
        assertFalse(TYPICAL_STUDENT_NOTE.equals(TYPICAL_STUDENT_NOTE_ALT));
    }
}
