package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class LastMetTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastMet(null));
    }

    @Test
    public void constructor_invalidLastMet_throwsIllegalArgumentException() {
        String invalidLastMet = "";
        assertThrows(IllegalArgumentException.class, () -> new LastMet(invalidLastMet));
    }

    @Test
    public void isValidLastMet() {
        // null email
        assertThrows(NullPointerException.class, () -> LastMet.isValidLastMet(null));

        // blank email
        assertFalse(LastMet.isValidLastMet(" ")); // empty string

        // missing parts
        assertFalse(LastMet.isValidLastMet("20-30")); // missing local part

        // invalid parts
        assertFalse(Email.isValidEmail("20-50-5050")); // invalid domain name
        assertFalse(Email.isValidEmail("60-08-2010"));
        assertFalse(Email.isValidEmail("5654-08-12"));

        // valid email
        assertTrue(Email.isValidEmail("01-01-2021"));
        assertTrue(Email.isValidEmail("20-09-2021"));
        assertTrue(Email.isValidEmail("30-12-2021"));
    }
}
