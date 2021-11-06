package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FriendIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FriendId(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String emptyId = "";
        assertThrows(IllegalArgumentException.class, () -> new FriendId(emptyId));

        String idWithSpaces = "Id with spaces";
        assertThrows(IllegalArgumentException.class, () -> new FriendId(idWithSpaces));
    }

    @Test
    public void isValidFriendId() {
        // null id
        assertThrows(NullPointerException.class, () -> FriendId.isValidFriendId(null));

        // invalid id
        assertFalse(FriendId.isValidFriendId("")); // empty string
        assertFalse(FriendId.isValidFriendId("        ")); // spaces only
        assertFalse(FriendId.isValidFriendId("!@#$%^&*()")); // only non-alphanumeric characters
        assertFalse(FriendId.isValidFriendId("peter*jack")); // contains non-alphanumeric characters
        assertFalse(FriendId.isValidFriendId("peter jack")); // contains multiple words
        assertFalse(FriendId.isValidFriendId("peter jack peter jack peter jack")); // longer than 20 chars

        // valid id
        assertTrue(FriendId.isValidFriendId("peter")); // alphabets only
        assertTrue(FriendId.isValidFriendId("12345")); // numbers only
        assertTrue(FriendId.isValidFriendId("peter24")); // alphanumeric characters
        assertTrue(FriendId.isValidFriendId("CapiTan")); // with capital letters
        assertTrue(FriendId.isValidFriendId("p")); // short id - 1 char
        assertTrue(FriendId.isValidFriendId("PeterEvansSchool1234")); // long id - 20 chars
    }

    @Test
    public void equals() {
        FriendId friendId = new FriendId(VALID_FRIEND_ID_AMY);

        // same object -> true
        assertEquals(friendId, friendId);

        // null -> false
        assertNotEquals(friendId, null);

        // different types
        assertNotEquals(friendId, "String");

        // different objects with same identity values -> true
        assertEquals(friendId, new FriendId(friendId.value));

        // different identity values
        assertNotEquals(friendId, new FriendId(VALID_FRIEND_ID_BOB));
    }
}
