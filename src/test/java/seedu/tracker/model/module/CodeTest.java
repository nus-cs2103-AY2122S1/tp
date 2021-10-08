package seedu.tracker.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Code(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidCode = "";
        assertThrows(IllegalArgumentException.class, () -> new Code(invalidCode));
    }

    @Test
    public void isValidCode() {
        // null name
        assertThrows(NullPointerException.class, () -> Code.isValidCode(null));

        // invalid name
        assertFalse(Code.isValidCode("")); // empty string
        assertFalse(Code.isValidCode(" ")); // spaces only
        assertFalse(Code.isValidCode("^")); // only non-alphanumeric characters
        assertFalse(Code.isValidCode("CS1101*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Code.isValidCode("cs")); // alphabets only
        assertTrue(Code.isValidCode("12345")); // numbers only
        assertTrue(Code.isValidCode("cs1101s")); // alphanumeric characters
        assertTrue(Code.isValidCode("CS1101s")); // with capital letters
        assertTrue(Code.isValidCode("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
