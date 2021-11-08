package seedu.edrecord.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.edrecord.storage.JsonAdaptedGrade.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.edrecord.testutil.Assert.assertThrows;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.edrecord.commons.exceptions.IllegalValueException;
import seedu.edrecord.model.assignment.Grade;
import seedu.edrecord.model.assignment.Score;

public class JsonAdaptedGradeTest {
    private static final Grade VALID_GRADE = new Grade(Optional.of(new Score("50")), Grade.GradeStatus.GRADED);

    @Test
    public void toModelType_validDetails_success() throws Exception {
        JsonAdaptedGrade grade = new JsonAdaptedGrade(1, VALID_GRADE);
        assertEquals(grade.toModelType(), Map.entry(1, VALID_GRADE));
    }

    //=========== ID =========================================================================================

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedGrade grade = new JsonAdaptedGrade(0, VALID_GRADE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "ID");
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    //=========== Status =====================================================================================

    @Test
    public void toModelType_validStatus_success() throws Exception {
        // not submitted
        JsonAdaptedGrade gradeNotSubmitted = new JsonAdaptedGrade("NOT_SUBMITTED", "null", 1);
        Grade expectedGradeNotSubmitted = new Grade(Optional.empty(), Grade.GradeStatus.NOT_SUBMITTED);
        assertEquals(new JsonAdaptedGrade(1, expectedGradeNotSubmitted).toModelType(), gradeNotSubmitted.toModelType());

        // submitted
        JsonAdaptedGrade gradeSubmitted = new JsonAdaptedGrade("SUBMITTED", "null", 1);
        Grade expectedGradeSubmitted = new Grade(Optional.empty(), Grade.GradeStatus.SUBMITTED);
        assertEquals(new JsonAdaptedGrade(1, expectedGradeSubmitted).toModelType(), gradeSubmitted.toModelType());

        // graded
        JsonAdaptedGrade gradeGraded = new JsonAdaptedGrade("GRADED", "10", 1);
        Grade expectedGradeGraded = new Grade(Optional.of(new Score("10")), Grade.GradeStatus.GRADED);
        assertEquals(new JsonAdaptedGrade(1, expectedGradeGraded).toModelType(), gradeGraded.toModelType());
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedGrade grade = new JsonAdaptedGrade(null, "10", 1);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.GradeStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedGrade grade = new JsonAdaptedGrade("not submitted", "10", 1);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.GradeStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    //=========== Score ======================================================================================

    @Test
    public void toModelType_nullScore_throwsIllegalValueException() {
        JsonAdaptedGrade grade = new JsonAdaptedGrade("GRADED", null, 1);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Score.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_invalidScore_throwsIllegalValueException() {
        JsonAdaptedGrade grade = new JsonAdaptedGrade("GRADED", "-10", 1);
        String expectedMessage = Score.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }

    @Test
    public void toModelType_statusScoreMismatch_throwsIllegalValueException() {
        JsonAdaptedGrade grade = new JsonAdaptedGrade("SUBMITTED", "10", 1);
        String expectedMessage = Grade.MESSAGE_STATUS_SCORE_MISMATCH;
        assertThrows(IllegalValueException.class, expectedMessage, grade::toModelType);
    }
}
