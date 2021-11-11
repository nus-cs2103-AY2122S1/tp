package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DateTest {
    private final String tooLong = "0123456789012345678901234567890123456789012345678901234567890123456789"
            + "0123456789012345678901234567890";
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidLabel_throwsIllegalArgumentException() {
        String invalidLabel = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidLabel));
    }

    @Test
    public void isValidLabel() {
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        //invalid dates
        assertFalse(Date.isValidDate(" 2020-08-20")); //starts with a space
        assertFalse(Date.isValidDate("2020- 08-  20")); //multiple spaces

        //valid dates
        assertTrue(Date.isValidDate("2020-08-20"));
    }

}
