//package seedu.address.storage;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static seedu.address.storage.JsonAdaptedstudent.MISSING_FIELD_MESSAGE_FORMAT;
//import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.Typicalstudents.BENSON;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.commons.exceptions.IllegalValueException;
//import seedu.address.model.student.ClassId;
//import seedu.address.model.student.Name;
//import seedu.address.model.student.StudentId;
//
//public class JsonAdaptedstudentTest {
//    private static final String INVALID_NAME = "R@chel";
//    private static final String INVALID_STUDENTID = "+651234";
//    private static final String INVALID_CLASSID = " ";
//    private static final String INVALID_GRADE = " ";
//
//    private static final String VALID_NAME = BENSON.getName().toString();
//    private static final String VALID_STUDENTID = BENSON.getStudentId().toString();
//    private static final String VALID_CLASSID = BENSON.getClassId().toString();
//    private static final String VALID_GRADE = BENSON.getGrade().toString();
//
//    @Test
//    public void toModelType_validstudentDetails_returnsstudent() throws Exception {
//        JsonAdaptedstudent student = new JsonAdaptedstudent(BENSON);
//        assertEquals(BENSON, student.toModelType());
//    }
//
//    @Test
//    public void toModelType_invalidName_throwsIllegalValueException() {
//        JsonAdaptedstudent student =
//                new JsonAdaptedstudent(INVALID_NAME, VALID_STUDENTID, VALID_CLASSID, VALID_GRADE);
//        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
//        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
//    }
//
//    @Test
//    public void toModelType_nullName_throwsIllegalValueException() {
//        JsonAdaptedstudent student = new JsonAdaptedstudent(null, VALID_STUDENTID, VALID_CLASSID, VALID_GRADE);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
//        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
//    }
//
//    @Test
//    public void toModelType_invalidStudentId_throwsIllegalValueException() {
//        JsonAdaptedstudent student =
//                new JsonAdaptedstudent(VALID_NAME, INVALID_STUDENTID, VALID_CLASSID, VALID_GRADE);
//        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
//        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
//    }
//
//    @Test
//    public void toModelType_nullStudentId_throwsIllegalValueException() {
//        JsonAdaptedstudent student = new JsonAdaptedstudent(VALID_NAME, null, VALID_CLASSID, VALID_GRADE);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
//        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
//    }
//
//    @Test
//    public void toModelType_invalidClassId_throwsIllegalValueException() {
//        JsonAdaptedstudent student =
//                new JsonAdaptedstudent(VALID_NAME, VALID_STUDENTID, INVALID_CLASSID, VALID_GRADE);
//        String expectedMessage = ClassId.MESSAGE_CONSTRAINTS;
//        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
//    }
//
//}
