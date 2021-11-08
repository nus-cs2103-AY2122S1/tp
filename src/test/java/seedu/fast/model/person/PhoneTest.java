package seedu.fast.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.testutil.Assert.assertThrows;

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
        assertFalse(Phone.isValidPhone(LengthUtil.EMPTY_STRING)); // empty string
        assertFalse(Phone.isValidPhone(LengthUtil.WHITE_SPACE_STRING)); // spaces only
        assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits
        assertFalse(Phone.isValidPhone("123123123123123123123")); // more than 20 numbers

        // valid phone numbers
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("124293842033123")); // long phone numbers (15 numbers)
        assertTrue(Phone.isValidPhone("12312312312312312312")); // exactly 20 numbers
    }

    @Test
    public void hashcode() {
        Phone standard = new Phone("93121534");
        Phone phoneWithSameValue = new Phone("93121534");
        Phone phoneWithDifferentValue = new Phone("911");

        assertTrue(standard.hashCode() == phoneWithSameValue.hashCode());
        assertTrue(standard.hashCode() == standard.hashCode());

        assertFalse(standard.hashCode() == phoneWithDifferentValue.hashCode());
    }
}
