package seedu.siasa.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.siasa.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    // TODO: Need to update file name
    private Path siasaFilePath = Paths.get("data" , "addressbook.json");
    private Path policyFilePath = Paths.get("data" , "policybook.json");

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
        setSiasaFilePath(newUserPrefs.getSiasaFilePath());
        setPolicyFilePath(newUserPrefs.getPolicyFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getSiasaFilePath() {
        return siasaFilePath;
    }

    public void setSiasaFilePath(Path siasaFilePath) {
        requireNonNull(siasaFilePath);
        this.siasaFilePath = siasaFilePath;
    }

    public Path getPolicyFilePath() {
        return policyFilePath;
    }

    public void setPolicyFilePath(Path policyFilePath) {
        requireNonNull(policyFilePath);
        this.policyFilePath = policyFilePath;
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
                && siasaFilePath.equals(o.siasaFilePath)
                && policyFilePath.equals(o.policyFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, siasaFilePath, policyFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal address data file location : " + siasaFilePath);
        sb.append("\nLocal policy data file location : " + policyFilePath);
        return sb.toString();
    }

}
