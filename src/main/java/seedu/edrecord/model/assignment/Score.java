package seedu.edrecord.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.AppUtil.checkArgument;

/**
 * Represents the score of an Assignment.
 * Guarantees: immutable; is valid as declared in {@link #isValidScore(String)}
 */
public class Score {
    public static final String MESSAGE_CONSTRAINTS = "Assignment score should be a non-negative integer";

    public final Double score;

    /**
     * Constructs a {@code Weightage} object.
     *
     * @param score The score.
     */
    public Score(String score) {
        requireNonNull(score);
        checkArgument(isValidScore(score), MESSAGE_CONSTRAINTS);
        this.score = Double.valueOf(score);
    }

    /**
     * Returns true if a given string is a valid maxScore.
     *
     * @param test The string to test.
     */
    public static boolean isValidScore(String test) {
        try {
            double d = Double.parseDouble(test);
            return d >= 0;
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
