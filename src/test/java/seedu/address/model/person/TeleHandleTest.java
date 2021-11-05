package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TeleHandleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TeleHandle(null));
    }

    @Test
    public void constructor_invalidTeleHandle_throwsIllegalArgumentException() {
        String invalidTeleHandle = "not a telegram handle";
        assertThrows(IllegalArgumentException.class, () -> new TeleHandle(invalidTeleHandle));
    }

    @Test
    public void isValidTeleHandle() {
        // null telegram handle
        assertThrows(NullPointerException.class, () -> TeleHandle.isValidTeleHandle(null));

        // invalid telegram handle
        assertFalse(TeleHandle.isValidTeleHandle("mytelehandle")); // does not start with @
        assertFalse(TeleHandle.isValidTeleHandle("@tele")); // less than 5 characters
        assertFalse(TeleHandle.isValidTeleHandle("@my tele handle")); // contains spaces

        // valid telegram handle
        assertTrue(TeleHandle.isValidTeleHandle("@mytelehandle")); // alphabets only
        assertTrue(TeleHandle.isValidTeleHandle("@james")); // 5 characters
        assertTrue(TeleHandle.isValidTeleHandle("@my_tele_handle")); // with underscores
        assertTrue(TeleHandle.isValidTeleHandle("@myTeleHandle")); // with capital letters
        assertTrue(TeleHandle.isValidTeleHandle("@mytelehandle99")); // with numbers
    }
}
