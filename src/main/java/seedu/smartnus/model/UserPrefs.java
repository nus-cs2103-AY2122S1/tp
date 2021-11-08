package seedu.smartnus.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.commons.core.theme.LightTheme;
import seedu.smartnus.commons.core.theme.Theme;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path smartNusFilePath = Paths.get("data" , "smartnus.json");
    private Theme theme = new LightTheme();

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setSmartNusFilePath(newUserPrefs.getSmartNusFilePath());
        setTheme(newUserPrefs.getTheme());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        requireNonNull(theme);
        this.theme = theme;
    }

    public Path getSmartNusFilePath() {
        return smartNusFilePath;
    }

    public void setSmartNusFilePath(Path smartNusFilePath) {
        requireNonNull(smartNusFilePath);
        this.smartNusFilePath = smartNusFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && smartNusFilePath.equals(o.smartNusFilePath)
                && theme.equals(o.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, smartNusFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + smartNusFilePath);
        sb.append("\nTheme: " + theme);
        return sb.toString();
    }

}
