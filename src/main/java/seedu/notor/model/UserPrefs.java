package seedu.notor.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.notor.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path notorFilePath = Paths.get("data", "notor.json");
    private Path notorArchiveFilePath = Paths.get("data", "archive.json");

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
        setNotorFilePath(newUserPrefs.getNotorFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getNotorFilePath() {
        return notorFilePath;
    }

    public void setNotorFilePath(Path notorFilePath) {
        requireNonNull(notorFilePath);
        this.notorFilePath = notorFilePath;
    }

    public Path getNotorArchiveFilePath() {
        return notorArchiveFilePath;
    }

    public void setNotorArchiveFilePath(Path notorArchiveFilePath) {
        requireNonNull(notorArchiveFilePath);
        this.notorArchiveFilePath = notorArchiveFilePath;
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
                && notorFilePath.equals(o.notorFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, notorFilePath);
    }

    @Override
    public String toString() {
        return "Gui Settings : " + guiSettings + "\nLocal data file location : " + notorFilePath;
    }
}
