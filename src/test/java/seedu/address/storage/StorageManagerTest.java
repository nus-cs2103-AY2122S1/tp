package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.FriendsList;
import seedu.address.model.GamesList;
import seedu.address.model.ReadOnlyFriendsList;
import seedu.address.model.ReadOnlyGamesList;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {
    private static final String FILE_PATH_USER_PREFS = "prefs";
    private static final String FILE_PATH_GAMES_LIST = "gameslist";
    private static final String FILE_PATH_FRIENDS_LIST = "friendslist";

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFriendsListStorage friendsListStorage = new JsonFriendsListStorage(getTempFilePath(FILE_PATH_FRIENDS_LIST));
        JsonGamesListStorage gamesListStorage = new JsonGamesListStorage(getTempFilePath(FILE_PATH_GAMES_LIST));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath(FILE_PATH_USER_PREFS));
        storageManager = new StorageManager(friendsListStorage, gamesListStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void friendsListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFriendsListStorage} class.
         * More extensive testing of FriendsList saving/reading is done in {@link JsonFriendsListStorageTest} class.
         */
        FriendsList original = getTypicalFriendsList();
        storageManager.saveFriendsList(original);
        ReadOnlyFriendsList retrieved = storageManager.readFriendsList().get();
        assertEquals(original, new FriendsList(retrieved));
    }

    @Test
    public void gamesListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonGamesListStorage} class.
         * More extensive testing of GamesList saving/reading is done in {@link JsonGamesListStorageTest} class.
         */
        GamesList original = getTypicalGamesList();
        storageManager.saveGamesList(original);
        ReadOnlyGamesList retrieved = storageManager.readGamesList().get();
        assertEquals(original, new GamesList(retrieved));
    }

    @Test
    public void getFriendsListFilePath() {
        assertNotNull(storageManager.getFriendsListFilePath());
        assertEquals(getTempFilePath(FILE_PATH_FRIENDS_LIST), storageManager.getFriendsListFilePath());
    }

    @Test
    public void getGamesListFilePath() {
        assertNotNull(storageManager.getGamesListFilePath());
        assertEquals(getTempFilePath(FILE_PATH_GAMES_LIST), storageManager.getGamesListFilePath());
    }

    @Test
    public void getUserPrefFilePath() {
        assertNotNull(storageManager.getUserPrefsFilePath());
        assertEquals(getTempFilePath(FILE_PATH_USER_PREFS), storageManager.getUserPrefsFilePath());
    }

}
