package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // invalid birthday
        assertFalse(Birthday.isValidBirthday("")); // empty string
        assertFalse(Birthday.isValidBirthday(" ")); // spaces only
        // TODO: 7/10/21 implement error message for birthdates 
//        assertFalse(Birthday.isValidBirthday("2020-19-30")); // empty string
//        assertFalse(Birthday.isValidBirthday("2020-02-30")); // empty string

        // valid birthday
        assertTrue(Birthday.isValidBirthday("1999-01-19"));
    }
}
