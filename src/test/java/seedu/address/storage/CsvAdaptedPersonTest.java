package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.CsvAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.IsCalled;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

class CsvAdaptedPersonTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GENDER = "APACHE HELICOPTER";
    private static final String INVALID_AGE = "Eighteen";
    private static final String INVALID_ISCALLED = "notCalled";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ISCALLED = BENSON.getIsCalled().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_AGE = BENSON.getAge().toString();
    private static final List<JsonAdaptedInterest> VALID_INTERESTS = BENSON.getInterests().getAllInterests().stream()
            .map(JsonAdaptedInterest::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        CsvAdaptedPerson person = new CsvAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() throws Exception {
        String csvString = INVALID_NAME + ";" + VALID_PHONE + ";" + VALID_EMAIL + ";" + VALID_ADDRESS
                + ";" + VALID_GENDER + ";" + VALID_AGE + ";" + VALID_INTERESTS + ";" + VALID_ISCALLED;
        CsvAdaptedPerson person = new CsvAdaptedPerson(csvString);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_emptyName_throwsIllegalValueException() throws Exception {
        String csvString = ";" + VALID_PHONE + ";" + VALID_EMAIL + ";" + VALID_ADDRESS
                        + ";" + VALID_GENDER + ";" + VALID_AGE + ";" + VALID_INTERESTS + ";" + VALID_ISCALLED;
        CsvAdaptedPerson person = new CsvAdaptedPerson(csvString);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() throws Exception {
        String csvString = VALID_NAME + ";" + INVALID_PHONE + ";" + VALID_EMAIL + ";" + VALID_ADDRESS
                + ";" + VALID_GENDER + ";" + VALID_AGE + ";" + VALID_INTERESTS + ";" + VALID_ISCALLED;
        CsvAdaptedPerson person = new CsvAdaptedPerson(csvString);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_emptyPhone_throwsIllegalValueException() throws Exception {
        String csvString = VALID_NAME + ";;" + VALID_EMAIL + ";" + VALID_ADDRESS
                + ";" + VALID_GENDER + ";" + VALID_AGE + ";" + VALID_INTERESTS + ";" + VALID_ISCALLED;
        CsvAdaptedPerson person = new CsvAdaptedPerson(csvString);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() throws Exception {
        String csvString = VALID_NAME + ";" + VALID_PHONE + ";" + INVALID_EMAIL + ";" + VALID_ADDRESS
                + ";" + VALID_GENDER + ";" + VALID_AGE + ";" + VALID_INTERESTS + ";" + VALID_ISCALLED;
        CsvAdaptedPerson person = new CsvAdaptedPerson(csvString);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_emptyEmail_throwsIllegalValueException() throws Exception {
        String csvString = VALID_NAME + ";" + VALID_PHONE + ";;" + VALID_ADDRESS
                + ";" + VALID_GENDER + ";" + VALID_AGE + ";" + VALID_INTERESTS + ";" + VALID_ISCALLED;
        CsvAdaptedPerson person = new CsvAdaptedPerson(csvString);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() throws Exception {
        String csvString = VALID_NAME + ";" + VALID_PHONE + ";" + VALID_EMAIL + ";" + VALID_ADDRESS
                + ";" + INVALID_GENDER + ";" + VALID_AGE + ";" + VALID_INTERESTS + ";" + VALID_ISCALLED;
        CsvAdaptedPerson person = new CsvAdaptedPerson(csvString);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAge_throwsIllegalValueException() throws Exception {
        String csvString = VALID_NAME + ";" + VALID_PHONE + ";" + VALID_EMAIL + ";" + VALID_ADDRESS
                + ";" + VALID_GENDER + ";" + INVALID_AGE + ";" + VALID_INTERESTS + ";" + VALID_ISCALLED;
        CsvAdaptedPerson person = new CsvAdaptedPerson(csvString);
        String expectedMessage = Age.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidIsCalled_throwsIllegalValueException() throws Exception {
        String csvString = VALID_NAME + ";" + VALID_PHONE + ";" + VALID_EMAIL + ";" + VALID_ADDRESS
                + ";" + VALID_GENDER + ";" + VALID_AGE + ";" + VALID_INTERESTS + ";" + INVALID_ISCALLED;
        CsvAdaptedPerson person = new CsvAdaptedPerson(csvString);
        String expectedMessage = IsCalled.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
