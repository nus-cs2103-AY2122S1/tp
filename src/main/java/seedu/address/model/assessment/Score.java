package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Assessment's score in a student's assessment list.
 * Guarantees: immutable; is valid as declared in {@link #isValidScore(String)} and {@link #isValidScore(int, int)}
 */
public class Score {

    public static final String SCORE_DELIMITER = "/";

    public static final String MESSAGE_CONSTRAINTS = "Score should be of the format actual-score" + SCORE_DELIMITER
            + "total-score and adhere to the following constraints:\n"
            + "1. The actual-score should be an integer greater than or equal to 0.\n"
            + "2. The total-score should be an integer greater than 0.\n"
            + "3. The actual-score should be less than or equal to the total-score.";

    public static final String INVALID_ACTUAL_SCORE = "Actual score should have a value greater than or equal to 0.";
    public static final String INVALID_TOTAL_SCORE = "Total score should have a value greater than 0.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^\\d+" + SCORE_DELIMITER + "\\d+$";

    public static final int PASSING_THRESHOLD = 50;

    public final int actualScore;
    public final int totalScore;

    /**
     * Constructs a {@code Score}.
     *
     * @param actualScore A valid actual score.
     * @param totalScore A valid total score.
     */
    public Score(int actualScore, int totalScore) {
        requireNonNull(actualScore);
        requireNonNull(totalScore);
        checkArgument(isValidScore(actualScore, totalScore), MESSAGE_CONSTRAINTS);
        this.actualScore = actualScore;
        this.totalScore = totalScore;
    }

    /**
     * Returns the score percentage as calculated from actual score/total score
     */
    public int getPercentage() {
        return Math.round((float) actualScore / totalScore * 100);
    }

    /**
     * Returns true if the score percentage is less than the passing threshold.
     */
    public boolean isFail() {
        return getPercentage() < PASSING_THRESHOLD;
    }


    /**
     * Returns true if the given score and total score are valid
     */
    public static boolean isValidScore(int testActualScore, int testTotalScore) {
        return testActualScore >= 0 && testTotalScore > 0 && testActualScore <= testTotalScore;
    }

    public static boolean isValidScore(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return actualScore + SCORE_DELIMITER + totalScore;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Score // instanceof handles nulls
                && actualScore == ((Score) other).actualScore // state check
                && totalScore == ((Score) other).totalScore);
    }

    @Override
    public int hashCode() {
        return actualScore * 31 + totalScore;
    }
}
