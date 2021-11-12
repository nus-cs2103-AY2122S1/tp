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
    public void constructor_emptyString_success() {
        String emptyPhone = "";
        boolean success = false;

        try {
            Phone phone = new Phone(emptyPhone);
            success = true;
        } catch (Exception e) {
            // Test Case Failed
            success = false;
        }
        assertTrue(success);
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

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
    public void toString_aValidInput_success() {
        String phoneNumber = "12345678";
        Phone phone = new Phone(phoneNumber);
        assertTrue(phoneNumber.equals(phone.toString()));
    }

    @Test
    public void equals_twoSameObjects_success() {
        String phoneNumber = "12345678";
        Phone phone = new Phone(phoneNumber);
        assertTrue(phone.equals(phone));
    }

    @Test
    public void equals_twoDifferentObjects_falseOutput() {
        Phone phone = new Phone("12345678");
        Email email = new Email("jay@gmail.com");
        assertFalse(phone.equals(email));
    }

    @Test
    public void equals_twoDifferentObjectsWithSameNumber_success() {
        String phoneNumber = "12345678";
        Phone phone1 = new Phone(phoneNumber);
        Phone phone2 = new Phone(phoneNumber);
        assertTrue(phone1.equals(phone2));
    }

    @Test
    public void equals_twoDifferentObjectsWithDifferentNumber_falseOutput() {
        String phoneNumber1 = "12345678";
        String phoneNumber2 = "87654321";
        Phone phone1 = new Phone(phoneNumber1);
        Phone phone2 = new Phone(phoneNumber2);
        assertFalse(phone1.equals(phone2));
    }

    @Test
    public void hashCode_validInput_correctOutput() {
        String phoneNumber = "12345678";
        Phone phone = new Phone(phoneNumber);
        assertTrue(phone.hashCode() == -1861353340);
    }
}
