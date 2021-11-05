package seedu.address.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents the notes of a student in the CSBook.
 * Guarantee: immutable; is always valid
 */
public class Note {
    public final String value;

    /**
     * Constructor to create a note
     */
    public Note(String note) {
        requireNonNull(note);
        value = note;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note
                && value.equals(((Note) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
