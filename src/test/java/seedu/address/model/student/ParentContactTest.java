package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ParentContactTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ParentContact(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new ParentContact(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> ParentContact.isValidPhone(null));

        // invalid phone numbers
        assertFalse(ParentContact.isValidPhone("")); // empty string
        assertFalse(ParentContact.isValidPhone(" ")); // spaces only
        assertFalse(ParentContact.isValidPhone("91")); // less than 3 numbers
        assertFalse(ParentContact.isValidPhone("phone")); // non-numeric
        assertFalse(ParentContact.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(ParentContact.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(ParentContact.isValidPhone("911")); // exactly 3 numbers
        assertTrue(ParentContact.isValidPhone("93121534"));
        assertTrue(ParentContact.isValidPhone("124293842033123")); // long phone numbers
    }
}
