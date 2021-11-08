package seedu.tuitione.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tuitione.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.tuitione.testutil.Assert.assertThrows;
import static seedu.tuitione.testutil.TypicalStudents.GEORGE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.tuitione.commons.exceptions.IllegalValueException;
import seedu.tuitione.model.student.Address;
import seedu.tuitione.model.student.Email;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.ParentContact;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GRADE = "F9";
    private static final String INVALID_CHAR_REMARK = "#friend";
    private static final String INVALID_LENGTH_REMARK = "aaaaaaaaaaaaaaaaaaaaaaaaaa";
    private static final String VALID_REMARK = "mock";

    private static final String VALID_NAME = GEORGE.getName().toString();
    private static final String VALID_PHONE = GEORGE.getParentContact().toString();
    private static final String VALID_EMAIL = GEORGE.getEmail().toString();
    private static final String VALID_ADDRESS = GEORGE.getAddress().toString();
    private static final String VALID_GRADE = GEORGE.getGrade().toString();
    private static final List<JsonAdaptedRemark> VALID_REMARKS = GEORGE.getRemarks().stream()
            .map(JsonAdaptedRemark::new)
            .collect(Collectors.toList());
    private static final List<String> VALID_LESSON_CODES = new ArrayList<>();

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws IllegalValueException {
        JsonAdaptedStudent student = new JsonAdaptedStudent(GEORGE);
        assertEquals(GEORGE, student.toModelType());
    }

    @Test
    public void toModelType_tooManyRemarks_returnsStudentWithFirstFewRemarks() throws IllegalValueException {
        List<JsonAdaptedRemark> extraRemarks = new ArrayList<>(VALID_REMARKS); // already has 5
        extraRemarks.add(new JsonAdaptedRemark(VALID_REMARK)); // hit past max
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GRADE,
                        extraRemarks, VALID_LESSON_CODES);
        // extra remarks are ignored in model creation
        assertEquals(GEORGE, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_GRADE, VALID_REMARKS, VALID_LESSON_CODES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_GRADE, VALID_REMARKS, VALID_LESSON_CODES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GRADE,
                        VALID_REMARKS, VALID_LESSON_CODES);
        String expectedMessage = ParentContact.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_GRADE, VALID_REMARKS, VALID_LESSON_CODES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ParentContact.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_GRADE, VALID_REMARKS, VALID_LESSON_CODES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_GRADE, VALID_REMARKS, VALID_LESSON_CODES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_GRADE, VALID_REMARKS, VALID_LESSON_CODES);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_GRADE, VALID_REMARKS, VALID_LESSON_CODES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidGrade_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_GRADE, VALID_REMARKS, VALID_LESSON_CODES);
        String expectedMessage = Grade.GRADE_MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullGrade_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_REMARKS, VALID_LESSON_CODES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidRemarks_throwsIllegalValueException() {
        List<JsonAdaptedRemark> invalidRemarks = new ArrayList<>(VALID_REMARKS);

        // invalid characters
        invalidRemarks.set(0, new JsonAdaptedRemark(INVALID_CHAR_REMARK));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GRADE,
                        invalidRemarks, VALID_LESSON_CODES);
        assertThrows(IllegalValueException.class, student::toModelType);

        // exceeds length
        invalidRemarks.set(0, new JsonAdaptedRemark(INVALID_LENGTH_REMARK));
        JsonAdaptedStudent otherStudent =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GRADE,
                        invalidRemarks, VALID_LESSON_CODES);
        assertThrows(IllegalValueException.class, otherStudent::toModelType);
    }
}
