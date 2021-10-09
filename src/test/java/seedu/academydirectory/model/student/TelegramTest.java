package seedu.academydirectory.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        String invalidTelegram = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram));
    }

    @Test
    public void isValidTelegram() {
        // null telegram
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // blank telegram
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only

        // missing parts
        assertFalse(Telegram.isValidTelegram("peterjack")); // missing '@' symbol

        // invalid parts
        assertFalse(Telegram.isValidTelegram("@peter jack")); // spaces in handle
        assertFalse(Telegram.isValidTelegram(" @peterjack")); // leading space
        assertFalse(Telegram.isValidTelegram("@peterjack ")); // trailing space
        assertFalse(Telegram.isValidTelegram("@@peterjack ")); // double '@' symbol
        assertFalse(Telegram.isValidTelegram("@peter@jack")); // '@' symbol in handle
        assertFalse(Telegram.isValidTelegram("@-peterjack")); // handle starts with a hyphen
        assertFalse(Telegram.isValidTelegram("@peterjack-")); // handle ends with a hyphen
        assertFalse(Telegram.isValidTelegram("@peter..jack")); // handle has two consecutive periods

        // valid telegram
        assertTrue(Telegram.isValidTelegram("@PeterJack_1190")); // underscore in handle
        assertTrue(Telegram.isValidTelegram("@PeterJack.1190")); // period in handle
        assertTrue(Telegram.isValidTelegram("@PeterJack+1190")); // '+' symbol in handle
        assertTrue(Telegram.isValidTelegram("@PeterJack-1190")); // hyphen in handle
        assertTrue(Telegram.isValidTelegram("@localhost")); // alphabets only
        assertTrue(Telegram.isValidTelegram("@145")); // numeric handle and domain name
        assertTrue(Telegram.isValidTelegram("@a1+be.d")); // mixture of alphanumeric and special characters
        assertTrue(Telegram.isValidTelegram("@if.you.dream.it_you.can.do.it")); // long handle
    }
}
