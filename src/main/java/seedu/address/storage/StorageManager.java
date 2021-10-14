package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFriendsList;
import seedu.address.model.ReadOnlyGamesList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final FriendsListStorage friendsListStorage;
    private final GamesListStorage gamesListStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FriendsListStorage friendsListStorage, GamesListStorage gamesListStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.friendsListStorage = friendsListStorage;
        this.gamesListStorage = gamesListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Friends list methods ==============================

    @Override
    public Path getFriendsListFilePath() {
        return friendsListStorage.getFriendsListFilePath();
    }

    @Override
    public Optional<ReadOnlyFriendsList> readFriendsList() throws DataConversionException, IOException {
        return readFriendsList(friendsListStorage.getFriendsListFilePath());
    }

    @Override
    public Optional<ReadOnlyFriendsList> readFriendsList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read friends list data from file: " + filePath);
        return friendsListStorage.readFriendsList(filePath);
    }

    @Override
    public void saveFriendsList(ReadOnlyFriendsList friendsList) throws IOException {
        saveFriendsList(friendsList, friendsListStorage.getFriendsListFilePath());
    }

    @Override
    public void saveFriendsList(ReadOnlyFriendsList friendsList, Path filePath) throws IOException {
        logger.fine("Attempting to write friends list data to file: " + filePath);
        friendsListStorage.saveFriendsList(friendsList, filePath);
    }

    // ================ Games list methods ==============================

    @Override
    public Path getGamesListFilePath() {
        return gamesListStorage.getGamesListFilePath();
    }

    @Override
    public Optional<ReadOnlyGamesList> readGamesList() throws DataConversionException, IOException {
        return readGamesList(gamesListStorage.getGamesListFilePath());
    }

    @Override
    public Optional<ReadOnlyGamesList> readGamesList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read games list data from file: " + filePath);
        return gamesListStorage.readGamesList(filePath);
    }

    @Override
    public void saveGamesList(ReadOnlyGamesList gamesList) throws IOException {
        saveGamesList(gamesList, gamesListStorage.getGamesListFilePath());
    }

    @Override
    public void saveGamesList(ReadOnlyGamesList gamesList, Path filePath) throws IOException {
        logger.fine("Attempting to write games list data to file: " + filePath);
        gamesListStorage.saveGamesList(gamesList, filePath);
    }
}
