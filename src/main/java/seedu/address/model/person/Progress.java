package seedu.address.model.person;

/**
 * Represents a student's progress in TutorAid.
 */
public class Progress {

    public final String progress;

    /**
     * Constructs a {@code Progress}.
     *
     * @param progress Progress of the student;
     */
    public Progress(String progress) {
        this.progress = progress;
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
