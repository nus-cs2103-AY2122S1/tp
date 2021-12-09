package seedu.times.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.times.commons.core.LogsCenter;
import seedu.times.commons.exceptions.DataConversionException;
import seedu.times.model.ReadOnlyTimesTable;
import seedu.times.model.ReadOnlyUserPrefs;
import seedu.times.model.UserPrefs;

/**
 * Manages storage of TimesTable in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TimesTableStorage timesTableStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TimesTableStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TimesTableStorage timesTableStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.timesTableStorage = timesTableStorage;
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


    // ================ TimesTable methods ==============================

    @Override
    public Path getTimesTableFilePath() {
        return timesTableStorage.getTimesTableFilePath();
    }

    @Override
    public Optional<ReadOnlyTimesTable> readTimesTable() throws DataConversionException, IOException {
        return readTimesTable(timesTableStorage.getTimesTableFilePath());
    }

    @Override
    public Optional<ReadOnlyTimesTable> readTimesTable(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return timesTableStorage.readTimesTable(filePath);
    }

    @Override
    public void saveTimesTable(ReadOnlyTimesTable timesTable) throws IOException {
        saveTimesTable(timesTable, timesTableStorage.getTimesTableFilePath());
    }

    @Override
    public void saveTimesTable(ReadOnlyTimesTable timesTable, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        timesTableStorage.saveTimesTable(timesTable, filePath);
    }

}
