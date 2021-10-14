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
    private Path friendsListFilePath = Paths.get("data", "friendsList.json");
    private Path gamesListFilePath = Paths.get("data", "gamesList.json");

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
        setFriendsListFilePath(newUserPrefs.getFriendsListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getFriendsListFilePath() {
        return friendsListFilePath;
    }

    public void setFriendsListFilePath(Path friendsListFilePath) {
        requireNonNull(friendsListFilePath);
        this.friendsListFilePath = friendsListFilePath;
    }

    public Path getGamesListFilePath() {
        return gamesListFilePath;
    }

    public void setGamesListFilePath(Path gamesListFilePath) {
        this.gamesListFilePath = gamesListFilePath;
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
                && friendsListFilePath.equals(o.friendsListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, friendsListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + friendsListFilePath);
        return sb.toString();
    }

}
