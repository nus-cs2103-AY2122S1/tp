package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LinkYearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LinkYear(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "w4!";
        assertThrows(IllegalArgumentException.class, () -> new LinkYear(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> LinkYear.isValidYear(null));

        // invalid name
        assertFalse(LinkYear.isValidYear("")); // empty string
        assertFalse(LinkYear.isValidYear(" ")); // space only
        assertFalse(LinkYear.isValidYear("may 2016")); // contains space
        assertFalse(LinkYear.isValidYear("^")); // only non-alphanumeric characters
        assertFalse(LinkYear.isValidYear("20*20")); // contains non-alphanumeric characters


        // valid name
        assertTrue(LinkYear.isValidYear("ay_20-21.21~22")); // all no
    }
}
