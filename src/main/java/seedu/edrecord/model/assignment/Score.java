package seedu.edrecord.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.AppUtil.checkArgument;

/**
 * Represents the score of an Assignment.
 * Guarantees: immutable; is valid as declared in {@link #isValidScore(String)}
 */
public class Score implements Comparable<Score> {
    public static final String MESSAGE_CONSTRAINTS = "Assignment score should be a non-negative decimal value.";

    public final Double score;

    /**
     * Constructs a {@code Score} object.
     *
     * @param score The score.
     */
    public Score(String score) {
        requireNonNull(score);
        checkArgument(isValidScore(score), MESSAGE_CONSTRAINTS);
        this.score = Double.valueOf(score);
    }

    /**
     * Returns true if a given string is a valid score.
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

    /**
     * Compares a score to another score.
     *
     * @param other Other score to compare to.
     * @return 0 if both scores are equal, a value less than 0 if this score is less than the other score,
     * a value greater than 0 if this score is numerically greater than the other score.
     */
    @Override
    public int compareTo(Score other) {
        return score.compareTo(other.score);
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
