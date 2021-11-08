package seedu.smartnus.commons.core.theme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ThemeTest {
    @Test
    public void theme_test() {
        Theme theme = new Theme();
        assertNotNull(theme.getThemeCssFile());
        assertNotNull(theme.getExtensionsCssFile());
        assertNotNull(theme.toString());
    }

    @Test
    public void light_theme_test() {
        Theme theme = new LightTheme();
        assertNotNull(theme.getThemeCssFile());
        assertNotNull(theme.getExtensionsCssFile());
        assertNotNull(theme.toString());
    }

    @Test
    public void dark_theme_test() {
        Theme theme = new DarkTheme();
        assertNotNull(theme.getThemeCssFile());
        assertNotNull(theme.getExtensionsCssFile());
        assertNotNull(theme.toString());
    }

    @Test
    public void equals_test() {
        Theme theme1 = new LightTheme();
        Theme theme2 = new DarkTheme();
        Theme theme3 = new LightTheme();
        assertEquals(theme1, theme3);
        assertNotEquals(theme1, theme2);
        assertNotEquals(null, theme1);
    }

}
