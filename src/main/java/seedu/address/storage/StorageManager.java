package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyCountdown;
import seedu.address.model.ReadOnlyShortcut;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private CountdownStorage countdownStorage;
    private ShortcutStorage shortcutStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, CountdownStorage countdownStorage,
                          UserPrefsStorage userPrefsStorage, ShortcutStorage shortcutStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.countdownStorage = countdownStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.shortcutStorage = shortcutStorage;
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
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }


    // ================ Countdown methods ==============================

    @Override
    public Path getCountdownFilePath() {
        return countdownStorage.getCountdownFilePath();
    }

    @Override
    public Optional<ReadOnlyCountdown> readCountdown() throws DataConversionException, IOException {
        return readCountdown(countdownStorage.getCountdownFilePath());
    }

    @Override
    public Optional<ReadOnlyCountdown> readCountdown(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return countdownStorage.readCountdown(filePath);
    }

    @Override
    public void saveCountdown(ReadOnlyCountdown countdown) throws IOException {
        saveCountdown(countdown, countdownStorage.getCountdownFilePath());
    }

    @Override
    public void saveCountdown(ReadOnlyCountdown countdown, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        countdownStorage.saveCountdown(countdown, filePath);
    }

    // ================ Shortcut methods ==============================

    @Override
    public Path getShortcutFilePath() {
        return countdownStorage.getCountdownFilePath();
    }

    @Override
    public Optional<ReadOnlyShortcut> readShortcut() throws DataConversionException, IOException {
        return readShortcut(shortcutStorage.getShortcutFilePath());
    }

    @Override
    public Optional<ReadOnlyShortcut> readShortcut(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return shortcutStorage.readShortcut(filePath);
    }

    @Override
    public void saveShortcut(ReadOnlyShortcut shortcut) throws IOException {
        saveShortcut(shortcut, shortcutStorage.getShortcutFilePath());
    }

    @Override
    public void saveShortcut(ReadOnlyShortcut shortcut, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        shortcutStorage.saveShortcut(shortcut, filePath);
    }

}
