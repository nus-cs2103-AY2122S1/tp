package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import seedu.address.MainApp;

/**
 * Encapsulates a theme to be applied on the GUI.
 */
public class ThemeType {
    private final String themeName;

    /**
     * Create a {@code ThemeType} with the given {@code themeName}.
     */
    public ThemeType(String themeName) {
        this.themeName = themeName;
        requireNonNull(getFilePathName());
    }

    public String getFilePathName() {
        String cssFileName = String.format("/view/%s.css", themeName);
        return MainApp.class.getResource(cssFileName).toString();
    }

    @Override
    public String toString() {
        return this.themeName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ThemeType)) {
            return false;
        }

        ThemeType otherTheme = (ThemeType) other;
        return otherTheme.themeName.equals(this.themeName);
    }
}
