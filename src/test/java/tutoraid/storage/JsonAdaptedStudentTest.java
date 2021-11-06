package tutoraid.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.model.student.Name;
import tutoraid.model.student.Phone;
import tutoraid.testutil.Assert;
import tutoraid.testutil.TypicalStudents;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";

    private static final String VALID_STUDENT_NAME = TypicalStudents.BENSON.getStudentName().toString();
    private static final String VALID_STUDENT_PHONE = TypicalStudents.BENSON.getStudentPhone().toString();
    private static final String VALID_PARENT_NAME = TypicalStudents.BENSON.getParentName().toString();
    private static final String VALID_PARENT_PHONE = TypicalStudents.BENSON.getParentPhone().toString();
    private static final ArrayList<String> VALID_PROGRESS_LIST =
            TypicalStudents.BENSON.getProgressList().getAllProgressAsStringArrayList();
    private static final ArrayList<String> VALID_LESSONS =
            TypicalStudents.BENSON.getLessons().getAllLessonNamesAsStringArrayList();

    @Test
    public void toModelType_validStudentDetails_returnsInitialStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(TypicalStudents.BENSON);
        assertEquals(TypicalStudents.INITIAL_BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidStudentName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(INVALID_NAME, VALID_STUDENT_PHONE,
                        VALID_PARENT_NAME, VALID_PARENT_PHONE,
                        VALID_PROGRESS_LIST, VALID_LESSONS);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullStudentName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_STUDENT_PHONE,
                VALID_PARENT_NAME, VALID_PARENT_PHONE,
                VALID_PROGRESS_LIST, VALID_LESSONS);

        String expectedMessage = String.format(
                JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidStudentPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_NAME, INVALID_PHONE,
                VALID_PARENT_NAME, VALID_PARENT_PHONE,
                VALID_PROGRESS_LIST, VALID_LESSONS);

        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidParentName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_NAME, VALID_STUDENT_PHONE,
                INVALID_NAME, VALID_PARENT_PHONE,
                VALID_PROGRESS_LIST, VALID_LESSONS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidParentPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_NAME, INVALID_PHONE,
                VALID_PARENT_NAME, INVALID_PHONE,
                VALID_PROGRESS_LIST, VALID_LESSONS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }
}
