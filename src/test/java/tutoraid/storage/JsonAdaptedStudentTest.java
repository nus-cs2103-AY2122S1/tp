package tutoraid.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tutoraid.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.model.student.Name;
import tutoraid.model.student.Phone;
import tutoraid.testutil.Assert;
import tutoraid.testutil.TypicalPersons;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";

    private static final String VALID_STUDENT_NAME = TypicalPersons.BENSON.getStudentName().toString();
    private static final String VALID_STUDENT_PHONE = TypicalPersons.BENSON.getStudentPhone().toString();
    private static final String VALID_PARENT_NAME = TypicalPersons.BENSON.getParentName().toString();
    private static final String VALID_PARENT_PHONE = TypicalPersons.BENSON.getParentPhone().toString();
    private static final String VALID_PROGRESS = TypicalPersons.BENSON.getProgress().toString();
    private static final boolean VALID_PAYMENT_STATUS = TypicalPersons.BENSON.getPaymentStatus().hasPaid;

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStudent person = new JsonAdaptedStudent(TypicalPersons.BENSON);
        assertEquals(TypicalPersons.BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidStudentName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(INVALID_NAME, VALID_STUDENT_PHONE,
                        VALID_PARENT_NAME, VALID_PARENT_PHONE,
                        VALID_PROGRESS, VALID_PAYMENT_STATUS);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStudentName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(null, VALID_STUDENT_PHONE,
                VALID_PARENT_NAME, VALID_PARENT_PHONE,
                VALID_PROGRESS, VALID_PAYMENT_STATUS);

        String expectedMessage = String.format(JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidStudentPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_STUDENT_NAME, INVALID_PHONE,
                VALID_PARENT_NAME, VALID_PARENT_PHONE,
                VALID_PROGRESS, VALID_PAYMENT_STATUS);

        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidParentName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_STUDENT_NAME, VALID_STUDENT_PHONE,
                INVALID_NAME, VALID_PARENT_PHONE,
                VALID_PROGRESS, VALID_PAYMENT_STATUS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidParentPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_STUDENT_NAME, INVALID_PHONE,
                VALID_PARENT_NAME, INVALID_PHONE,
                VALID_PROGRESS, VALID_PAYMENT_STATUS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
