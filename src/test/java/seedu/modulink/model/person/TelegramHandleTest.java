package seedu.modulink.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulink.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramHandleTest {

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTelegramHandle = "";
        assertThrows(IllegalArgumentException.class, () -> new TelegramHandle(invalidTelegramHandle));
    }

    @Test
    public void isValidTelegramHandle() {
        // invalid Telegram handle
        assertFalse(TelegramHandle.isValidHandle("")); // empty string
        assertFalse(TelegramHandle.isValidHandle(" ")); // spaces only
        assertFalse(TelegramHandle.isValidHandle("^")); // only non-alphanumeric characters
        assertFalse(TelegramHandle.isValidHandle("@peter!23")); // contains non-alphanumeric characters
        assertFalse(TelegramHandle.isValidHandle("@peter@23")); // contains '@' after first character
        assertFalse(TelegramHandle.isValidHandle("alex yeoh")); // contains spaces

        // valid Telegram handle
        assertTrue(TelegramHandle.isValidHandle("peterjack")); // alphabets only
        assertTrue(TelegramHandle.isValidHandle("12345")); // numbers only
        assertTrue(TelegramHandle.isValidHandle("peterthe2nd")); // alphanumeric characters
        assertTrue(TelegramHandle.isValidHandle("CapitalTan")); // with capital letters
        assertTrue(TelegramHandle.isValidHandle("alexa_tan_test")); // with hyphens
        assertTrue(TelegramHandle.isValidHandle(null)); // null telegram handle
    }
}
