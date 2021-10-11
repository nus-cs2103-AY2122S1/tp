package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";

    private static final String VALID_STUDENT_NAME = BENSON.getStudentName().toString();
    private static final String VALID_STUDENT_PHONE = BENSON.getStudentPhone().toString();
    private static final String VALID_PARENT_NAME = BENSON.getParentName().toString();
    private static final String VALID_PARENT_PHONE = BENSON.getParentPhone().toString();
    private static final String VALID_PROGRESS = BENSON.getProgress().toString();
    private static final boolean VALID_PAYMENT_STATUS = BENSON.getPaymentStatus().hasPaid;

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidStudentName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_STUDENT_PHONE,
                        VALID_PARENT_NAME, VALID_PARENT_PHONE,
                        VALID_PROGRESS, VALID_PAYMENT_STATUS);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStudentName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_STUDENT_PHONE,
                VALID_PARENT_NAME, VALID_PARENT_PHONE,
                VALID_PROGRESS, VALID_PAYMENT_STATUS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidStudentPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_STUDENT_NAME, INVALID_PHONE,
                VALID_PARENT_NAME, VALID_PARENT_PHONE,
                VALID_PROGRESS, VALID_PAYMENT_STATUS);

        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidParentName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_STUDENT_NAME, VALID_STUDENT_PHONE,
                INVALID_NAME, VALID_PARENT_PHONE,
                VALID_PROGRESS, VALID_PAYMENT_STATUS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidParentPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_STUDENT_NAME, INVALID_PHONE,
                VALID_PARENT_NAME, INVALID_PHONE,
                VALID_PROGRESS, VALID_PAYMENT_STATUS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
