package seedu.smartnus.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.util.AppUtil.checkArgument;

import java.util.Objects;

public class Note {

    public static final String MESSAGE_CONSTRAINTS = "Notes can take any values, and they cannot not be blank";

    /*
     * The first character of the choice must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String title;

    /**
     * Constructs a {@code Note}.
     *
     * @param title Title of the note.
     */
    public Note(String title) {
        requireNonNull(title);
        checkArgument(isValidNoteTitle(title), MESSAGE_CONSTRAINTS);
        this.title = title;
    }

    /**
     * Returns true if a given string is a valid choice title.
     */
    public static boolean isValidNoteTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getTitle() {
        return title;
    }

    /**
     * Returns true if both notes have the same name.
     * This defines a weaker notion of equality between two notes.
     */
    public boolean isSameNote(Note otherNote) {
        if (otherNote == this) {
            return true;
        }

        return otherNote != null
                && otherNote.getTitle().equals(getTitle());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return otherNote.getTitle().equals(getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
