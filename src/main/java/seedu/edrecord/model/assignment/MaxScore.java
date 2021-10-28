package seedu.edrecord.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.AppUtil.checkArgument;

/**
 * Represents an Assignment's weightage in edrecord.
 * Guarantees: immutable; is valid as declared in {@link #isValidMaxScore(String)}
 */
public class MaxScore implements Comparable<MaxScore> {
    public static final String MESSAGE_CONSTRAINTS = "Assignment maximum score should be a non-negative integer";

    public final Float maxScore;

    /**
     * Constructs a {@code Weightage} object.
     * @param maxScore The maxScore.
     */
    public MaxScore(String maxScore) {
        requireNonNull(maxScore);
        checkArgument(isValidMaxScore(maxScore), MESSAGE_CONSTRAINTS);
        this.maxScore = Float.valueOf(maxScore);
    }

    /**
     * Returns true if a given string is a valid maxScore.
     * @param test The string to test.
     */
    public static boolean isValidMaxScore(String test) {
        try {
            float f = Float.parseFloat(test);
            return f >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%.2f", maxScore);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MaxScore // instanceof handles nulls
                && maxScore.equals(((MaxScore) other).maxScore)); // state check
    }

    @Override
    public int hashCode() {
        return maxScore.hashCode();
    }

    @Override
    public int compareTo(MaxScore o) {
        return this.maxScore.compareTo(o.maxScore);
    }
}
