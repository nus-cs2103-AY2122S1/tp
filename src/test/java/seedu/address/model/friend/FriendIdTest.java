package seedu.address.model.friend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertFalse(FriendId.isValidFriendId(" ")); // spaces only
        assertFalse(FriendId.isValidFriendId("^")); // only non-alphanumeric characters
        assertFalse(FriendId.isValidFriendId("peter*")); // contains non-alphanumeric characters
        assertFalse(FriendId.isValidFriendId("peter jack")); // contains multiple spaced words

        // valid id
        assertTrue(FriendId.isValidFriendId("peter")); // alphabets only
        assertTrue(FriendId.isValidFriendId("12345")); // numbers only
        assertTrue(FriendId.isValidFriendId("peter24")); // alphanumeric characters
        assertTrue(FriendId.isValidFriendId("CapiTan")); // with capital letters
        assertTrue(FriendId.isValidFriendId("PeterFromSchoolWhoIs24yo")); // long ids
    }
}
