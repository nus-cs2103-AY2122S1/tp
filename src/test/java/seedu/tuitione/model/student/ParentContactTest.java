package seedu.tuitione.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.testutil.Assert.assertThrows;

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
        assertFalse(ParentContact.isValidPhone("1")); // PH with not exactly 8 characters
        assertFalse(ParentContact.isValidPhone("12"));
        assertFalse(ParentContact.isValidPhone("123"));
        assertFalse(ParentContact.isValidPhone("1234"));
        assertFalse(ParentContact.isValidPhone("12345"));
        assertFalse(ParentContact.isValidPhone("123456"));
        assertFalse(ParentContact.isValidPhone("1234567"));
        assertFalse(ParentContact.isValidPhone("123456789"));
        assertFalse(ParentContact.isValidPhone("phone")); // non-numeric
        assertFalse(ParentContact.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(ParentContact.isValidPhone("9312 1534")); // spaces within digits
        assertFalse(ParentContact.isValidPhone("02345678")); //PH that starts with 0
        assertFalse(ParentContact.isValidPhone("12345678")); //PH that starts with 1
        assertFalse(ParentContact.isValidPhone("22345678")); //PH that starts with 2
        assertFalse(ParentContact.isValidPhone("32345678")); //PH that starts with 3
        assertFalse(ParentContact.isValidPhone("42345678")); //PH that starts with 4
        assertFalse(ParentContact.isValidPhone("52345678")); //PH that starts with 5
        assertFalse(ParentContact.isValidPhone("72345678")); //PH that starts with 7

        // valid phone numbers
        assertTrue(ParentContact.isValidPhone("67777777")); //PH that starts with 6 and is 8 digits long
        assertTrue(ParentContact.isValidPhone("83121534")); //PH that starts with 8 and is 8 digits long
        assertTrue(ParentContact.isValidPhone("98765432")); //PH that starts with 9 and is 8 digits long
    }

    @Test
    public void isValidEquals() {
        ParentContact test1 = new ParentContact("91234567");
        ParentContact copyTest1 = new ParentContact("91234567");
        ParentContact test2 = new ParentContact("91234568");
        assertTrue(test1.equals(test1));
        assertTrue(test1.equals(copyTest1));
        assertFalse(test1.equals(test2));
    }
}
