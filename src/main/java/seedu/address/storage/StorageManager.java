package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyCsBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of CsBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private CsBookStorage csBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code CsBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(CsBookStorage csBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.csBookStorage = csBookStorage;
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


    // ================ CsBook methods ==============================

    @Override
    public Path getCsBookFilePath() {
        return csBookStorage.getCsBookFilePath();
    }

    @Override
    public Optional<ReadOnlyCsBook> readCsBook() throws DataConversionException, IOException {
        return readCsBook(csBookStorage.getCsBookFilePath());
    }

    @Override
    public Optional<ReadOnlyCsBook> readCsBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return csBookStorage.readCsBook(filePath);
    }

    @Override
    public void saveCsBook(ReadOnlyCsBook csBook) throws IOException {
        saveCsBook(csBook, csBookStorage.getCsBookFilePath());
    }

    @Override
    public void saveCsBook(ReadOnlyCsBook csBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        csBookStorage.saveCsBook(csBook, filePath);
    }

}
