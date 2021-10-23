package seedu.edrecord.model.grade;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.AppUtil.checkArgument;

import seedu.edrecord.model.assignment.MaxScore;

/**
 * Represents a student's score for an assignment in EdRecord.
 * Guarantees: immutable; is valid as declared in {@link #isValidScore(String)}
 */
public class Score {
    public static final String MESSAGE_CONSTRAINTS = "Student's score should be a non-negative integer less than "
            + "the maximum score for the assignment";

    private final Double score;
    private final MaxScore maxScore;

    /**
     * Constructs a {@code Weightage} object.
     *
     * @param score    Student's score.
     * @param maxScore Maximum score of the assignment.
     */
    public Score(String score, MaxScore maxScore) {
        requireNonNull(score);
        checkArgument(isValidScore(score), MESSAGE_CONSTRAINTS);
        this.score = Double.parseDouble(score);
        this.maxScore = maxScore;
    }

    /**
     * Returns true if a given string is a valid maxScore.
     *
     * @param test The string to test.
     */
    public boolean isValidScore(String test) {
        try {
            double d = Double.parseDouble(test);
            return d >= 0 && d <= maxScore.maxScore;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%.2f", score);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Score // instanceof handles nulls
                && score.equals(((Score) other).score)); // state check
    }

    @Override
    public int hashCode() {
        return score.hashCode();
    }
}
