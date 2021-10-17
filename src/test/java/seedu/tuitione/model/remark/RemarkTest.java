package seedu.tuitione.model.remark;

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
        assertThrows(IllegalArgumentException.class, () -> new Remark(invalidRemarkName));
    }

    @Test
    public void isValidRemarkName() {
        // null remark name
        assertThrows(NullPointerException.class, () -> Remark.isValidRemarkName(null));
    }

}
