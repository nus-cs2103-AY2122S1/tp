package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthday_throwsIllegalArgumentException() {
        String invalidBirthday = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthday));
    }

    @Test
    public void isValidBirthday() {
        // null address
        assertThrows(NullPointerException.class, () -> Birthday.isValidFormat(null));

        // blank birthday
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // invalid format
        assertFalse(Birthday.isValidFormat("31-12-2011")); // invalid format
        assertFalse(Birthday.isValidFormat("2021-11-12")); // invalid format
        assertFalse(Birthday.isValidDate("12122012 ")); // white space at end
        assertFalse(Birthday.isValidDate("12122012 ")); // white space at the start

        // invalid dates
        assertFalse(Birthday.isValidDate("32022011")); // invalid date
        assertFalse(Birthday.isValidDate("12345678")); // invalid date
        assertFalse(Birthday.isValidDate("00000000")); // invalid date

        // valid birthday
        assertTrue(Birthday.isValidFormat("19011999")); // valid date
        assertTrue(Birthday.isValidFormat("31121999")); // 31st December 1999
        assertTrue(Birthday.isValidFormat("29022012")); // 29th February leap year
    }
}
