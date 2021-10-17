package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.CaseNumber;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.ShnPeriod;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_CASE_NUMBER = "-2";
    private static final String INVALID_HOME_ADDRESS = " ";
    private static final String INVALID_WORK_ADDRESS = " ";
    private static final String INVALID_QUARANTINE_ADDRESS = " ";
    private static final String INVALID_SHN_PERIOD = "12 May - 15 May 2020";
    private static final String INVALID_NEXT_OF_KIN_NAME = "J-hn";
    private static final String INVALID_NEXT_OF_KIN_PHONE = "+526243";
    private static final String INVALID_NEXT_OF_KIN_ADDRESS = " ";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_CASE_NUMBER = BENSON.getCaseNumber().toString();
    private static final String VALID_HOME_ADDRESS = BENSON.getHomeAddress().toString();
    private static final String VALID_WORK_ADDRESS = BENSON.getWorkAddress().map(Object::toString).orElse(null);
    private static final String VALID_QUARANTINE_ADDRESS =
            BENSON.getQuarantineAddress().map(Object::toString).orElse(null);
    private static final String VALID_SHN_PERIOD = BENSON.getShnPeriod().map(Object::toString).orElse(null);
    private static final String VALID_NEXT_OF_KIN_NAME = BENSON.getNextOfKinName().map(Object::toString).orElse(null);
    private static final String VALID_NEXT_OF_KIN_PHONE = BENSON.getNextOfKinPhone().map(Object::toString).orElse(null);
    private static final String VALID_NEXT_OF_KIN_ADDRESS =
            BENSON.getNextOfKinAddress().map(Object::toString).orElse(null);

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidCaseNumber_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = CaseNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullCaseNumber_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CaseNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidHomeAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_CASE_NUMBER,
                INVALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullHomeAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_CASE_NUMBER,
                null, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidWorkAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, INVALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidQuarantineAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, INVALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidShnPeriod_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, INVALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = ShnPeriod.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNextOfKinName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                INVALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNextOfKinPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, INVALID_NEXT_OF_KIN_PHONE, VALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNextOfKinAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_CASE_NUMBER,
                VALID_HOME_ADDRESS, VALID_WORK_ADDRESS, VALID_QUARANTINE_ADDRESS, VALID_SHN_PERIOD,
                VALID_NEXT_OF_KIN_NAME, VALID_NEXT_OF_KIN_PHONE, INVALID_NEXT_OF_KIN_ADDRESS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
