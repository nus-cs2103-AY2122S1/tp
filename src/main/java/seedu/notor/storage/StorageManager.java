package seedu.notor.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.notor.commons.core.LogsCenter;
import seedu.notor.commons.exceptions.DataConversionException;
import seedu.notor.model.ReadOnlyNotor;
import seedu.notor.model.ReadOnlyUserPrefs;
import seedu.notor.model.UserPrefs;

/**
 * Manages storage of Notor data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final NotorStorage notorStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code NotorStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(NotorStorage notorStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.notorStorage = notorStorage;
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


    // ================ Notor methods ==============================

    @Override
    public Path getNotorFilePath() {
        return notorStorage.getNotorFilePath();
    }

    @Override
    public Optional<ReadOnlyNotor> readNotor() throws DataConversionException, IOException {
        return readNotor(notorStorage.getNotorFilePath());
    }

    @Override
    public Optional<ReadOnlyNotor> readNotor(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return notorStorage.readNotor(filePath);
    }

    @Override
    public void saveNotor(ReadOnlyNotor notor) throws IOException {
        saveNotor(notor, notorStorage.getNotorFilePath());
    }

    @Override
    public void saveNotor(ReadOnlyNotor notor, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        notorStorage.saveNotor(notor, filePath);
    }

}
