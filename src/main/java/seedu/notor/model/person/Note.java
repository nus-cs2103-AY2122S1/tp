package seedu.notor.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's note that is opened upon note command.
 */
public class Note {

    /** content of note **/
    public final String value;
    public final String savedDate;


    /**
     * Constructor for a Note instance.
     *
     * @param value Value of the note.
     */
    public Note(String value, String savedDate) {
        requireNonNull(value);
        this.value = value;
        this.savedDate = savedDate;
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

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }


}
