package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.scene.image.Image;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TELEGRAM = "c4nnot!!!";
    private static final String INVALID_GITHUB = "a--b";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_TELEGRAM = BENSON.getTelegram().toString();
    private static final String VALID_GITHUB = BENSON.getGithub().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final HashMap<String, Double> VALID_STATS = new HashMap<>();
    private static final Image VALID_IMAGE = new Image(JsonAdaptedPersonTest.class
            .getResourceAsStream("/images/profile.png"));
    private static final boolean NOT_IS_FAVORITE = false;

    @Test
    public void isDefaultImage_defaultImage_returnsTrue() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertTrue(person.isDefaultImage(new Image(getClass().getResourceAsStream("/images/profile.png"))));
    }

    @Test
    public void isDefaultImage_randomImage_returnsFalse() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertFalse(person.isDefaultImage(new Image(getClass().getResourceAsStream("/images/logo.png"))));
    }

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, NOT_IS_FAVORITE, VALID_STATS, VALID_IMAGE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, NOT_IS_FAVORITE, VALID_STATS, VALID_IMAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTelegram_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, NOT_IS_FAVORITE, VALID_STATS, VALID_IMAGE);
        String expectedMessage = Telegram.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTelegram_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_GITHUB, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, NOT_IS_FAVORITE, VALID_STATS, VALID_IMAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Telegram.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGithub_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAM, INVALID_GITHUB, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, NOT_IS_FAVORITE, VALID_STATS, VALID_IMAGE);
        String expectedMessage = Github.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGithub_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAM, null, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, NOT_IS_FAVORITE, VALID_STATS, VALID_IMAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Github.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAM, VALID_GITHUB, INVALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, NOT_IS_FAVORITE, VALID_STATS, VALID_IMAGE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAM, VALID_GITHUB, null,
                VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, NOT_IS_FAVORITE, VALID_STATS, VALID_IMAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                        INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS, NOT_IS_FAVORITE, VALID_STATS, VALID_IMAGE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                null, VALID_ADDRESS, VALID_TAGS, NOT_IS_FAVORITE, VALID_STATS, VALID_IMAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                VALID_EMAIL, null, VALID_TAGS, NOT_IS_FAVORITE, VALID_STATS, VALID_IMAGE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TELEGRAM, VALID_GITHUB, VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, invalidTags, NOT_IS_FAVORITE, VALID_STATS, VALID_IMAGE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
