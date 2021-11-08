package seedu.edrecord.model.assignment;

import static seedu.edrecord.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents an Grade under a Student in EdRecord.
 * Guarantees: immutable, details are present and not null.
 */
public class Grade {

    public enum GradeStatus { NOT_SUBMITTED, SUBMITTED, GRADED }

    public static final String MESSAGE_CONSTRAINTS = "Status should be \"not submitted\", \"submitted\" or \"graded\".";

    private final Optional<Score> score;
    private final GradeStatus status;

    /**
     * Constructs a {@code Grade}. Every field must be present and not null.
     *
     * @param score  Score of the grade
     * @param status Status of the grade.
     */
    public Grade(Optional<Score> score, GradeStatus status) {
        requireAllNonNull(score);
        this.score = score;
        this.status = status;
    }

    public Optional<Score> getScore() {
        return score;
    }

    public GradeStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Status: ").append(status);
        if (score.isPresent()) {
            builder.append(", Score: ");
            score.ifPresent(builder::append);
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Grade // instanceof handles nulls
                && score.equals(((Grade) other).score)
                && status.equals(((Grade) other).status)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, status);
    }
}
