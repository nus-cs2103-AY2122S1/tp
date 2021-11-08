package seedu.address.testutil;

import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.Score;

/**
 * A utility class to help with building Assessment objects.
 */
public class AssessmentBuilder {

    public static final String DEFAULT_ASSESSMENT_NAME = "Midterms";
    public static final int DEFAULT_ACTUAL_SCORE = 60;
    public static final int DEFAULT_TOTAL_SCORE = 100;

    private AssessmentName assessmentName;
    private Score score;

    /**
     * Creates a {@code AssessmentBuilder} with the default details.
     */
    public AssessmentBuilder() {
        assessmentName = new AssessmentName(DEFAULT_ASSESSMENT_NAME);
        score = new Score(DEFAULT_ACTUAL_SCORE, DEFAULT_TOTAL_SCORE);
    }

    /**
     * Initializes the AssessmentBuilder with the data of {@code assessmentToCopy}.
     */
    public AssessmentBuilder(Assessment assessmentToCopy) {
        assessmentName = assessmentToCopy.getAssessmentName();
        score = assessmentToCopy.getScore();
    }

    /**
     * Sets the {@code AssessmentName} of the {@code Assessment} that we are building.
     */
    public AssessmentBuilder withAssesmentName(String assessmentName) {
        this.assessmentName = new AssessmentName(assessmentName);
        return this;
    }

    /**
     * Sets the {@code Score} of the {@code Assessment} that we are building.
     */
    public AssessmentBuilder withScore(int actualScore, int totalScore) {
        this.score = new Score(actualScore, totalScore);
        return this;
    }

    /**
     * Builds an Assessment
     * @return built assessment
     */
    public Assessment build() {
        return new Assessment(assessmentName, score);
    }

}
