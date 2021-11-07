package seedu.address.storage.guest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.guest.JsonAdaptedGuest.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.guest.TypicalGuests.BENSON_GUEST;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.guest.RoomNumber;
import seedu.address.storage.JsonAdaptedChargeable;
import seedu.address.storage.JsonAdaptedTag;

class JsonAdaptedGuestTest {
    private static final String INVALID_PASSPORT_NUMBER = "@@@@@";
    private static final String INVALID_NAME = "   ";
    private static final String INVALID_ROOM_NUMBER = "@@@@@@";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_PASSPORT_NUMBER = BENSON_GUEST.getPassportNumber().toString();
    private static final String VALID_NAME = BENSON_GUEST.getName().toString();
    private static final String VALID_ROOM_NUMBER = BENSON_GUEST.getRoomNumber().toString();
    private static final String VALID_EMAIL = BENSON_GUEST.getEmail().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON_GUEST.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedChargeable> VALID_CHARGEABLE_USED =
            BENSON_GUEST.getChargeableUsed().stream().map(JsonAdaptedChargeable::new).collect(
                    Collectors.toList());

    @Test
    public void toModelType_validGuestDetails_returnsGuest() throws Exception {
        JsonAdaptedGuest guest = new JsonAdaptedGuest(BENSON_GUEST);
        assertEquals(BENSON_GUEST, guest.toModelType());
    }

    @Test
    public void toModelType_invalidPassportNumber_throwsIllegalValueException() {
        JsonAdaptedGuest guest =
                new JsonAdaptedGuest(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ROOM_NUMBER, INVALID_PASSPORT_NUMBER,
                        VALID_CHARGEABLE_USED);
        String expectedMessage = PassportNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, guest::toModelType);
    }

    @Test
    public void toModelType_nullPassportNumber_throwsIllegalValueException() {
        JsonAdaptedGuest guest =
                new JsonAdaptedGuest(VALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ROOM_NUMBER, null,
                        VALID_CHARGEABLE_USED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PassportNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, guest::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedGuest guest =
                new JsonAdaptedGuest(INVALID_NAME, VALID_EMAIL, VALID_TAGS, VALID_ROOM_NUMBER, VALID_PASSPORT_NUMBER,
                        VALID_CHARGEABLE_USED);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, guest::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedGuest guest = new JsonAdaptedGuest(null, VALID_EMAIL, VALID_TAGS,
                VALID_ROOM_NUMBER, VALID_PASSPORT_NUMBER, VALID_CHARGEABLE_USED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, guest::toModelType);
    }

    @Test
    public void toModelType_invalidRoomNumber_throwsIllegalValueException() {
        JsonAdaptedGuest guest =
                new JsonAdaptedGuest(VALID_NAME, VALID_EMAIL, VALID_TAGS, INVALID_ROOM_NUMBER, VALID_PASSPORT_NUMBER,
                        VALID_CHARGEABLE_USED);
        String expectedMessage = RoomNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, guest::toModelType);
    }

    @Test
    public void toModelType_nullRoomNumber_throwsIllegalValueException() {
        JsonAdaptedGuest guest = new JsonAdaptedGuest(VALID_NAME, VALID_EMAIL, VALID_TAGS, null, VALID_PASSPORT_NUMBER,
                VALID_CHARGEABLE_USED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, guest::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedGuest guest =
                new JsonAdaptedGuest(VALID_NAME, INVALID_EMAIL, VALID_TAGS, VALID_ROOM_NUMBER, VALID_PASSPORT_NUMBER,
                        VALID_CHARGEABLE_USED);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, guest::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedGuest guest = new JsonAdaptedGuest(VALID_NAME, null, VALID_TAGS,
                VALID_ROOM_NUMBER, VALID_PASSPORT_NUMBER, VALID_CHARGEABLE_USED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, guest::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedGuest guest =
                new JsonAdaptedGuest(VALID_NAME, VALID_EMAIL, invalidTags, VALID_ROOM_NUMBER, VALID_PASSPORT_NUMBER,
                        VALID_CHARGEABLE_USED);
        assertThrows(IllegalValueException.class, guest::toModelType);
    }
}
