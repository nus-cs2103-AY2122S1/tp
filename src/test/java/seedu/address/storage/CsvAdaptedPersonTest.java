package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.CsvAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BENSON_ADDRESS;
import static seedu.address.testutil.TypicalPersons.BENSON_EMAIL;
import static seedu.address.testutil.TypicalPersons.BENSON_GITHUB;
import static seedu.address.testutil.TypicalPersons.BENSON_NAME;
import static seedu.address.testutil.TypicalPersons.BENSON_PHONE;
import static seedu.address.testutil.TypicalPersons.BENSON_TAG_1;
import static seedu.address.testutil.TypicalPersons.BENSON_TAG_2;
import static seedu.address.testutil.TypicalPersons.BENSON_TELEGRAM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;

/**
 * A class for testing {@link CsvAdaptedPerson}.
 */
public class CsvAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TELEGRAM = "c4nnot!!!";
    private static final String INVALID_GITHUB = "a--b";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON_NAME;
    private static final String VALID_TELEGRAM = BENSON_TELEGRAM;
    private static final String VALID_GITHUB = BENSON_GITHUB;
    private static final String VALID_PHONE = BENSON_PHONE;
    private static final String VALID_EMAIL = BENSON_EMAIL;
    private static final String VALID_ADDRESS = BENSON_ADDRESS;
    private static final String VALID_TAGS = String.join(" ", BENSON_TAG_1, BENSON_TAG_2);

    /**
     * Successful conversion from {@code CsvAdaptedPerson} to {@code Person}.
     */
    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        CsvAdaptedPerson person = new CsvAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    /**
     * Conversion from {@code CsvAdaptedPerson} to {@code Person} fails when there is an invalid name.
     */
    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        CsvAdaptedPerson person =
                new CsvAdaptedPerson(INVALID_NAME, VALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /**
     * Conversion from {@code CsvAdaptedPerson} to {@code Person} fails when there is a null name.
     */
    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(null, VALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /**
     * Conversion from {@code CsvAdaptedPerson} to {@code Person} fails when there is an invalid Telegram username.
     */
    @Test
    public void toModelType_invalidTelegram_throwsIllegalValueException() {
        CsvAdaptedPerson person =
                new CsvAdaptedPerson(VALID_NAME, INVALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Telegram.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /**
     * Conversion from {@code CsvAdaptedPerson} to {@code Person} fails when there is a null Telegram username.
     */
    @Test
    public void toModelType_nullTelegram_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(
                VALID_NAME, null, VALID_GITHUB, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Telegram.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /**
     * Conversion from {@code CsvAdaptedPerson} to {@code Person} fails when there is an invalid GitHub username.
     */
    @Test
    public void toModelType_invalidGithub_throwsIllegalValueException() {
        CsvAdaptedPerson person =
                new CsvAdaptedPerson(VALID_NAME, VALID_TELEGRAM, INVALID_GITHUB, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Github.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /**
     * Conversion from {@code CsvAdaptedPerson} to {@code Person} fails when there is a null GitHub username.
     */
    @Test
    public void toModelType_nullGithub_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, VALID_TELEGRAM, null, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Github.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /**
     * Conversion from {@code CsvAdaptedPerson} to {@code Person} fails when there is an invalid phone number.
     */
    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        CsvAdaptedPerson person =
                new CsvAdaptedPerson(VALID_NAME, VALID_TELEGRAM, VALID_GITHUB, INVALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /**
     * Conversion from {@code CsvAdaptedPerson} to {@code Person} fails when there is a null phone number.
     */
    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, VALID_TELEGRAM, VALID_GITHUB, null,
                VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /**
     * Conversion from {@code CsvAdaptedPerson} to {@code Person} fails when there is an invalid email.
     */
    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        CsvAdaptedPerson person =
                new CsvAdaptedPerson(VALID_NAME, VALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                        INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /**
     * Conversion from {@code CsvAdaptedPerson} to {@code Person} fails when there is a null email.
     */
    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, VALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                null, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /**
     * Conversion from {@code CsvAdaptedPerson} to {@code Person} fails when there is a null address.
     */
    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        CsvAdaptedPerson person = new CsvAdaptedPerson(VALID_NAME, VALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                VALID_EMAIL, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /**
     * Conversion from {@code CsvAdaptedPerson} to {@code Person} fails when there are invalid tags present.
     */
    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        String invalidTags = VALID_TAGS + " " + INVALID_TAG;
        CsvAdaptedPerson person =
                new CsvAdaptedPerson(VALID_NAME, VALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
