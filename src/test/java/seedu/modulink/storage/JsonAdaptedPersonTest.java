package seedu.modulink.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modulink.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.modulink.testutil.Assert.assertThrows;
import static seedu.modulink.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.modulink.commons.exceptions.IllegalValueException;
import seedu.modulink.model.person.Email;
import seedu.modulink.model.person.GitHubUsername;
import seedu.modulink.model.person.Name;
import seedu.modulink.model.person.Phone;
import seedu.modulink.model.person.StudentId;
import seedu.modulink.model.person.TelegramHandle;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ID = "1234568Z";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GITHUB_USERNAME = "@alex";
    private static final String INVALID_TELEGRAM_HANDLE = "alex!22";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ID = BENSON.getStudentId().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_GITHUB_USERNAME = BENSON.getGithubUsername().toString();
    private static final String VALID_TELEGRAM_HANDLE = BENSON.getTelegramHandle().toString();
    private static final boolean VALID_ISFAVOURITE = BENSON.getIsFavourite();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getMods().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final boolean VALID_ISMYPROFILE = BENSON.getIsMyProfile();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                    VALID_GITHUB_USERNAME, VALID_TELEGRAM_HANDLE, VALID_ISFAVOURITE, VALID_TAGS, VALID_ISMYPROFILE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_ID, VALID_PHONE,
                VALID_EMAIL, VALID_GITHUB_USERNAME, VALID_TELEGRAM_HANDLE,
                VALID_ISFAVOURITE, VALID_TAGS, VALID_ISMYPROFILE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_ID, INVALID_PHONE, VALID_EMAIL,
                        VALID_GITHUB_USERNAME, VALID_TELEGRAM_HANDLE, VALID_ISFAVOURITE, VALID_TAGS, VALID_ISMYPROFILE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ID,
                null, VALID_EMAIL, VALID_GITHUB_USERNAME,
                VALID_TELEGRAM_HANDLE, VALID_ISFAVOURITE, VALID_TAGS, VALID_ISMYPROFILE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_ID, VALID_PHONE, INVALID_EMAIL,
                        VALID_GITHUB_USERNAME, VALID_TELEGRAM_HANDLE, VALID_ISFAVOURITE,
                        VALID_TAGS, VALID_ISMYPROFILE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_ID,
                VALID_PHONE, null, VALID_GITHUB_USERNAME,
                VALID_TELEGRAM_HANDLE, VALID_ISFAVOURITE, VALID_TAGS, VALID_ISMYPROFILE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_ID, VALID_PHONE,
                        VALID_EMAIL, VALID_GITHUB_USERNAME, VALID_TELEGRAM_HANDLE,
                        VALID_ISFAVOURITE, VALID_TAGS, VALID_ISMYPROFILE);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null,
                VALID_PHONE, VALID_EMAIL, VALID_GITHUB_USERNAME,
                VALID_TELEGRAM_HANDLE, VALID_ISFAVOURITE, VALID_TAGS, VALID_ISMYPROFILE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGitHubUsername_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                        INVALID_GITHUB_USERNAME, VALID_TELEGRAM_HANDLE, VALID_ISFAVOURITE,
                        VALID_TAGS, VALID_ISMYPROFILE);
        String expectedMessage = GitHubUsername.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_ID, VALID_PHONE, VALID_EMAIL,
                        VALID_GITHUB_USERNAME, INVALID_TELEGRAM_HANDLE, VALID_ISFAVOURITE,
                        VALID_TAGS, VALID_ISMYPROFILE);
        String expectedMessage = TelegramHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_ID, VALID_PHONE,
                        VALID_EMAIL, VALID_GITHUB_USERNAME,
                        VALID_TELEGRAM_HANDLE, VALID_ISFAVOURITE, invalidTags, VALID_ISMYPROFILE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
