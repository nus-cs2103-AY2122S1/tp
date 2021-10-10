package seedu.address.model.game;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Game(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Game(new GameId(invalidTagName)));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> GameId.isValidGameId(null));
    }

}
