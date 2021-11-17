package socialite.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import socialite.testutil.Assert;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        Assert.assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("124293842033123")); // long phone numbers
    }

    @Test
    public void testToString() {
        String expectedString = "12345678";
        Phone phone = new Phone("12345678");
        assertEquals(expectedString, phone.toString());
    }

    @Test
    public void testEquals() {
        Phone phone = new Phone("12345678");
        Phone differentPhone = new Phone("87654321");
        assertEquals(phone, phone);
        assertEquals(phone, new Phone("12345678"));
        assertNotEquals(phone, differentPhone);
    }

    @Test
    public void testHashCode() {
        Phone phone = new Phone("12345678");
        Phone differentPhone = new Phone("87654321");

        assertEquals(phone.hashCode(), new Phone("12345678").hashCode());
        assertNotEquals(phone.hashCode(), differentPhone.hashCode());
    }
}
