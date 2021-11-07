package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's name in the ProgrammerError.
 */
public class LabTotal {
    public static final String MESSAGE_CONSTRAINT =
            "Lab total score should be between 1 and 100 (inclusive)";

    private final Integer labTotalScore;

    /**
     * Constructs a {@code Name}.
     *
     * @param labTotalScore A valid name.
     */
    public LabTotal(Integer labTotalScore) {
        requireNonNull(labTotalScore);
        checkArgument(isValidLabTotalScore(labTotalScore), MESSAGE_CONSTRAINT);
        this.labTotalScore = labTotalScore;
    }

    public int getLabTotalScore() {
        return this.labTotalScore;
    }

    /**
     * Returns true if a given string is a valid labNum.
     */
    public static boolean isValidLabTotalScore (Integer test) {
        return test.compareTo(0) > 0 && test.compareTo(101) < 0;
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
