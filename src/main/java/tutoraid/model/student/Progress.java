package tutoraid.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student's progress in TutorAid.
 */
public class Progress {

    public static final String MESSAGE_CONSTRAINTS =
            "Progress can be anything";

    public static final String EMPTY_PROGRESS_DESCRIPTION = "No Progress";

    private static final Progress EMPTY_PROGRESS = new Progress(EMPTY_PROGRESS_DESCRIPTION);

    public final String progress;

    /**
     * Constructs a {@code Progress}.
     *
     * @param progress Progress of the student
     */
    public Progress(String progress) {
        requireNonNull(progress);
        this.progress = progress;
    }

    /**
     * Checks if a given string is a valid progress.
     *
     * @return true if the string is not empty, false otherwise
     */
    public static boolean isValidProgress(String test) {
        return !test.trim().equals("");
    }

    /**
     * Checks if this progress is an empty progress.
     *
     * @return true if this progress is an empty progress, false otherwise
     */
    public boolean isEmptyProgress() {
        return EMPTY_PROGRESS.equals(this);
    }

    /**
     * Returns the latest progress if there is at least one progress, else returns an EMPTY_PROGRESS.
     */
    public static Progress getEmptyProgress() {
        return EMPTY_PROGRESS;
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
