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
        assertFalse(Phone.isValidPhone("91")); // less than 8 numbers
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits
        assertFalse(Phone.isValidPhone("124293842033123")); // long phone numbers

        // valid phone numbers
        assertTrue(Phone.isValidPhone("67654321")); // exactly 8 numbers beginning with 6
        assertTrue(Phone.isValidPhone("87654321")); // exactly 8 numbers beginning with 8
        assertTrue(Phone.isValidPhone("93121534")); // exactly 8 numbers beginning with 9
    }

    @Test
    public void equals() {
        Phone phone = new Phone("98765432");

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // same values -> returns true
        Phone remarkCopy = new Phone("98765432");
        assertTrue(phone.equals(remarkCopy));

        // different types -> returns false
        assertFalse(phone.equals(1));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different remark -> returns false
        Phone differentPhone = new Phone("62353535");
        assertFalse(phone.equals(differentPhone));
    }
}
