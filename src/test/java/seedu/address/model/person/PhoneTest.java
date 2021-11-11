package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "invalid phone";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Phone.isValidPhone("")); // empty string
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("124293842033123")); // long phone numbers
    }

    @Test
    public void isValidInternationalPhone() {
        assertTrue(Phone.isValidPhone("2055550125"));
        assertTrue(Phone.isValidPhone("202 555 0125"));
        assertTrue(Phone.isValidPhone("(202) 555-0125"));
        assertTrue(Phone.isValidPhone("+111 (202) 555-0125"));
        assertTrue(Phone.isValidPhone("636 856 789"));
        assertTrue(Phone.isValidPhone("+111 636 856 789"));
        assertTrue(Phone.isValidPhone("636 85 67 89"));
        assertTrue(Phone.isValidPhone("+111 636 85 67 89"));
        assertTrue(Phone.isValidPhone("+6501234567"));
    }
}
