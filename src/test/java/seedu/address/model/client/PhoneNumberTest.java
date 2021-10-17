package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PhoneNumber(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(invalidPhone));
    }

    @Test
    public void isValidPhoneNumber() {
        // null phone number
        assertThrows(NullPointerException.class, () -> PhoneNumber.isValidPhoneNumber(null));

        // invalid phone numbers
        assertFalse(PhoneNumber.isValidPhoneNumber("")); // empty string
        assertFalse(PhoneNumber.isValidPhoneNumber(" ")); // spaces only
        assertFalse(PhoneNumber.isValidPhoneNumber("phone")); // non-numeric
        assertFalse(PhoneNumber.isValidPhoneNumber("9011p041")); // alphabets within digits
        assertFalse(PhoneNumber.isValidPhoneNumber("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(PhoneNumber.isValidPhoneNumber("91")); // short phone numbers
        assertTrue(PhoneNumber.isValidPhoneNumber("911")); // short phone numbers
        assertTrue(PhoneNumber.isValidPhoneNumber("93121534"));
        assertTrue(PhoneNumber.isValidPhoneNumber("124293842033123")); // long phone numbers
    }
}
