package seedu.address.model.alias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ShortcutTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Shortcut(null));
    }

    @Test
    public void constructor_invalidShortcut_throwsIllegalArgumentException() {
        String invalidShortcut = "";
        assertThrows(IllegalArgumentException.class, () -> new Shortcut(invalidShortcut));
    }

    @Test
    public void isValidShortcut() {
        // null shortcut
        assertThrows(NullPointerException.class, () -> Shortcut.isValidShortcut(null));

        // invalid shortcut
        assertFalse(Shortcut.isValidShortcut("")); // empty string
        assertFalse(Shortcut.isValidShortcut(" ")); // spaces only
        assertFalse(Shortcut.isValidShortcut("listf")); // an existing command
        assertFalse(Shortcut.isValidShortcut("list facilities"));; // more than one word

        // valid shortcut
        assertTrue(Shortcut.isValidShortcut("lf")); // alphabets only
        assertTrue(Shortcut.isValidShortcut("23")); // numbers only
        assertTrue(Shortcut.isValidShortcut("lf2"));; // alphanumeric characters
        assertTrue(Shortcut.isValidShortcut("Lf"));; // with capital letters
    }

}
