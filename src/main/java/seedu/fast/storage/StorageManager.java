package seedu.fast.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.fast.commons.core.LogsCenter;
import seedu.fast.commons.exceptions.DataConversionException;
import seedu.fast.model.ReadOnlyFast;
import seedu.fast.model.ReadOnlyUserPrefs;
import seedu.fast.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FastStorage fastStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FastStorage fastStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.fastStorage = fastStorage;
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
    public Path getFastFilePath() {
        return fastStorage.getFastFilePath();
    }

    @Override
    public Optional<ReadOnlyFast> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(fastStorage.getFastFilePath());
    }

    @Override
    public Optional<ReadOnlyFast> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return fastStorage.readAddressBook(filePath);
    }

    @Override
    public void saveFast(ReadOnlyFast addressBook) throws IOException {
        saveFast(addressBook, fastStorage.getFastFilePath());
    }

    @Override
    public void saveFast(ReadOnlyFast addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        fastStorage.saveFast(addressBook, filePath);
    }

}
