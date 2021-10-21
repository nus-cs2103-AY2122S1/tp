package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;

public class Score {

    public static final int PASSING_THRESHOLD = 50;

    public final int score;
    public final int totalScore;

    /**
     * Constructs a {@code Score}.
     *
     * @param score A valid score.
     */
    public Score(int score, int totalScore) {
        requireNonNull(score);
        requireNonNull(totalScore);
        this.score = score;
        this.totalScore = totalScore;
    }

    public boolean isPassing() {
        return score / totalScore * 100 >= PASSING_THRESHOLD;
    }

    @Override
    public String toString() {
        return score + "/" + totalScore;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Score // instanceof handles nulls
                && score == ((Score) other).score // state check
                && score == ((Score) other).totalScore);
    }

    @Override
    public int hashCode() {
        return score * 31 + totalScore;
    }
}
