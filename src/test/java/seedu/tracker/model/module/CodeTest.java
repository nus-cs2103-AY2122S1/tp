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
        // null code
        assertThrows(NullPointerException.class, () -> Code.isValidCode(null));

        // invalid code
        assertFalse(Code.isValidCode("")); // empty string
        assertFalse(Code.isValidCode(" ")); // spaces only
        assertFalse(Code.isValidCode("^")); // only non-alphanumeric characters
        assertFalse(Code.isValidCode("C1101S")); // only 1 starting letter
        assertFalse(Code.isValidCode("CS110S")); // only 3 digits
        assertFalse(Code.isValidCode("CS1100SSS")); // 3 ending letters


        // valid code
        assertTrue(Code.isValidCode("CS1101"));
        assertTrue(Code.isValidCode("CS1101S"));
        assertTrue(Code.isValidCode("CSI1101S"));
        assertTrue(Code.isValidCode("CSI1101SS"));
    }
}
