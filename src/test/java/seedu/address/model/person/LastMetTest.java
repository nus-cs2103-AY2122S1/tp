package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.StringUtil.isValidDate;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LastMetTest {

    @Test
    public void constructor_invalidLastMet_throwsIllegalArgumentException() {
        String invalidLastMet = "hello";
        assertThrows(IllegalArgumentException.class, () -> new LastMet(invalidLastMet));
    }

    @Test
    public void isValidLastMet() {
        // null email
        assertThrows(NullPointerException.class, () -> isValidDate(null));

        // blank email
        assertFalse(isValidDate(" ")); // empty string

        // missing parts
        assertFalse(isValidDate("20-30")); // missing local part

        // invalid parts
        assertFalse(isValidDate("20-50-5050")); // invalid domain name
        assertFalse(isValidDate("60-08-2010"));
        assertFalse(isValidDate("5654-08-12"));

        // valid email
        assertTrue(isValidDate("20-12-2021"));
        assertTrue(isValidDate("20-09-2021"));
        assertTrue(isValidDate("30-12-2021"));
    }
}
