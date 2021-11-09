package seedu.address.model.module.student;

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
        String invalidTeleHandle = "";
        assertThrows(IllegalArgumentException.class, () -> new TeleHandle(invalidTeleHandle));
    }

    @Test
    public void isValidTeleHandle() {
        // null tele handle
        assertThrows(NullPointerException.class, () -> TeleHandle.isValidTeleHandle(null));

        // invalid tele handles
        assertFalse(TeleHandle.isValidTeleHandle("")); // empty string
        assertFalse(TeleHandle.isValidTeleHandle(" ")); // spaces only
        assertFalse(TeleHandle.isValidTeleHandle("@")); // @ only
        assertFalse(TeleHandle.isValidTeleHandle("amy")); // no @
        assertFalse(TeleHandle.isValidTeleHandle("a")); // no@
        assertFalse(TeleHandle.isValidTeleHandle("@amy lee ")); // there is a space separating the name
        assertFalse(TeleHandle.isValidTeleHandle("@amy'lee ")); // special character is used
        assertFalse(TeleHandle.isValidTeleHandle("@amy")); // less than 5 characters
        assertFalse(TeleHandle.isValidTeleHandle("@amyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy")); // more than 32 characters

        // valid tele handles
        assertTrue(TeleHandle.isValidTeleHandle("@_____")); // underscores only
        assertTrue(TeleHandle.isValidTeleHandle("@AmyLee")); // letters only
        assertTrue(TeleHandle.isValidTeleHandle("@999970")); // numbers only
        assertTrue(TeleHandle.isValidTeleHandle("@amylee99")); // combination of numbers and letters
        assertTrue(TeleHandle.isValidTeleHandle("@___99")); // combination of numbers and underscores
        assertTrue(TeleHandle.isValidTeleHandle("@amy_lee_")); // combination of underscores and letters
        assertTrue(TeleHandle.isValidTeleHandle("@amylee99__")); // combination of numbers, letters and underscores
    }
}
