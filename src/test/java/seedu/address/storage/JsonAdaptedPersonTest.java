package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CAROL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.common.Name;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void build_withIdObject_constructsWithCorrectId() throws IllegalValueException {
        Id id = Id.parse("0-1");
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(BENSON)
                .withId(id)
                .build();
        assertEquals(Id.parse("0-1"), person.getId());
    }

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(BENSON)
                .build();
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_validPersonDetailsWithNoOptionalAttributes_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(CAROL)
                .build();
        assertEquals(CAROL, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(INVALID_NAME, VALID_PHONE, VALID_EMAIL)
                .withAddress(VALID_ADDRESS)
                .withTagged(VALID_TAGS)
                .build();
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(null, VALID_PHONE, VALID_EMAIL)
                .withAddress(VALID_ADDRESS)
                .withTagged(VALID_TAGS)
                .build();
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(VALID_NAME, INVALID_PHONE, VALID_EMAIL)
                .withAddress(VALID_ADDRESS)
                .withTagged(VALID_TAGS)
                .build();
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(VALID_NAME, null, VALID_EMAIL)
                .withAddress(VALID_ADDRESS)
                .withTagged(VALID_TAGS)
                .build();
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(VALID_NAME, VALID_PHONE, INVALID_EMAIL)
                .withAddress(VALID_ADDRESS)
                .withTagged(VALID_TAGS)
                .build();
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(VALID_NAME, VALID_PHONE, null)
                .withAddress(VALID_ADDRESS)
                .withTagged(VALID_TAGS)
                .build();
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(VALID_NAME, VALID_PHONE, VALID_EMAIL)
                .withAddress(INVALID_ADDRESS)
                .withTagged(VALID_TAGS)
                .build();
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_returnsPerson() throws IllegalValueException {
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(VALID_NAME, VALID_PHONE, VALID_EMAIL)
                .withAddress(null)
                .withTagged(VALID_TAGS)
                .build();
        Person expectedPerson = new Person.Builder(BENSON.getName(), BENSON.getPhone(), BENSON.getEmail())
                .withTags(BENSON.getTags())
                .build();
        assertEquals(expectedPerson, person.toModelType());
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(VALID_NAME, VALID_PHONE, VALID_EMAIL)
                .withAddress(VALID_ADDRESS)
                .withTagged(invalidTags)
                .build();
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullTags_returnsPerson() throws IllegalValueException {
        JsonAdaptedPerson person = new JsonAdaptedPerson.Builder(VALID_NAME, VALID_PHONE, VALID_EMAIL)
                .withAddress(VALID_ADDRESS)
                .withTagged(null)
                .build();
        Person expectedPerson = new Person.Builder(BENSON.getName(), BENSON.getPhone(), BENSON.getEmail())
                .withAddress(BENSON.getAddress())
                .build();
        assertEquals(expectedPerson, person.toModelType());
    }

}
