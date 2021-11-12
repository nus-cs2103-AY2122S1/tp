package seedu.notor.model.common;

import static java.util.Objects.requireNonNull;

/**
 * Represents a note that is opened upon the execution of note command.
 */
public class Note {
    public static final Note EMPTY_NOTE = new EmptyNote();
    /** regex to remove newLine **/
    private static final String REMOVE_EMPTY_LINES = "(?m)^\\s*$[\n\r]{1,}";

    /**
     * content of note
     **/
    public final String value;
    private final String savedDate;

    /**
     * Constructor for a Note instance.
     *
     * @param value Value of the note.
     * @param savedDate Date that note is last saved.
     */
    private Note(String value, String savedDate) {
        requireNonNull(value);
        this.value = value;
        this.savedDate = savedDate;
    }

    /**
     * Factory method to create Note.
     *
     * @param value Content of the note.
     * @param savedDate Date that note is last saved.
     * @return Instance of Note or EmptyNote.
     */
    public static Note of(String value, String savedDate) {
        if (value.equals("")) {
            return EMPTY_NOTE;
        }
        return new Note(value, savedDate);
    }

    /**
     * Returns string representation of saved date.
     *
     * @return String representation of saved date
     */
    public String getSavedDate() {
        return savedDate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Note)) {
            return false;
        }
        Note noteOther = (Note) other;
        return value.equals(noteOther.value) && savedDate.equals(noteOther.savedDate); // state check
    }
    public String getNoEmptyLineNote() {
        return value.replaceAll(REMOVE_EMPTY_LINES, "");
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }

    private static final class EmptyNote extends Note {
        public EmptyNote() {
            super("", "");
        }
        @Override
        public boolean equals(Object other) {
            return other == this;
        }
    }
}
