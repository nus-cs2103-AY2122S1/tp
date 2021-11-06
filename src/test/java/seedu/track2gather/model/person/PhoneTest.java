package seedu.track2gather.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.track2gather.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.track2gather.model.person.attributes.Phone;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("0")); // zero
        assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits
        assertFalse(Phone.isValidPhone("01123")); // leading zeros
        assertFalse(Phone.isValidPhone("124293831231")); // longer than 11 digits

        // valid phone numbers
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("12429383123")); // exactly 11 numbers
    }

    @Test
    public void isValidPhoneKeyword() {
        // null phone number keyword
        assertThrows(NullPointerException.class, () -> Phone.isValidPhoneKeyword(null));

        // invalid phone number keywords
        assertFalse(Phone.isValidPhoneKeyword("")); // empty string
        assertFalse(Phone.isValidPhoneKeyword(" ")); // spaces only
        assertFalse(Phone.isValidPhoneKeyword("0")); // zero
        assertFalse(Phone.isValidPhoneKeyword("phone")); // non-numeric
        assertFalse(Phone.isValidPhoneKeyword("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhoneKeyword("9312 1534")); // spaces within digits
        assertFalse(Phone.isValidPhoneKeyword("01123")); // leading zeros
        assertFalse(Phone.isValidPhoneKeyword("124293831231")); // longer than 11 digits

        // valid phone number keywords
        assertTrue(Phone.isValidPhoneKeyword("9")); // exactly 1 number
        assertTrue(Phone.isValidPhoneKeyword("93121534"));
        assertTrue(Phone.isValidPhoneKeyword("12429383123")); // exactly 11 numbers
    }
}
