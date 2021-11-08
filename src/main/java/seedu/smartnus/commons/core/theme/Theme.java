package seedu.smartnus.commons.core.theme;

import static seedu.smartnus.commons.core.theme.LightTheme.LIGHT_EXTENSION_CSS_FILE;
import static seedu.smartnus.commons.core.theme.LightTheme.LIGHT_THEME_CSS_FILE;

/**
 * The UI Theme.
 */
public class Theme {

    private final String themeCssFile;
    private final String extensionCssFile;

    /**
     * Default constructor for theme.
     */
    public Theme() {
        this.themeCssFile = LIGHT_THEME_CSS_FILE;
        this.extensionCssFile = LIGHT_EXTENSION_CSS_FILE;
    }


    /**
     * Constructor for a theme.
     * @param themeCssFile The theme css file name.
     * @param extensionCssFile The extension css file name.
     */
    public Theme(String themeCssFile, String extensionCssFile) {
        this.themeCssFile = themeCssFile;
        this.extensionCssFile = extensionCssFile;
    }


    /**
     * Returns the filename of the theme css file.
     * @return The filename of the theme css file.
     */
    public String getThemeCssFile() {
        return themeCssFile;
    }

    /**
     * Returns the filename of the extensions css file.
     * @return The filename of the extensions css file.
     */
    public String getExtensionsCssFile() {
        return extensionCssFile;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Theme)) { //this handles null as well.
            return false;
        }

        Theme o = (Theme) other;

        return themeCssFile.equals(o.themeCssFile)
                && extensionCssFile.equals(o.extensionCssFile);
    }

    @Override
    public String toString() {
        return "Theme";
    }
}
