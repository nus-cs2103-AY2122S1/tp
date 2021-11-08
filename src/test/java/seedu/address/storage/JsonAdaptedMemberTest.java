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
import seedu.address.model.Timestamp;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Id;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;

/**
 * Tests the functionalities of
 * {@code JsonAdaptedMember}.
 */
public class JsonAdaptedMemberTest {
    private static final String INVALID_ID = "3A001";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_REGISTRATION_TIMESTAMP = "00001&234";
    private static final String INVALID_CREDIT = "*99";
    private static final String INVALID_POINT = "*99";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_ID = BENSON.getId().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_REGISTRATION_TIMESTAMP = BENSON.getTimestamp().toString();
    private static final String VALID_CREDIT = BENSON.getCredit().toString();
    private static final String VALID_POINT = BENSON.getPoint().toString();
    private static final List<JsonAdaptedTransaction> VALID_TRANSACTIONS = BENSON.getTransactions().stream()
            .map(JsonAdaptedTransaction::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedReservation> VALID_RESERVATIONS = BENSON.getReservations().stream()
            .map(JsonAdaptedReservation::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validMemberDetails_returnsMember() throws Exception {
        JsonAdaptedMember member = new JsonAdaptedMember(BENSON);
        assertEquals(BENSON, member.toModelType());
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(INVALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(null, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidTimestamp_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = Timestamp.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullTimestamp_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        null, VALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = String.format(
                MISSING_FIELD_MESSAGE_FORMAT, Timestamp.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidCredit_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, INVALID_CREDIT, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);

        String expectedMessage = Credit.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullCredit_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, null, VALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);

        String expectedMessage = String.format(
                MISSING_FIELD_MESSAGE_FORMAT, Credit.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidPoint_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, INVALID_POINT, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = Point.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_nullPoint_throwsIllegalValueException() {
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, null, VALID_TRANSACTIONS,
                        VALID_RESERVATIONS, VALID_TAGS);
        String expectedMessage = String.format(
                MISSING_FIELD_MESSAGE_FORMAT, Point.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, member::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedMember member =
                new JsonAdaptedMember(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_REGISTRATION_TIMESTAMP, VALID_CREDIT, VALID_POINT,
                        VALID_TRANSACTIONS, VALID_RESERVATIONS, invalidTags);
        assertThrows(IllegalValueException.class, member::toModelType);
    }

}
