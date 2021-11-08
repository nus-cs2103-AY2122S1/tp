package seedu.smartnus.commons.core.theme;

public class LightTheme extends Theme {

    protected static final String LIGHT_THEME_CSS_FILE = "LightTheme.css";
    protected static final String LIGHT_EXTENSION_CSS_FILE = "LightExtensions.css";

    public LightTheme() {
        super(LIGHT_THEME_CSS_FILE, LIGHT_EXTENSION_CSS_FILE);
    }

    @Override
    public String toString() {
        return "Light Theme";
    }
}
