package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.SocialHandle;
import seedu.address.model.person.TutorialGroup;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_GENDER = "N";
    private static final String INVALID_PHONE = "@651234";
    private static final String INVALID_NATIONALITY = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TUTORIAL_GROUP = "A";
    private static final String INVALID_SOCIAL_HANDLE = "with space";
    private static final String INVALID_REMARK = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_NATIONALITY = BENSON.getNationality().toString();
    private static final String VALID_TUTORIAL_GROUP = BENSON.getTutorialGroup().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedSocialHandle> VALID_SOCIAL_HANDLES = BENSON.getSocialHandles().stream()
            .map(JsonAdaptedSocialHandle::new)
            .collect(Collectors.toList());

    private static final String VALID_TAG_COLOUR = "#00FFFF";

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NATIONALITY,
                        VALID_TUTORIAL_GROUP, VALID_GENDER, VALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                null, VALID_PHONE, VALID_EMAIL, VALID_NATIONALITY, VALID_TUTORIAL_GROUP,
                VALID_GENDER, VALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_NATIONALITY,
                        VALID_TUTORIAL_GROUP, VALID_GENDER, VALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, null, VALID_EMAIL, VALID_NATIONALITY, VALID_TUTORIAL_GROUP,
                VALID_GENDER, VALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_NATIONALITY,
                        VALID_TUTORIAL_GROUP, VALID_GENDER, VALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, null, VALID_NATIONALITY, VALID_TUTORIAL_GROUP,
                VALID_GENDER, VALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNationality_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_NATIONALITY,
                        VALID_TUTORIAL_GROUP, VALID_GENDER, VALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = Nationality.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNationality_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TUTORIAL_GROUP,
                VALID_GENDER, VALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nationality.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTutorialGroup_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NATIONALITY,
                        INVALID_TUTORIAL_GROUP, VALID_GENDER, VALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = TutorialGroup.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTutorialGroup_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NATIONALITY, null,
                VALID_GENDER, VALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TutorialGroup.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NATIONALITY,
                        VALID_TUTORIAL_GROUP, INVALID_GENDER, VALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NATIONALITY, VALID_TUTORIAL_GROUP,
                null, VALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRemark_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NATIONALITY,
                        VALID_TUTORIAL_GROUP, VALID_GENDER, INVALID_REMARK, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = Remark.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NATIONALITY, VALID_TUTORIAL_GROUP,
                VALID_GENDER, null, VALID_TAGS, VALID_SOCIAL_HANDLES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG, VALID_TAG_COLOUR));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NATIONALITY,
                        VALID_TUTORIAL_GROUP, VALID_GENDER, VALID_REMARK, invalidTags, VALID_SOCIAL_HANDLES);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidSocialHandles_throwsIllegalValueException() {
        List<JsonAdaptedSocialHandle> invalidSocialHandles = new ArrayList<>(VALID_SOCIAL_HANDLES);
        invalidSocialHandles.add(new JsonAdaptedSocialHandle(INVALID_SOCIAL_HANDLE));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_NATIONALITY,
                        VALID_TUTORIAL_GROUP, VALID_GENDER, VALID_REMARK, VALID_TAGS,
                        invalidSocialHandles);
        String expectedMessage = SocialHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
