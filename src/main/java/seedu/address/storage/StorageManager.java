package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFriendsList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FriendsListStorage friendsListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FriendsListStorage friendsListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.friendsListStorage = friendsListStorage;
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return friendsListStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyFriendsList> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(friendsListStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyFriendsList> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return friendsListStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyFriendsList addressBook) throws IOException {
        saveAddressBook(addressBook, friendsListStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyFriendsList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        friendsListStorage.saveAddressBook(addressBook, filePath);
    }

}
