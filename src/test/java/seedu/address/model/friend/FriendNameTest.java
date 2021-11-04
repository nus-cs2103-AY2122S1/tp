package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FriendNameTest {

    @Test
    public void constructor_null_returnsFriendWithDefaultName() {
        assertEquals(FriendName.DEFAULT_FRIEND_NAME, new FriendName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new FriendName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> FriendName.isValidName(null));

        // invalid name
        assertFalse(FriendName.isValidName("")); // empty string
        assertFalse(FriendName.isValidName("    ")); // spaces only
        assertFalse(FriendName.isValidName("----")); // hyphen only characters
        assertFalse(FriendName.isValidName("peter-jack")); // contains hyphen characters
        assertFalse(FriendName.isValidName("peter jack peter jack peter jack")); // longer than 20 chars

        // valid name
        assertTrue(FriendName.isValidName("Peter123")); // single word
        assertTrue(FriendName.isValidName("Peter jack123")); // multi-word
        assertTrue(FriendName.isValidName("`~!@#$%^&*()-_=+[{]}")); // all special characters available
        assertTrue(FriendName.isValidName("\\|;:'\",<.>/?")); // on an average keyboard
        assertTrue(FriendName.isValidName("Peter jack#123 @<>")); // with special characters
        assertTrue(FriendName.isValidName("p")); // short name - 1 char
        assertTrue(FriendName.isValidName("David Roger Evans Jr")); // long names - 20 chars
    }
}
