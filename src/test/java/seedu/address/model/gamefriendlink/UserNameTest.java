package seedu.address.model.gamefriendlink;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USER_NAME_DRACO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USER_NAME_OMEGA;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.FriendName;

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
        assertFalse(FriendName.isValidName("")); // empty string
        assertFalse(FriendName.isValidName("    ")); // spaces only
        assertFalse(FriendName.isValidName("----")); // hyphen only characters
        assertFalse(FriendName.isValidName("peter-jack")); // contains hyphen characters
        assertFalse(FriendName.isValidName("peter jack peter jack peter jack")); // longer than 20 chars

        // valid name
        assertTrue(FriendName.isValidName("Peter123")); // single word
        assertTrue(FriendName.isValidName("Peter jack123")); // multi-word
        assertTrue(FriendName.isValidName("`~!@#$%^&*()_=+[{]}")); // all special characters available
        assertTrue(FriendName.isValidName("\\|;:'\",<.>/?")); // on an average keyboard
        assertTrue(FriendName.isValidName("Peter jack#123 @<>")); // with special characters
        assertTrue(FriendName.isValidName("p")); // short name - 1 char
        assertTrue(FriendName.isValidName("David Roger Evans Jr")); // long names - 20 chars
    }

    @Test
    public void equals() {
        UserName userName = new UserName(VALID_USER_NAME_OMEGA);

        // same object -> true
        assertEquals(userName, userName);

        // null -> false
        assertNotEquals(userName, null);

        // different types
        assertNotEquals(userName, "String");

        // different objects with same identity values -> true
        assertEquals(userName, new UserName(userName.value));

        // different identity values
        assertNotEquals(userName, new UserName(VALID_USER_NAME_DRACO));
    }
}
