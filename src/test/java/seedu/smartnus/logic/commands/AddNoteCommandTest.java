package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.smartnus.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.Messages;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.ModelStub;
import seedu.smartnus.model.ModelStubTagPanel;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.testutil.NoteBuilder;
import seedu.smartnus.ui.panel.NoteListPanel;

public class AddNoteCommandTest {
    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(null));
    }

    @Test
    public void execute_noteAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingNoteAdded modelStub = new ModelStubAcceptingNoteAdded();
        Note validNote = new NoteBuilder().build();

        CommandResult commandResult = new AddNoteCommand(validNote).execute(modelStub);

        assertEquals(String.format(AddNoteCommand.MESSAGE_SUCCESS, validNote), commandResult.getFeedbackToUser());
        assertEquals(List.of(validNote), modelStub.notesAdded);
    }

    @Test
    public void execute_duplicateNote_throwsCommandException() {
        Note validNote = new NoteBuilder().build();
        AddNoteCommand addCommand = new AddNoteCommand(validNote);
        ModelStub modelStub = new ModelStubWithNote(validNote);

        assertThrows(CommandException.class,
                AddNoteCommand.MESSAGE_DUPLICATE_NOTE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_inWrongPanel_throwsException() {
        ModelStubTagPanel modelStub = new ModelStubTagPanel();
        Note validNote = new NoteBuilder().build();


        assertThrows(CommandException.class,
                Messages.MESSAGE_NOT_IN_NOTE_PANEL, () -> new AddNoteCommand(validNote).execute(modelStub));
    }

    @Test
    public void equals() {
        Note alice = new NoteBuilder().withName("Alice").build();
        Note bob = new NoteBuilder().withName("Bob").build();
        AddNoteCommand addAliceCommand = new AddNoteCommand(alice);
        AddNoteCommand addBobCommand = new AddNoteCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddNoteCommand addAliceCommandCopy = new AddNoteCommand(alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different note -> returns false
        assertNotEquals(addAliceCommand, addBobCommand);
    }

    /**
     * A Model stub that always accept the note being added.
     */
    private class ModelStubAcceptingNoteAdded extends ModelStub {
        final ArrayList<Note> notesAdded = new ArrayList<>();

        @Override
        public String getPanel() {
            return NoteListPanel.NOTE_PANEL;
        }

        @Override
        public void addNote(Note note) {
            requireNonNull(note);
            notesAdded.add(note);
        }

        @Override
        public boolean hasNote(Note note) {
            requireNonNull(note);
            return notesAdded.stream().anyMatch(note::isSameNote);
        }

        @Override
        public ReadOnlySmartNus getSmartNus() {
            return new SmartNus();
        }
    }

    /**
     * A Model stub that contains a single note.
     */
    private class ModelStubWithNote extends ModelStub {
        private final Note note;

        ModelStubWithNote(Note note) {
            requireNonNull(note);
            this.note = note;
        }

        @Override
        public String getPanel() {
            return NoteListPanel.NOTE_PANEL;
        }

        @Override
        public boolean hasNote(Note note) {
            requireNonNull(note);
            return this.note.isSameNote(note);
        }
    }
}
