package tutoraid.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student's progress in TutorAid.
 */
public class Progress {

    public static final String MESSAGE_CONSTRAINTS =
            "Progress can be anything";

    public final String progress;

    /**
     * Constructs a {@code Progress}.
     *
     * @param progress Progress of the student;
     */
    public Progress(String progress) {
        requireNonNull(progress);
        this.progress = progress;
    }

    /**
     * Returns true if a given string is a valid progress.
     */
    public static boolean isValidProgress(String test) {
        return !test.trim().equals("");
    }

    @Override
    public String toString() {
        return progress;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Progress // instanceof handles nulls
                && this.progress.equals(((Progress) other).progress)); // state check
    }

    @Override
    public int hashCode() {
        return progress.hashCode();
    }
}
