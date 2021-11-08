package seedu.smartnus.testutil;

import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NOTE_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_NOTE_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.note.Note;

public class TypicalNotes {
    public static final Note CS2103T_NOTE = new NoteBuilder().withName(VALID_NOTE_1).build();

    public static final Note CS2100_NOTE = new NoteBuilder().withName(VALID_NOTE_2)
            .build();

    // to prevent instantiation
    private TypicalNotes() {}

    /**
     * Returns an {@code SmartNus} with all the typical questions.
     */
    public static SmartNus getTypicalSmartNusNotes() {
        SmartNus ab = new SmartNus();
        for (Note note : getTypicalNotes()) {
            ab.addNote(note);
        }
        return ab;
    }

    public static List<Note> getTypicalNotes() {
        return new ArrayList<>(Arrays.asList(CS2103T_NOTE, CS2100_NOTE));
    }
}
