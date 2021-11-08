package seedu.address.model.group;

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
        String invalidName = "w4-4";
        assertThrows(IllegalArgumentException.class, () -> new GroupName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> GroupName.isValidName(null));

        // invalid name
        assertFalse(GroupName.isValidName("")); // empty string
        assertFalse(GroupName.isValidName(" ")); // spaces only
        assertFalse(GroupName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(GroupName.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(GroupName.isValidName("w4-4")); // first numerical input only 1 digit
        assertFalse(GroupName.isValidName("w14-44")); // second numerical input more than 1 digit
        assertFalse(GroupName.isValidName("w14 4")); // no dash between numerical inputs


        // valid name
        assertTrue(GroupName.isValidName("w14-4")); // lowercase letter
        assertTrue(GroupName.isValidName("F04-2")); // uppercase letter
    }
}
