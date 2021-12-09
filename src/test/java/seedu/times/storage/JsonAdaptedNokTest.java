package seedu.times.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.times.storage.JsonAdaptedNok.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.times.testutil.Assert.assertThrows;
import static seedu.times.testutil.TypicalTimestable.BENSON;

import org.junit.jupiter.api.Test;

import seedu.times.commons.exceptions.IllegalValueException;
import seedu.times.model.person.Address;
import seedu.times.model.person.Email;
import seedu.times.model.person.Name;
import seedu.times.model.person.Nok;
import seedu.times.model.person.Phone;

public class JsonAdaptedNokTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final Nok VALID_NOK = BENSON.getNok();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedNok nok = new JsonAdaptedNok(VALID_NOK);
        assertEquals(VALID_NOK, nok.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedNok nok = new JsonAdaptedNok(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, nok::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedNok nok = new JsonAdaptedNok(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, nok::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedNok nok = new JsonAdaptedNok(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, nok::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedNok nok = new JsonAdaptedNok(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, nok::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedNok nok = new JsonAdaptedNok(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, nok::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedNok nok = new JsonAdaptedNok(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, nok::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedNok nok = new JsonAdaptedNok(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, nok::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedNok nok = new JsonAdaptedNok(VALID_NAME, VALID_PHONE, VALID_EMAIL, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, nok::toModelType);
    }

}
