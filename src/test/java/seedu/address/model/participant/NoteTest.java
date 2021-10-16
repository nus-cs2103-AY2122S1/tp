package seedu.address.model.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void noteStringRepresentationTest() {
        Note note = new Note("test1", Note.Importance.VERY_HIGH);
        assertEquals("Importance[VERY_HIGH] test1", note.toString());
    }

    @Test
    public void testEquals() {
        Note sampleNote = new Note("E flat", Note.Importance.VERY_HIGH);
        Note sampleNoteCopy = new Note("E flat", Note.Importance.VERY_HIGH);
        Note differentNameSampleNote = new Note("D flat", Note.Importance.VERY_HIGH);
        Note differentImportanceSampleNote = new Note("E flat", Note.Importance.HIGH);

        // same object -> return true
        assertEquals(sampleNote, sampleNote);

        // different object, same value -> return true
        assertEquals(sampleNote, sampleNoteCopy);

        // null object -> return false
        assertNotEquals(sampleNote, null);

        // different type -> return false
        assertNotEquals(sampleNote, 5);

        // different name -> return false
        assertNotEquals(sampleNote, differentNameSampleNote);

        // different importance -> return false
        assertNotEquals(sampleNote, differentImportanceSampleNote);
    }
}
