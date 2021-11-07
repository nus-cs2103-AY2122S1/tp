package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.STUDENT_MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.AMY;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.student.Email;
import seedu.address.model.module.student.Name;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.TeleHandle;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STUDENT_ID = "123";
    private static final String INVALID_TELE_HANDLE = "noLeadingCharacter";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_NAME = AMY.getName().toString();
    private static final String VALID_STUDENT_ID = AMY.getStudentId().toString();
    private static final String VALID_TELE_HANDLE = AMY.getTeleHandle().toString();
    private static final String VALID_EMAIL = AMY.getEmail().toString();
    private static final List<JsonAdaptedTask> VALID_TASK_LIST = AMY.getTaskList()
            .asUnmodifiableObservableList()
            .stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(AMY);
        assertEquals(AMY, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_TELE_HANDLE, VALID_EMAIL, VALID_STUDENT_ID, VALID_TASK_LIST);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(null, VALID_TELE_HANDLE, VALID_EMAIL, VALID_STUDENT_ID, VALID_TASK_LIST);
        String expectedMessage = String.format(STUDENT_MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_TELE_HANDLE, VALID_EMAIL, INVALID_STUDENT_ID, VALID_TASK_LIST);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_TELE_HANDLE, VALID_EMAIL, null, VALID_TASK_LIST);
        String expectedMessage = String.format(STUDENT_MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTeleHandle_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_TELE_HANDLE, VALID_EMAIL, VALID_STUDENT_ID, VALID_TASK_LIST);
        String expectedMessage = TeleHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullTeleHandle_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, null, VALID_EMAIL, VALID_STUDENT_ID, VALID_TASK_LIST);
        String expectedMessage = String.format(STUDENT_MISSING_FIELD_MESSAGE_FORMAT, TeleHandle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_TELE_HANDLE, INVALID_EMAIL, VALID_STUDENT_ID, VALID_TASK_LIST);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_TELE_HANDLE, null, VALID_STUDENT_ID, VALID_TASK_LIST);
        String expectedMessage = String.format(STUDENT_MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }
}
