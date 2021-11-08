package seedu.smartnus.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalNotes.CS2100_NOTE;
import static seedu.smartnus.testutil.TypicalNotes.CS2103T_NOTE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.smartnus.model.note.exceptions.DuplicateNoteException;
import seedu.smartnus.model.note.exceptions.NoteNotFoundException;
import seedu.smartnus.testutil.NoteBuilder;


public class NoteListTest {
    private final NoteList noteList = new NoteList();

    @Test
    public void add_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noteList.add(null));
    }

    @Test
    public void contains_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noteList.contains(null));
    }

    @Test
    public void contains_noteNotInList_returnsFalse() {
        assertFalse(noteList.contains(CS2100_NOTE));
    }

    @Test
    public void contains_noteInList_returnsTrue() {
        noteList.add(CS2100_NOTE);
        assertTrue(noteList.contains(CS2100_NOTE));
    }

    @Test
    public void contains_noteWithSameIdentityFieldsInList_returnsTrue() {
        noteList.add(CS2100_NOTE);
        Note editedAlice = new NoteBuilder(CS2100_NOTE)
                .build();
        assertTrue(noteList.contains(editedAlice));
    }

    @Test
    public void add_duplicateNote_throwsDuplicateNoteException() {
        noteList.add(CS2103T_NOTE);
        assertThrows(DuplicateNoteException.class, () -> noteList.add(CS2103T_NOTE));
    }

    @Test
    public void setNote_nullTargetNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noteList.setNote(null, CS2103T_NOTE));
    }

    @Test
    public void setNote_targetNoteNotInList_throwsNoteNotFoundException() {
        assertThrows(NoteNotFoundException.class, () ->
                noteList.setNote(CS2103T_NOTE, CS2103T_NOTE));
    }

    @Test
    public void setNote_editedNoteIsSameNote_success() {
        noteList.add(CS2103T_NOTE);
        noteList.setNote(CS2103T_NOTE, CS2103T_NOTE);
        NoteList expectedUniqueNoteList = new NoteList();
        expectedUniqueNoteList.add(CS2103T_NOTE);
        assertEquals(expectedUniqueNoteList, noteList);
    }

    @Test
    public void setNote_editedNoteHasNonUniqueIdentity_throwsDuplicateNoteException() {
        noteList.add(CS2103T_NOTE);
        noteList.add(CS2100_NOTE);
        assertThrows(DuplicateNoteException.class, () ->
                noteList.setNote(CS2103T_NOTE, CS2100_NOTE));
    }

    @Test
    public void remove_noteDoesNotExist_throwsNoteNotFoundException() {
        assertThrows(NoteNotFoundException.class, () -> noteList.remove(CS2100_NOTE));
    }

    @Test
    public void setNotes_nullUniqueNoteList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noteList.setNotes((NoteList) null));
    }

    @Test
    public void setNotes_uniqueNoteList_replacesOwnListWithProvidedUniqueNoteList() {
        noteList.add(CS2100_NOTE);
        NoteList expectedUniqueNoteList = new NoteList();
        expectedUniqueNoteList.add(CS2103T_NOTE);
        noteList.setNotes(expectedUniqueNoteList);
        assertEquals(expectedUniqueNoteList, noteList);
    }

    @Test
    public void setNotes_list_replacesOwnListWithProvidedList() {
        noteList.add(CS2103T_NOTE);
        List<Note> uniqueNoteList = Collections.singletonList(CS2100_NOTE);
        noteList.setNotes(uniqueNoteList);
        NoteList expectedUniqueNoteList = new NoteList();
        expectedUniqueNoteList.add(CS2100_NOTE);
        assertEquals(expectedUniqueNoteList, noteList);
    }

    @Test
    public void setNotes_listWithDuplicateNotes_throwsDuplicateNoteException() {
        List<Note> listWithDuplicateNotes = Arrays.asList(CS2100_NOTE, CS2100_NOTE);
        assertThrows(DuplicateNoteException.class, () -> noteList
                .setNotes(listWithDuplicateNotes));
    }

    @Test
    public void setNote_nullEditedNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noteList.setNote(CS2103T_NOTE, null));
    }

    @Test
    public void setNote_editedNoteHasSameIdentity_success() {
        noteList.add(CS2103T_NOTE);
        Note editedCs2103t = new NoteBuilder(CS2103T_NOTE).build();
        noteList.setNote(CS2103T_NOTE, editedCs2103t);
        NoteList expectedNoteList = new NoteList();
        expectedNoteList.add(editedCs2103t);
        assertEquals(expectedNoteList, noteList);
    }

    @Test
    public void setNote_editedNoteHasDifferentIdentity_success() {
        noteList.add(CS2103T_NOTE);
        noteList.setNote(CS2103T_NOTE, CS2100_NOTE);
        NoteList expectedNoteList = new NoteList();
        expectedNoteList.add(CS2100_NOTE);
        assertEquals(expectedNoteList, noteList);
    }

    @Test
    public void remove_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noteList.remove(null));
    }

    @Test
    public void remove_existingNote_removesNote() {
        noteList.add(CS2100_NOTE);
        noteList.remove(CS2100_NOTE);
        NoteList expectedNoteList = new NoteList();
        assertEquals(expectedNoteList, noteList);
    }

    @Test
    public void setNotes_nullNoteList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noteList.setNoteList(null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> noteList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void setNotes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> noteList.setNotes((List<Note>) null));
    }

    @Test
    public void hashCode_test() {
        NoteList alice = new NoteList();
        NoteList bob = new NoteList();
        alice.add(CS2100_NOTE);
        bob.add(CS2103T_NOTE);

        NoteList aliceCopy = new NoteList();
        aliceCopy.setNotes(alice);

        assertEquals(alice.hashCode(), aliceCopy.hashCode());
        assertNotEquals(alice.hashCode(), bob.hashCode());
    }

    @Test
    public void equals() {
        NoteList alice = new NoteList();
        alice.add(CS2100_NOTE);
        NoteList bob = new NoteList();
        bob.add(CS2103T_NOTE);
        assertFalse(alice.equals(bob));

        NoteList aliceCopy = new NoteList();
        aliceCopy.setNotes(alice);
        assertTrue(alice.equals(aliceCopy));

        assertFalse(alice.equals(2));

        //equal to itself
        assertTrue(alice.equals(alice));
    }
}
