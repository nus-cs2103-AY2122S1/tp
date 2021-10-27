package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.student.TeleHandle;

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

        // valid tele handles
        assertTrue(TeleHandle.isValidTeleHandle("@AmyLee")); // letters only
        assertTrue(TeleHandle.isValidTeleHandle("@999")); // numbers only
        assertTrue(TeleHandle.isValidTeleHandle("@amylee99")); // combination of numbers and letters
    }
}
