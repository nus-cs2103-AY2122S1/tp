package seedu.programmer.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.programmer.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.programmer.testutil.Assert.assertThrows;
import static seedu.programmer.testutil.TypicalStudents.BENSON;

import org.junit.jupiter.api.Test;

import seedu.programmer.commons.exceptions.IllegalValueException;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.StudentId;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STUDENT_ID = "+651234";
    private static final String INVALID_CLASS_ID = " ";
    private static final String INVALID_GRADE = " ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_STUDENT_ID = BENSON.getStudentId().toString();
    private static final String VALID_CLASS_ID = BENSON.getClassId().toString();
    private static final String VALID_GRADE = BENSON.getGrade().toString();

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_STUDENT_ID, VALID_CLASS_ID, VALID_GRADE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_STUDENT_ID, VALID_CLASS_ID, VALID_GRADE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_STUDENT_ID, VALID_CLASS_ID, VALID_GRADE);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_CLASS_ID, VALID_GRADE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidClassId_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_STUDENT_ID, INVALID_CLASS_ID, VALID_GRADE);
        String expectedMessage = ClassId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

}
