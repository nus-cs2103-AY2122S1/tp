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
    private Path accountFilePath = Paths.get("data" , "account.json");
    private Path ezFoodieFilePath = Paths.get("data" , "ezfoodie.json");

    /**
     * Constructs a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Constructs a {@code UserPrefs} with the prefs in {@code userPrefs}.
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
        setEzFoodieFilePath(newUserPrefs.getEzFoodieFilePath());
    }

    /**
     * Gets Gui Settings.
     */
    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    /**
     * Sets the Gui Settings by this input {@code guiSettings}.
     */
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    /**
     * Gets account file path.
     *
     * @return Path of account file.
     */
    public Path getAccountFilePath() {
        return accountFilePath;
    }

    /**
     * Sets the account file path by this input {@code accountFilePath}.
     */
    public void setAccountFilePath(Path accountFilePath) {
        requireNonNull(accountFilePath);
        this.accountFilePath = accountFilePath;
    }

    /**
     * Gets EzFoodie file path.
     *
     * @return Path of EzFoodie file.
     */
    public Path getEzFoodieFilePath() {
        return ezFoodieFilePath;
    }

    /**
     * Sets the EzFoodie file path by this input {@code ezFoodieFilePath}.
     */
    public void setEzFoodieFilePath(Path ezFoodieFilePath) {
        requireNonNull(ezFoodieFilePath);
        this.ezFoodieFilePath = ezFoodieFilePath;
    }

    /**
     * Overrides the equals method.
     */
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
                && accountFilePath.equals(o.accountFilePath)
                && ezFoodieFilePath.equals(o.ezFoodieFilePath);
    }

    /**
     * Overrides the hashCode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, accountFilePath, ezFoodieFilePath);
    }

    /**
     * Overrides toString method.
     *
     * @return String with gui Setting and file path and location.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal account file location : " + accountFilePath);
        sb.append("\nLocal data file location : " + ezFoodieFilePath);
        return sb.toString();
    }

}
