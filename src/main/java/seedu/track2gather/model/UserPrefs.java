package seedu.track2gather.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.track2gather.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private static final String DATA_DIR = "data";
    private static final String USER_PREFS_FILE = "track2gather.json";
    private GuiSettings guiSettings = new GuiSettings();
    private Path track2GatherFilePath = Paths.get(DATA_DIR, USER_PREFS_FILE);

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
        setTrack2GatherFilePath(newUserPrefs.getTrack2GatherFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getTrack2GatherFilePath() {
        return track2GatherFilePath;
    }

    public void setTrack2GatherFilePath(Path track2GatherFilePath) {
        requireNonNull(track2GatherFilePath);
        this.track2GatherFilePath = track2GatherFilePath;
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
                && track2GatherFilePath.equals(o.track2GatherFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, track2GatherFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + track2GatherFilePath);
        return sb.toString();
    }

}
