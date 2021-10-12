package seedu.address.testutil;

import seedu.address.model.student.Assessment;
import seedu.address.model.student.ID;
import seedu.address.model.student.Score;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class to help with building Assessment object.
 */
public class AssessmentBuilder {

    public static final String DEFAULT_VALUE = "P01";

    private Map<ID, Score> scores;
    private String value;

    /**
     * Creates a {@code AssessmentBuilder} with the default details.
     */
    public AssessmentBuilder() {
        scores = new HashMap<>();
        value = DEFAULT_VALUE;
    }

    /**
     * Initializes the AssessmentBuilder with the data of {@code assessmentToCopy}.
     */
    public AssessmentBuilder(Assessment assessmentToCopy) {
        scores = assessmentToCopy.getScores();
        value = assessmentToCopy.getValue();
    }

    /**
     * Sets the {@code scores} of the {@code assessment} that we are building.
     */
    public AssessmentBuilder withScores(Map<String, String> scores) {
        Map<ID, Score> scoresAdapted = new HashMap<>();
        for (Map.Entry<String, String> score : scores.entrySet()) {
            scoresAdapted.put(new ID(score.getKey()), new Score(score.getValue()));
        }
        this.scores = scoresAdapted;
        return this;
    }

    /**
     * Sets the {@code value} of the {@code assessment} that we are building.
     */
    public AssessmentBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public Assessment build() {
        Assessment assessment = new Assessment(value);
        assessment.getScores().putAll(scores);
        return assessment;
    }
}
