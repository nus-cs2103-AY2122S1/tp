package seedu.smartnus.testutil;

import seedu.smartnus.model.note.Note;

public class NoteBuilder {
    public static final String DEFAULT_NOTE = "CS2103T is super fun";

    private String noteName;

    /**
     * Creates a {@code NoteBuilder} with the default details.
     */
    public NoteBuilder() {
        noteName = DEFAULT_NOTE;
    }

    /**
     * Initializes the NoteBuilder with the data of {@code noteToCopy}.
     */
    public NoteBuilder(Note noteToCopy) {
        noteName = noteToCopy.getTitle();
    }

    /**
     * Sets the {@code Name} of the {@code Note} that we are building.
     */
    public NoteBuilder withName(String noteName) {
        this.noteName = noteName;
        return this;
    }

    /**
     * Builds the the {@code Note} that we are building.
     */
    public Note build() {
        return new Note(noteName);
    }
}
