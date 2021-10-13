package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("@u.nus.edu")); // missing local part
        assertFalse(Email.isValidEmail("e1234567u.nus.edu")); // missing '@' symbol
        assertFalse(Email.isValidEmail("e1234567@")); // missing domain name

        // invalid parts
        assertFalse(Email.isValidEmail("e1234567@unusedu")); // invalid domain name
        assertFalse(Email.isValidEmail("e123456@u.nus.edu")); // invalid local part

        // valid email
        assertTrue(Email.isValidEmail("e1234567@u.nus.edu"));
        assertTrue(Email.isValidEmail("E1234567@u.nus.edu"));
    }

    @Test
    public void isEqualEmail() {
        Email email = new Email("e1234567@u.nus.edu");
        Email different_email = new Email("E7654321@u.nus.edu");
        Email same_email = new Email("e1234567@u.nus.edu");

        // Different Email
        assertFalse(email.equals(different_email));

        // Same Object
        assertTrue(email.equals(email));

        // Different Objects Same Email
        assertTrue(email.equals(same_email));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(VALID_EMAIL_AMY.hashCode(), VALID_EMAIL_AMY.hashCode());
        assertNotEquals(VALID_EMAIL_AMY.hashCode(), VALID_EMAIL_BOB.hashCode());
    }
}
