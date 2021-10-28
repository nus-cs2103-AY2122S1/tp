package seedu.edrecord.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edrecord.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InfoTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Info(null));
    }

    @Test
    public void constructor_invalidInfo_throwsIllegalArgumentException() {
        String invalidInfo = " ";
        assertThrows(IllegalArgumentException.class, () -> new Info(invalidInfo));
    }

    @Test
    public void isValidInfo() {
        // invalid info
        assertFalse(Info.isValidInfo(null)); // null string only
        assertFalse(Info.isValidInfo("  ")); // spaces only

        // valid info
        assertTrue(Info.isValidInfo("")); // empty string
        assertTrue(Info.isValidInfo("A hardworking student"));
        assertTrue(Info.isValidInfo("-")); // one character
        assertTrue(Info.isValidInfo("This student is very hardworking, and is projected to get FCH")); // long info
    }
}
