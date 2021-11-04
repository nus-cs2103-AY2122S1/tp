package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a student's name in the ProgrammerError.
 */
public class LabTotal {

    private final Integer labTotalScore;

    /**
     * Constructs a {@code Name}.
     *
     * @param labTotal A valid name.
     */
    public LabTotal(Integer labTotalScore) {
        requireNonNull(labTotalScore);
        //checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.labTotalScore = labTotalScore;
    }

    public int getLabTotalScore() {
        return this.labTotalScore;
    }

    @Override
    public String toString() {
        return labTotalScore.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LabTotal // instanceof handles nulls
                && labTotalScore.equals(((LabTotal) other).labTotalScore)); // state check
    }

    @Override
    public int hashCode() {
        return labTotalScore.hashCode();
    }
}
