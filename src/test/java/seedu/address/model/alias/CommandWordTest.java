package seedu.address.model.alias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommandWordTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CommandWord(null));
    }

    @Test
    public void constructor_invalidCommandWord_throwsIllegalArgumentException() {
        String invalidCommandWord = "bing";
        assertThrows(IllegalArgumentException.class, () -> new CommandWord(invalidCommandWord));
    }

    @Test
    public void isValidCommandWord() {
        // null command
        assertThrows(NullPointerException.class, () -> CommandWord.isValidCommandWord(null));

        // invalid command
        assertFalse(CommandWord.isValidCommandWord("")); // empty string
        assertFalse(CommandWord.isValidCommandWord(" ")); // spaces only
        assertFalse(CommandWord.isValidCommandWord("bingo")); // Unknown command

        // valid command
        assertTrue(CommandWord.isValidCommandWord("listf"));
        assertTrue(CommandWord.isValidCommandWord("clearm"));
    }
}
