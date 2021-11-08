package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String invalidPhone = "hello";
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
        assertFalse(Phone.isValidPhone("931214214124124215251058125301851515315811534")); // exceed limit >30 char

        // valid phone numbers
        assertTrue(Phone.isValidPhone("")); // empty string
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("124293842033123")); // long phone numbers <= 30 char
    }

    @Test
    public void equals() {
        Phone john = new Phone("93121534");
        Phone otherJohn = new Phone("93121534");
        Phone jane = new Phone("911");

        // same object
        assertTrue(john.equals(john));

        // different object same Phone
        assertTrue(john.equals(otherJohn));
        assertEquals(john.hashCode(), otherJohn.hashCode());

        // different object different Phone
        assertFalse(john.equals(jane));

        // different type
        assertFalse(john.equals("93121534"));

    }
}
