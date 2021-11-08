package seedu.edrecord.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.edrecord.storage.JsonAdaptedAssignment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.edrecord.testutil.Assert.assertThrows;
import static seedu.edrecord.testutil.TypicalAssignments.MIDTERM;

import org.junit.jupiter.api.Test;

import seedu.edrecord.commons.exceptions.IllegalValueException;
import seedu.edrecord.model.assignment.Score;
import seedu.edrecord.model.assignment.Weightage;
import seedu.edrecord.model.name.Name;

public class JsonAdaptedAssignmentTest {

    private static final String INVALID_NAME = "A$$ignment";
    private static final String INVALID_WEIGHTAGE = "150";
    private static final String INVALID_MAX_SCORE = "-3.5";

    private static final String VALID_NAME = MIDTERM.getName().name;
    private static final String VALID_WEIGHTAGE = String.valueOf(MIDTERM.getWeightage().weightage);
    private static final String VALID_MAX_SCORE = String.valueOf(MIDTERM.getMaxScore().score);
    private static final int VALID_ID = MIDTERM.getId();

    @Test
    public void toModelType_validAssignmentDetails_returnsAssignment() throws Exception {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(MIDTERM);
        assertEquals(MIDTERM.isSameAssignment(assignment.toModelType()), true);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(INVALID_NAME, VALID_WEIGHTAGE,
                VALID_MAX_SCORE, VALID_ID);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(null, VALID_WEIGHTAGE,
                VALID_MAX_SCORE, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_invalidWeightage_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_NAME, INVALID_WEIGHTAGE,
                VALID_MAX_SCORE, VALID_ID);
        String expectedMessage = Weightage.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullWeightage_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_NAME, null,
                VALID_MAX_SCORE, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weightage.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_invalidMaxScore_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_NAME, VALID_WEIGHTAGE,
                INVALID_MAX_SCORE, VALID_ID);
        String expectedMessage = Score.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

    @Test
    public void toModelType_nullMaxScore_throwsIllegalValueException() {
        JsonAdaptedAssignment assignment = new JsonAdaptedAssignment(VALID_NAME, VALID_WEIGHTAGE,
                null, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Score.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, assignment::toModelType);
    }

}
