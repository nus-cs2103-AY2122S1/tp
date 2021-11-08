package seedu.smartnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartnus.storage.JsonAdaptedNote.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalNotes.CS2103T_NOTE;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.exceptions.IllegalValueException;
import seedu.smartnus.model.note.NoteName;

public class JsonAdaptedNoteTest {
    private static final String INVALID_NAME = " ";

    private static final String VALID_NAME = CS2103T_NOTE.getTitle();

    @Test
    public void toModelType_validNoteDetails_returnsNote() throws Exception {
        JsonAdaptedNote note = new JsonAdaptedNote(CS2103T_NOTE);
        assertEquals(CS2103T_NOTE, note.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedNote note =
                new JsonAdaptedNote(INVALID_NAME);
        String expectedMessage = NoteName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedNote note = new JsonAdaptedNote((String) null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NoteName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, note::toModelType);
    }
}
