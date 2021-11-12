package seedu.teachbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.teachbook.commons.core.LogsCenter;
import seedu.teachbook.commons.exceptions.DataConversionException;
import seedu.teachbook.model.ReadOnlyTeachBook;
import seedu.teachbook.model.ReadOnlyUserPrefs;
import seedu.teachbook.model.UserPrefs;

/**
 * Manages storage of TeachBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final TeachBookStorage teachBookStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TeachBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TeachBookStorage teachBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.teachBookStorage = teachBookStorage;
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


    // ================ TeachBook methods ==============================

    @Override
    public Path getTeachBookFilePath() {
        return teachBookStorage.getTeachBookFilePath();
    }

    @Override
    public Optional<ReadOnlyTeachBook> readTeachBook() throws DataConversionException, IOException {
        return readTeachBook(teachBookStorage.getTeachBookFilePath());
    }

    @Override
    public Optional<ReadOnlyTeachBook> readTeachBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return teachBookStorage.readTeachBook(filePath);
    }

    @Override
    public void saveTeachBook(ReadOnlyTeachBook teachBook) throws IOException {
        saveTeachBook(teachBook, teachBookStorage.getTeachBookFilePath());
    }

    @Override
    public void saveTeachBook(ReadOnlyTeachBook teachBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        teachBookStorage.saveTeachBook(teachBook, filePath);
    }

}
