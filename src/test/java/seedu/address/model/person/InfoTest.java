package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InfoTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Info(null));
    }

    @Test
    public void constructor_invalidInfo_throwsIllegalArgumentException() {
        String invalidInfo = "&";
        assertThrows(IllegalArgumentException.class, () -> new Info(invalidInfo));
    }

    @Test
    public void isValidImportantInfo() {
        // null info
        assertThrows(NullPointerException.class, () -> Info.isValidInfo(null));

        // invalid info
        assertFalse(Info.isValidInfo("*")); // use of non-alphanumeric characters

        // valid info
        assertTrue(Info.isValidInfo("only contactable via whatsapp"));
        assertTrue(Info.isValidInfo("none")); // no important info
    }

}
