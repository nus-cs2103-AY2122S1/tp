package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedMember.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMembers.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.member.Address;
import seedu.address.model.member.Email;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;

public class JsonAdaptedMemberTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_POSITION = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedPosition> VALID_POSITIONS = BENSON.getPositions().stream()
            .map(JsonAdaptedPosition::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validMemberDetails_returnsMember() throws Exception {
        JsonAdaptedMember member = new JsonAdaptedMember(BENSON);
        assertEquals(BENSON, member.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_POSITIONS);
=======
        JsonAdaptedMember member =
                new JsonAdaptedMember(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
>>>>>>> master:src/test/java/seedu/address/storage/JsonAdaptedMemberTest.java
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_POSITIONS);
=======
        JsonAdaptedMember member = new JsonAdaptedMember(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
>>>>>>> master:src/test/java/seedu/address/storage/JsonAdaptedMemberTest.java
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_POSITIONS);
=======
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
>>>>>>> master:src/test/java/seedu/address/storage/JsonAdaptedMemberTest.java
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_POSITIONS);
=======
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
>>>>>>> master:src/test/java/seedu/address/storage/JsonAdaptedMemberTest.java
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_POSITIONS);
=======
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
>>>>>>> master:src/test/java/seedu/address/storage/JsonAdaptedMemberTest.java
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_POSITIONS);
=======
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS);
>>>>>>> master:src/test/java/seedu/address/storage/JsonAdaptedMemberTest.java
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_POSITIONS);
=======
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS);
>>>>>>> master:src/test/java/seedu/address/storage/JsonAdaptedMemberTest.java
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_POSITIONS);
=======
        JsonAdaptedMember member = new JsonAdaptedMember(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS);
>>>>>>> master:src/test/java/seedu/address/storage/JsonAdaptedMemberTest.java
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
    public void toModelType_invalidPositions_throwsIllegalValueException() {
        List<JsonAdaptedPosition> invalidPositions = new ArrayList<>(VALID_POSITIONS);
        invalidPositions.add(new JsonAdaptedPosition(INVALID_POSITION));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidPositions);
        assertThrows(IllegalValueException.class, person::toModelType);
=======
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags);
        assertThrows(IllegalValueException.class, member::toModelType);
>>>>>>> master:src/test/java/seedu/address/storage/JsonAdaptedMemberTest.java
    }

}
