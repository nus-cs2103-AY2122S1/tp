package seedu.address.model.assessment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Assessment in a student's assessment list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Assessment {

    private final AssessmentName assessmentName;
    private final Score score;

    /**
     * Every field must be present and not null.
     */
    public Assessment(AssessmentName assessmentName, Score score) {
        requireAllNonNull(assessmentName, score);
        this.assessmentName = assessmentName;
        this.score = score;
    }

    public AssessmentName getAssessmentName() {
        return assessmentName;
    }

    public Score getScore() {
        return score;
    }

    /**
     * Returns true if both assessments have the same assessment name.
     */
    public boolean isSameAssessment(Assessment otherAssessment) {
        if (otherAssessment == this) {
            return true;
        }

        return otherAssessment != null
                && otherAssessment.getAssessmentName().equals(getAssessmentName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Assessment)) {
            return false;
        }

        Assessment otherAssessment = (Assessment) other;
        return otherAssessment.getAssessmentName().equals(getAssessmentName())
                && otherAssessment.getScore().equals(getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(assessmentName, score);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Assessment: ")
                .append(getAssessmentName())
                .append("; Score: ")
                .append(getScore());

        return builder.toString();
    }
}
