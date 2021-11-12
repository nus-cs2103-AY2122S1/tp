package seedu.unify.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.unify.commons.core.LogsCenter;
import seedu.unify.commons.exceptions.DataConversionException;
import seedu.unify.model.ReadOnlyUniFy;
import seedu.unify.model.ReadOnlyUserPrefs;
import seedu.unify.model.UserPrefs;

/**
 * Manages storage of UniFy data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private UniFyStorage uniFyStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code UniFyStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(UniFyStorage uniFyStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.uniFyStorage = uniFyStorage;
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


    // ================ UniFy methods ==============================

    @Override
    public Path getUniFyFilePath() {
        return uniFyStorage.getUniFyFilePath();
    }

    @Override
    public Optional<ReadOnlyUniFy> readUniFy() throws DataConversionException, IOException {
        return readUniFy(uniFyStorage.getUniFyFilePath());
    }

    @Override
    public Optional<ReadOnlyUniFy> readUniFy(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return uniFyStorage.readUniFy(filePath);
    }

    @Override
    public void saveUniFy(ReadOnlyUniFy uniFy) throws IOException {
        saveUniFy(uniFy, uniFyStorage.getUniFyFilePath());
    }

    @Override
    public void saveUniFy(ReadOnlyUniFy uniFy, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        uniFyStorage.saveUniFy(uniFy, filePath);
    }

}
