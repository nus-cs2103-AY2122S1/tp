package seedu.address.model.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoomNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoomNumber(null));
    }

    @Test
    public void constructor_invalidRoomNumber_throwsIllegalArgumentException() {
        String invalidRoomNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new RoomNumber(invalidRoomNumber));
    }

    @Test
    public void isValidRoomNumber() {
        // null room number
        assertThrows(NullPointerException.class, () -> RoomNumber.isValidRoomNumber(null));

        // invalid room number
        assertFalse(RoomNumber.isValidRoomNumber("")); // empty string
        assertFalse(RoomNumber.isValidRoomNumber(" ")); // spaces only
        assertFalse(RoomNumber.isValidRoomNumber("^")); // only non-alphanumeric characters
        assertFalse(RoomNumber.isValidRoomNumber("peter*")); // contains non-alphanumeric characters
        assertFalse(RoomNumber.isValidRoomNumber("-32131")); // negative numbers
        assertFalse(RoomNumber.isValidRoomNumber("2312D")); // contains letter
        assertFalse(RoomNumber.isValidRoomNumber("000")); // 0

        // valid room number
        assertTrue(RoomNumber.isValidRoomNumber("2312312312")); // number greater than 0
    }

}
