package seedu.address.model.gamefriendlink;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserNameTest {

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String emptyUsername = "";
        assertThrows(IllegalArgumentException.class, () -> new UserName(emptyUsername));

        String spaceUsername = "    ";
        assertThrows(IllegalArgumentException.class, () -> new UserName(spaceUsername));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> UserName.isValidUserName(null));

        // invalid name
        assertFalse(UserName.isValidUserName("")); // empty string
        assertFalse(UserName.isValidUserName("  ")); // spaces only

        // valid name
        assertTrue(UserName.isValidUserName("peter jack")); // alphabets only
        assertTrue(UserName.isValidUserName("12345")); // numbers only
        assertTrue(UserName.isValidUserName("peter the 2nd")); // alphanumeric characters
        assertTrue(UserName.isValidUserName("Capital Tan")); // with capital letters
        assertTrue(UserName.isValidUserName("!@#$%^")); // special characters only
        assertTrue(UserName.isValidUserName("peter@123.!?")); // alphabets with special characters
        assertTrue(UserName.isValidUserName("Roger@Jackson#1111")); // long names

    }
}
