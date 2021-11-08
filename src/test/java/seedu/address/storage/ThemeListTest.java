package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.ui.ThemeType;

public class ThemeListTest {

    private final ThemeType bookTheme = ThemeType.of("BookTheme").get();
    private final ThemeType spaceTheme = ThemeType.of("SpaceTheme").get();
    private final ThemeList themeList = new ThemeList(bookTheme);

    @Test
    public void equals() {
        ThemeList other = new ThemeList(spaceTheme);

        // same object
        assertTrue(themeList.equals(themeList));

        // same theme
        assertTrue(themeList.equals(new ThemeList(bookTheme)));

        // different class
        assertFalse(themeList.equals("BookTheme"));

        // different themes
        assertFalse(themeList.equals(other));
    }
}
