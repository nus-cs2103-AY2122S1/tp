package seedu.address.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_APEX_LEGENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_CSGO;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.FriendId;

public class GameIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GameId(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String emptyId = "";
        assertThrows(IllegalArgumentException.class, () -> new GameId(emptyId));

        String idWithSpaces = "Id with spaces";
        assertThrows(IllegalArgumentException.class, () -> new GameId(idWithSpaces));
    }

    @Test
    public void isValidGameId() {
        // null id
        assertThrows(NullPointerException.class, () -> GameId.isValidGameId(null));

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
        GameId gameId = new GameId(VALID_GAME_ID_APEX_LEGENDS);

        // same object -> true
        assertEquals(gameId, gameId);

        // null -> false
        assertNotEquals(gameId, null);

        // different types
        assertNotEquals(gameId, "String");

        // different objects with same identity values -> true
        assertEquals(gameId, new GameId(gameId.value));

        // different identity values
        assertNotEquals(gameId, new GameId(VALID_GAME_ID_CSGO));
    }
}
