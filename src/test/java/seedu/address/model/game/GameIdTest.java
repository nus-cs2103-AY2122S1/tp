package seedu.address.model.game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
        assertFalse(GameId.isValidGameId("")); // empty string
        assertFalse(GameId.isValidGameId(" ")); // spaces only
        assertFalse(GameId.isValidGameId("^")); // only non-alphanumeric characters
        assertFalse(GameId.isValidGameId("peter*")); // contains non-alphanumeric characters
        assertFalse(GameId.isValidGameId("peter jack")); // contains multiple spaced words

        // valid id
        assertTrue(GameId.isValidGameId("minecraft")); // alphabets only
        assertTrue(GameId.isValidGameId("12345")); // numbers only
        assertTrue(GameId.isValidGameId("gta5")); // alphanumeric characters
        assertTrue(GameId.isValidGameId("AmongUs")); // with capital letters
        assertTrue(GameId.isValidGameId("RedDead2Redemption")); // long ids
    }
}
