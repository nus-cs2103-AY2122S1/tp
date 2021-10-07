package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STUDENTID = "+651234";
    private static final String INVALID_CLASSID = " ";
    private static final String INVALID_GRADE = " ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_STUDENTID = BENSON.getStudentId().toString();
    private static final String VALID_CLASSID = BENSON.getClassId().toString();
    private static final String VALID_GRADE = BENSON.getGrade().toString();

    //todo
//    @Test
//    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
//        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
//        assertEquals(BENSON, person.toModelType());
//    }
//
//    @Test
//    public void toModelType_invalidName_throwsIllegalValueException() {
//        JsonAdaptedPerson person =
//                new JsonAdaptedPerson(INVALID_NAME, VALID_STUDENTID, VALID_CLASSID, VALID_GRADE);
//        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
//        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
//    }
//
//    @Test
//    public void toModelType_nullName_throwsIllegalValueException() {
//        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_STUDENTID, VALID_CLASSID, VALID_GRADE);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
//        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
//    }
//
//    @Test
//    public void toModelType_invalidPhone_throwsIllegalValueException() {
//        JsonAdaptedPerson person =
//                new JsonAdaptedPerson(VALID_NAME, INVALID_STUDENTID, VALID_CLASSID, VALID_GRADE);
//        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
//        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
//    }
//
//    @Test
//    public void toModelType_nullPhone_throwsIllegalValueException() {
//        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_CLASSID, VALID_GRADE);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
//        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
//    }
//
//    @Test
//    public void toModelType_invalidEmail_throwsIllegalValueException() {
//        JsonAdaptedPerson person =
//                new JsonAdaptedPerson(VALID_NAME, VALID_STUDENTID, INVALID_CLASSID, VALID_GRADE);
//        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
//        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
//    }

}
