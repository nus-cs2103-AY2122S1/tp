package seedu.smartnus.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.smartnus.commons.core.LogsCenter;
import seedu.smartnus.commons.exceptions.DataConversionException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.ReadOnlyUserPrefs;
import seedu.smartnus.model.UserPrefs;

/**
 * Manages storage of SmartNus data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SmartNusStorage smartNusStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SmartNusStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(SmartNusStorage smartNusStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.smartNusStorage = smartNusStorage;
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


    // ================ SmartNus methods ==============================

    @Override
    public Path getSmartNusFilePath() {
        return smartNusStorage.getSmartNusFilePath();
    }

    @Override
    public Optional<ReadOnlySmartNus> readSmartNus() throws DataConversionException, IOException {
        return readSmartNus(smartNusStorage.getSmartNusFilePath());
    }

    @Override
    public Optional<ReadOnlySmartNus> readSmartNus(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return smartNusStorage.readSmartNus(filePath);
    }

    @Override
    public void saveSmartNus(ReadOnlySmartNus smartNus) throws IOException {
        saveSmartNus(smartNus, smartNusStorage.getSmartNusFilePath());
    }

    @Override
    public void saveSmartNus(ReadOnlySmartNus smartNus, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        smartNusStorage.saveSmartNus(smartNus, filePath);
    }

}
