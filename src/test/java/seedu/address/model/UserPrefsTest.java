package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setFriendsListFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setFriendsListFilePath(null));
    }

    @Test
    public void setGamesListFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setGamesListFilePath(null));
    }

    @Test
    public void getGamesListFilePath_returnsCorrectPath() {
        UserPrefs userPrefs = new UserPrefs();
        Path path = Paths.get("games", "path");
        userPrefs.setGamesListFilePath(path);
        assertEquals(path, userPrefs.getGamesListFilePath());
    }

    @Test
    public void equals() {
        UserPrefs userPref = new UserPrefs();
        Path newPath = Paths.get("new", "path");

        // same object
        assertEquals(userPref, userPref);

        // null
        assertNotEquals(userPref, null);

        // null
        assertNotEquals(userPref, "String");

        // another new userPref
        assertEquals(userPref, new UserPrefs());

        // different gui settings
        UserPrefs diffUserPref = new UserPrefs();
        diffUserPref.setGuiSettings(new GuiSettings(100, 100, 100, 100));
        assertNotEquals(userPref, diffUserPref);

        diffUserPref.setGuiSettings(userPref.getGuiSettings()); // reset

        // different friendsListFilePath
        diffUserPref.setFriendsListFilePath(newPath);
        assertNotEquals(userPref, diffUserPref);

        diffUserPref.setFriendsListFilePath(userPref.getFriendsListFilePath());

        // different gamesListFilePath
        diffUserPref.setGamesListFilePath(newPath);
        assertNotEquals(userPref, diffUserPref);
    }
}
