package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path guestBookFilePath = Paths.get("data", "guests.json");
    private Path vendorBookFilePath = Paths.get("data", "vendors.json");
    private Path archiveFilePath = Paths.get("data", "archive.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

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
        setGuestBookFilePath(newUserPrefs.getGuestBookFilePath());
        setVendorBookFilePath(newUserPrefs.getVendorBookFilePath());
        setArchiveFilePath(newUserPrefs.getArchiveFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getGuestBookFilePath() {
        return guestBookFilePath;
    }

    public void setGuestBookFilePath(Path guestBookFilePath) {
        requireNonNull(guestBookFilePath);
        this.guestBookFilePath = guestBookFilePath;
    }

    public Path getVendorBookFilePath() {
        return vendorBookFilePath;
    }

    public void setVendorBookFilePath(Path vendorBookFilePath) {
        requireNonNull(vendorBookFilePath);
        this.vendorBookFilePath = vendorBookFilePath;
    }

    public Path getArchiveFilePath() {
        return archiveFilePath;
    }

    public void setArchiveFilePath(Path archiveFilePath) {
        requireNonNull(archiveFilePath);
        this.archiveFilePath = archiveFilePath;
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
                && guestBookFilePath.equals(o.guestBookFilePath)
                && vendorBookFilePath.equals(o.vendorBookFilePath)
                && archiveFilePath.equals(o.archiveFilePath);
    }

    // are there issues with the implementation of the methods below??

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, guestBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + guestBookFilePath);
        return sb.toString();
    }

}
