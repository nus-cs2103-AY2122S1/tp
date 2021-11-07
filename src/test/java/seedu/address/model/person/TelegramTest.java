package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        // null phone number
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid phone numbers
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("john")); // less than 5 character
        assertFalse(Telegram.isValidTelegram("john!!")); // invalid characters
        assertFalse(Telegram.isValidTelegram("John doe")); // spaces within telegram handle

        // valid phone numbers
        assertTrue(Telegram.isValidTelegram("john_doe_")); // lower case alphabet and underscore
        assertTrue(Telegram.isValidTelegram("DavidCopper")); // upper and lower case alphabets
        assertTrue(Telegram.isValidTelegram("12345678")); // all number
        assertTrue(Telegram.isValidTelegram("John_doe_123")); // mix of all possible characters
    }

    @Test
    public void equals_twoSameObjects_success() {
        Telegram telegram = new Telegram("Jai2501");
        assertTrue(telegram.equals(telegram));
    }

    @Test
    public void equals_twoDifferentObjects_falseOutput() {
        Telegram telegram = new Telegram("Jai2501");
        Email email = new Email("jay@gmail.com");
        assertFalse(telegram.equals(email));
    }

    @Test
    public void equals_twoDifferentObjectsWithSameTelegramHandles_success() {
        Telegram telegram1 = new Telegram("Jai2501");
        Telegram telegram2 = new Telegram("Jai2501");
        assertTrue(telegram1.equals(telegram2));
    }

    @Test
    public void equals_twoDifferentObjectsWithDifferentTelegramHandles_falseOutput() {
        Telegram telegram1 = new Telegram("Jai2501");
        Telegram telegram2 = new Telegram("Atin1234");
        assertFalse(telegram1.equals(telegram2));
    }

    @Test
    public void toString_aValidInput_success() {
        Telegram telegram = new Telegram("Jai2501");
        assertTrue(telegram.toString().equals("Jai2501"));
    }

    @Test
    public void hashCode_validInput_correctOutput() {
        Telegram telegram = new Telegram("Jai2501");
        assertTrue(telegram.hashCode() == -168664970);
    }
}
