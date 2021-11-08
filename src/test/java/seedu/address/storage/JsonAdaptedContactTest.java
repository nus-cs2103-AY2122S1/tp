package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedContact.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.BATTLEBOX;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;
import seedu.address.model.contact.Rating;

public class JsonAdaptedContactTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_RATING = "6";

    private static final String VALID_CATEGORY_CODE = BATTLEBOX.getCategoryCode().toString();
    private static final String VALID_NAME = BATTLEBOX.getName().toString();
    private static final String VALID_PHONE = BATTLEBOX.getPhone().toString();
    private static final String VALID_EMAIL = BATTLEBOX.getEmail().toString();
    private static final String VALID_REVIEW = BATTLEBOX.getReview().toString();
    private static final String VALID_ADDRESS = BATTLEBOX.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BATTLEBOX.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_RATING = BATTLEBOX.getRating().toString();

    //TODO(HK): add invalid test cases for Category Codes
    @Test
    public void toModelType_validContactDetails_returnsContact() throws Exception {
        JsonAdaptedContact contact = new JsonAdaptedContact(BATTLEBOX);
        assertEquals(BATTLEBOX, contact.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_CATEGORY_CODE, INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REVIEW, VALID_TAGS, VALID_RATING);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_CATEGORY_CODE, null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_REVIEW, VALID_TAGS, VALID_RATING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_CATEGORY_CODE, VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REVIEW, VALID_TAGS, VALID_RATING);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_CATEGORY_CODE, VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_REVIEW, VALID_TAGS, VALID_RATING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_CATEGORY_CODE, VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_REVIEW, VALID_TAGS, VALID_RATING);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_CATEGORY_CODE, VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_REVIEW, VALID_TAGS, VALID_RATING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_CATEGORY_CODE, VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_REVIEW, VALID_TAGS, VALID_RATING);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_CATEGORY_CODE, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_REVIEW, VALID_TAGS, VALID_RATING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_CATEGORY_CODE, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REVIEW, invalidTags, VALID_RATING);
        assertThrows(IllegalValueException.class, contact::toModelType);
    }

    @Test
    public void toModelType_invalidRating_throwsIllegalValueException() {
        JsonAdaptedContact contact =
            new JsonAdaptedContact(VALID_CATEGORY_CODE, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_REVIEW, VALID_TAGS, INVALID_RATING);
        String expectedMessage = Rating.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullRating_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_CATEGORY_CODE, VALID_NAME, VALID_PHONE, VALID_EMAIL,
            VALID_ADDRESS, VALID_REVIEW, VALID_TAGS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

}
