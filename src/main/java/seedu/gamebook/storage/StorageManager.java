package seedu.gamebook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.gamebook.commons.core.LogsCenter;
import seedu.gamebook.commons.exceptions.DataConversionException;
import seedu.gamebook.model.ReadOnlyGameBook;
import seedu.gamebook.model.ReadOnlyUserPrefs;
import seedu.gamebook.model.UserPrefs;

/**
 * Manages storage of GameBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private GameBookStorage gameBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code GameBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(GameBookStorage gameBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.gameBookStorage = gameBookStorage;
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


    // ================ GameBook methods ==============================

    @Override
    public Path getGameBookFilePath() {
        return gameBookStorage.getGameBookFilePath();
    }

    @Override
    public Optional<ReadOnlyGameBook> readGameBook() throws DataConversionException, IOException {
        return readGameBook(gameBookStorage.getGameBookFilePath());
    }

    @Override
    public Optional<ReadOnlyGameBook> readGameBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return gameBookStorage.readGameBook(filePath);
    }

    @Override
    public void saveGameBook(ReadOnlyGameBook gameBook) throws IOException {
        saveGameBook(gameBook, gameBookStorage.getGameBookFilePath());
    }

    @Override
    public void saveGameBook(ReadOnlyGameBook gameBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        gameBookStorage.saveGameBook(gameBook, filePath);
    }

}
