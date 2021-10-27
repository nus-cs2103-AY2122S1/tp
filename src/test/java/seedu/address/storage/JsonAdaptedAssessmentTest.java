package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedAssessment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssessments.MIDTERMS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.Score;


public class JsonAdaptedAssessmentTest {
    private static final String INVALID_ASSESSMENT_NAME = "Fin@ls";
    private static final String INVALID_SCORE = "101/100";

    private static final String VALID_ASSESSMENT_NAME = MIDTERMS.getAssessmentName().toString();
    private static final String VALID_SCORE = MIDTERMS.getScore().toString();

    @Test
    public void toModelType_invalidAssessmentName_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment =
                new JsonAdaptedAssessment(INVALID_ASSESSMENT_NAME, VALID_SCORE);
        String expectedMessage = AssessmentName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> assessment.toModelType());
    }

    @Test
    public void toModelType_nullAssessmentName_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(null, VALID_SCORE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                AssessmentName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> assessment.toModelType());
    }

    @Test
    public void toModelType_invalidScore_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment =
                new JsonAdaptedAssessment(VALID_ASSESSMENT_NAME, INVALID_SCORE);
        String expectedMessage = Score.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> assessment.toModelType());
    }

    @Test
    public void toModelType_nullScore_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(VALID_ASSESSMENT_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Score.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> assessment.toModelType());
    }

}
