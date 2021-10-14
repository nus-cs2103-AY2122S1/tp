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
        assertFalse(FriendName.isValidName(" ")); // spaces only
        assertFalse(FriendName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(FriendName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(FriendName.isValidName("peter jack")); // alphabets only
        assertTrue(FriendName.isValidName("12345")); // numbers only
        assertTrue(FriendName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(FriendName.isValidName("Capital Tan")); // with capital letters
        assertTrue(FriendName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
