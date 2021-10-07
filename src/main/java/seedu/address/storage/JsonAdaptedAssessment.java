package seedu.address.storage;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.ID;
import seedu.address.model.student.Score;

public class JsonAdaptedAssessment {
    private final String assessmentName;
    private final Map<String, String> scores;

    /**
     * Constructs a {@code JsonAdaptedAssessment} with the given {@code assessmentName}.
     */
    @JsonCreator
    public JsonAdaptedAssessment(String assessmentName, Map<String, String> scores) {
        this.assessmentName = assessmentName;
        this.scores = scores;
    }

    /**
     * Converts a given {@code Assessment} into this class for Jackson use.
     */
    public JsonAdaptedAssessment(Assessment source) {
        assessmentName = source.value;
        scores = new HashMap<>();
        for (ID id : source.scores.keySet()) {
            scores.put(id.value, source.scores.get(id).value);
        }
    }

    @JsonValue
    public String getAssessmentName() {
        return assessmentName;
    }

    @JsonValue
    public Map<String, String> getScores() {
        return scores;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Assessment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assessment.
     */
    public Assessment toModelType() throws IllegalValueException {
        if (!Assessment.isValidAssessment(assessmentName)) {
            throw new IllegalValueException(Assessment.MESSAGE_CONSTRAINTS);
        }

        Assessment assessment = new Assessment(assessmentName);
        for (String id : scores.keySet()) {
            assessment.scores.put(new ID(id), new Score(scores.get(id)));
        }
        return assessment;
    }
}
