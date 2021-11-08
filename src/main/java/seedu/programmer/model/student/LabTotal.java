package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.AppUtil.checkArgument;

/**
 * Represents a lab total score in the ProgrammerError.
 */
public class LabTotal {
    private final Integer labTotalScore;

    /**
     * Class constructor for {@code LabTotal}.
     *
     * @param labTotalScore The total score of a lab.
     */
    public LabTotal(Integer labTotalScore) {
        requireNonNull(labTotalScore);
        checkArgument(isValidLabTotalScore(labTotalScore), Lab.MESSAGE_LAB_TOTAL_SCORE_CONSTRAINT);
        this.labTotalScore = labTotalScore;
    }

    public int getLabTotalScore() {
        return this.labTotalScore;
    }

    /**
     * Returns true if a given string is a valid lab total score.
     *
     * @param test The Integer to be tested against.
     * @return true if the lab total score is valid.
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
