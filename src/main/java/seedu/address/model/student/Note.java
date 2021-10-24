package seedu.address.model.student;

<<<<<<< Updated upstream
/**
 * Stub for implementing Ui
 */
public class Note {

    public String getNote() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vitae mattis nunc,"
            + " eu dictum ex. Proin auctor, lectus sit amet semper posuere, lorem nulla maximus lacus, "
            + "vitae finibus urna enim ac augue. Sed sapien lectus, faucibus sed ipsum non, laoreet ultrices "
            + "felis. Nullam ornare nisi eget lectus interdum aliquam. Curabitur vulputate, lacus nec feugiat "
            + "placerat, ligula magna tempor augue, ac maximus nunc justo a tortor. Etiam id elit eros. "
            + "Etiam vel mauris in dolor viverra dictum. Donec vel purus eu lorem tempor molestie. "
            + "Integer pharetra feugiat neque, at sagittis nulla commodo id. Nulla justo erat, commodo eget "
            + "mattis ac, sollicitudin eget erat. Curabitur accumsan ipsum eu mi vulputate, tincidunt pharetra "
            + "odio euismod. Praesent condimentum tristique quam, id viverra massa. Etiam at tristique sapien. "
            + "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. "
            + "Nulla facilisi.";
=======
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
>>>>>>> Stashed changes
    }
}
