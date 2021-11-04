package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyRhrh;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Rhrh data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private RhrhStorage rhrhStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code RhrhStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(RhrhStorage rhrhStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.rhrhStorage = rhrhStorage;
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


    // ================ Rhrh methods ==============================

    @Override
    public Path getRhrhFilePath() {
        return rhrhStorage.getRhrhFilePath();
    }

    @Override
    public Optional<ReadOnlyRhrh> readRhrh() throws DataConversionException, IOException {
        return readRhrh(rhrhStorage.getRhrhFilePath());
    }

    @Override
    public Optional<ReadOnlyRhrh> readRhrh(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return rhrhStorage.readRhrh(filePath);
    }

    @Override
    public void saveRhrh(ReadOnlyRhrh rhrh) throws IOException {
        saveRhrh(rhrh, rhrhStorage.getRhrhFilePath());
    }

    @Override
    public void saveRhrh(ReadOnlyRhrh rhrh, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        rhrhStorage.saveRhrh(rhrh, filePath);
    }

}
