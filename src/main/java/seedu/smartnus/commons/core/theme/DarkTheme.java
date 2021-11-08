package seedu.smartnus.commons.core.theme;

public class DarkTheme extends Theme {

    protected static final String DARK_THEME_CSS_FILE = "DarkTheme.css";
    protected static final String DARK_EXTENSION_CSS_FILE = "Extensions.css";

    public DarkTheme() {
        super(DARK_THEME_CSS_FILE, DARK_EXTENSION_CSS_FILE);
    }

    @Override
    public String toString() {
        return "Dark Theme";
    }
}
