package seedu.address.testutil;


import seedu.address.model.student.Note;

/**
 * A utility class to help with building Note objects.
 */
public class NoteBuilder {

    public static final String DEFAULT_NOTE_VALUE = "A personal note";

    private String value;

    /**
     * Creates a {@code NoteBuilder} with the default details.
     */
    public NoteBuilder() {
        value = DEFAULT_NOTE_VALUE;
    }

    /**
     * Initializes the NoteBuilder with the data of {@code noteToCopy}.
     */
    public NoteBuilder(Note noteToCopy) {
        value = noteToCopy.getValue();
    }

    /**
     * Sets the {@code value} of the {@code Note} that we are building.
     */
    public NoteBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Builds a Note
     * @return built note
     */
    public Note build() {
        return new Note(value);
    }

}
