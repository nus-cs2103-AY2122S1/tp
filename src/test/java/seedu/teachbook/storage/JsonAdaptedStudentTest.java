package seedu.teachbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.teachbook.storage.JsonAdaptedStudent.ILLEGAL_FIELD_MESSAGE_FORMAT;
import static seedu.teachbook.testutil.Assert.assertThrows;
import static seedu.teachbook.testutil.TypicalStudents.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.teachbook.commons.exceptions.IllegalValueException;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.student.Address;
import seedu.teachbook.model.student.Attendance;
import seedu.teachbook.model.student.Email;
import seedu.teachbook.model.student.Name;
import seedu.teachbook.model.student.Phone;
import seedu.teachbook.model.student.Remark;
import seedu.teachbook.model.student.Student;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TAG = "999999999999999999999999999999999999999999999"
            + "00000000000000000000000000000000000000000000000000000000000000000000000000000000000"
            + "1111111111111111111111111111111111111111111111111111111111111111111111111111111111";
    //tag now accepts special character but must be < 30 characters

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_ATTENDANCE = BENSON.getAttendance().toString();
    private static final String VALID_GRADE = "";
    private static final String VALID_CLASSNAME = "A";

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        Class testClass = new Class(new ClassName(VALID_CLASSNAME));
        Student testStudent = new Student(BENSON);
        JsonAdaptedStudent person = new JsonAdaptedStudent(testStudent);
        Student convertedStudent = person.toModelType();
        convertedStudent.setStudentClass(testClass);
        assertEquals(testStudent, convertedStudent);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK, VALID_TAGS, VALID_ATTENDANCE, VALID_GRADE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK, VALID_TAGS, VALID_ATTENDANCE, VALID_GRADE);
        String expectedMessage = String.format(ILLEGAL_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK, VALID_TAGS, VALID_ATTENDANCE, VALID_GRADE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK, VALID_TAGS, VALID_ATTENDANCE, VALID_GRADE);
        String expectedMessage = String.format(ILLEGAL_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK, VALID_TAGS, VALID_ATTENDANCE, VALID_GRADE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_REMARK, VALID_TAGS, VALID_ATTENDANCE, VALID_GRADE);
        String expectedMessage = String.format(ILLEGAL_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_REMARK, VALID_TAGS, VALID_ATTENDANCE, VALID_GRADE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_REMARK, VALID_TAGS, VALID_ATTENDANCE, VALID_GRADE);
        String expectedMessage = String.format(ILLEGAL_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, null, VALID_TAGS, VALID_ATTENDANCE, VALID_GRADE);
        String expectedMessage = String.format(ILLEGAL_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK, invalidTags, VALID_ATTENDANCE, VALID_GRADE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullAttendance_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_REMARK, VALID_TAGS, null, VALID_GRADE);
        String expectedMessage = String.format(ILLEGAL_FIELD_MESSAGE_FORMAT, Attendance.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
