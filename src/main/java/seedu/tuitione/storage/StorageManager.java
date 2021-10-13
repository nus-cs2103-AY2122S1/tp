package seedu.tuitione.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.tuitione.commons.core.LogsCenter;
import seedu.tuitione.commons.exceptions.DataConversionException;
import seedu.tuitione.model.ReadOnlyTuitione;
import seedu.tuitione.model.ReadOnlyUserPrefs;
import seedu.tuitione.model.UserPrefs;

/**
 * Manages storage of Tuitione data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TuitioneStorage tuitioneStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TuitioneStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TuitioneStorage tuitioneStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.tuitioneStorage = tuitioneStorage;
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


    // ================ Tuitione methods ==============================

    @Override
    public Path getTuitioneFilePath() {
        return tuitioneStorage.getTuitioneFilePath();
    }

    @Override
    public Optional<ReadOnlyTuitione> readTuitione() throws DataConversionException, IOException {
        return readTuitione(tuitioneStorage.getTuitioneFilePath());
    }

    @Override
    public Optional<ReadOnlyTuitione> readTuitione(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return tuitioneStorage.readTuitione(filePath);
    }

    @Override
    public void saveTuitione(ReadOnlyTuitione tuitione) throws IOException {
        saveTuitione(tuitione, tuitioneStorage.getTuitioneFilePath());
    }

    @Override
    public void saveTuitione(ReadOnlyTuitione tuitione, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        tuitioneStorage.saveTuitione(tuitione, filePath);
    }

}
