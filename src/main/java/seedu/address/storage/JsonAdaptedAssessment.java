package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.Score;
import seedu.address.model.student.Name;

/**
 * Jackson-friendly version of {@link Assessment}.
 */
public class JsonAdaptedAssessment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assessments's %s field is missing!";

    private final String assessmentName;
    private final String score;

    /**
     * Constructs a {@code JsonAdaptedAssessment} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedAssessment(@JsonProperty("assessmentName") String assessmentName,
                                 @JsonProperty("score") String score) {
        this.assessmentName = assessmentName;
        this.score = score;
    }

    /**
     * Converts a given {@code Assessment} into this class for Jackson use.
     */
    public JsonAdaptedAssessment(Assessment source) {
        assessmentName = source.getAssessmentName().toString();
        score = source.getScore().toString();
    }

    /**
     * Converts this Jackson-friendly adapted assessment object into the model's {@code Assessment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assessment.
     */
    public Assessment toModelType() throws IllegalValueException {
        if (assessmentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AssessmentName.class.getSimpleName()));
        }
        if (!Name.isValidName(assessmentName)) {
            throw new IllegalValueException(AssessmentName.MESSAGE_CONSTRAINTS);
        }
        final AssessmentName modelAssessmentName = new AssessmentName(assessmentName);

        if (score == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Score.class.getSimpleName()));
        }
        final Score modelScore = ParserUtil.parseScore(score);

        return new Assessment(modelAssessmentName, modelScore);
    }
}
