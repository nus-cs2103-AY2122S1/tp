package seedu.tuitione.model.remark;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void constructor_invalidRemarkName_throwsIllegalArgumentException() {
        String invalidRemarkName = "";
        String remarkMoreThanMaxCharAllowed = "01234567890123456789123456";
        assertThrows(IllegalArgumentException.class, () -> new Remark(invalidRemarkName));
        assertThrows(IllegalArgumentException.class, () -> new Remark(remarkMoreThanMaxCharAllowed));
    }

    @Test
    public void isValidRemarkName() {
        // null remark name
        assertThrows(NullPointerException.class, () -> Remark.isValidRemarkName(null));
        assertTrue(Remark.isValidRemarkName("testone"));
        assertTrue(Remark.isValidRemarkName("0123456789012345678912345"));
        assertTrue(Remark.isValidRemarkName("1"));
        assertFalse(Remark.isValidRemarkName(""));
        assertFalse(Remark.isValidRemarkName("   "));
    }

}
