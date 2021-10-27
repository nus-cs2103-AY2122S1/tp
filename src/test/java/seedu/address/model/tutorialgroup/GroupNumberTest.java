package seedu.address.model.tutorialgroup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class GroupNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupNumber(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidGroupName = "";
        assertThrows(IllegalArgumentException.class, () -> new GroupNumber(invalidGroupName));
    }

    @Test
    public void isValidName() {
        // null group name
        assertThrows(NullPointerException.class, () -> GroupNumber.isValidGroupName(null));

        // invalid group name
        assertFalse(GroupNumber.isValidGroupName("")); // empty string
        assertFalse(GroupNumber.isValidGroupName(" ")); // spaces only
        assertFalse(GroupNumber.isValidGroupName("^")); // only non-alphanumeric characters
        assertFalse(GroupNumber.isValidGroupName("peter*")); // contains non-alphanumeric characters
        assertFalse(GroupNumber.isValidGroupName("peter")); // contains alphabets
        assertFalse(GroupNumber.isValidGroupName("12")); // contains more than one digit

        // valid group name
        assertTrue(GroupNumber.isValidGroupName("1")); // alphabets only
        assertTrue(GroupNumber.isValidGroupName("2")); // numbers only
    }
}
