package seedu.address.model.tutorialgroup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class GroupNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GroupName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidGroupName = "";
        assertThrows(IllegalArgumentException.class, () -> new GroupName(invalidGroupName));
    }

    @Test
    public void isValidName() {
        // null group name
        assertThrows(NullPointerException.class, () -> GroupName.isValidGroupName(null));

        // invalid group name
        assertFalse(GroupName.isValidGroupName("")); // empty string
        assertFalse(GroupName.isValidGroupName(" ")); // spaces only
        assertFalse(GroupName.isValidGroupName("^")); // only non-alphanumeric characters
        assertFalse(GroupName.isValidGroupName("peter*")); // contains non-alphanumeric characters
        assertFalse(GroupName.isValidGroupName("peter")); // contains alphabets
        assertFalse(GroupName.isValidGroupName("12")); // contains more than one digit

        // valid group name
        assertTrue(GroupName.isValidGroupName("1")); // alphabets only
        assertTrue(GroupName.isValidGroupName("2")); // numbers only
    }
}
