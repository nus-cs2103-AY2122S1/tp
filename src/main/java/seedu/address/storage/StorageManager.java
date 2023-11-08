package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlySportsPa;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of SportsPa data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SportsPaStorage sportsPaStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SportsPaStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(SportsPaStorage sportsPaStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.sportsPaStorage = sportsPaStorage;
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


    // ================ SportsPa methods ==============================

    @Override
    public Path getSportsPaFilePath() {
        return sportsPaStorage.getSportsPaFilePath();
    }

    @Override
    public Optional<ReadOnlySportsPa> readSportsPa() throws DataConversionException, IOException {
        return readSportsPa(sportsPaStorage.getSportsPaFilePath());
    }

    @Override
    public Optional<ReadOnlySportsPa> readSportsPa(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return sportsPaStorage.readSportsPa(filePath);
    }

    @Override
    public void saveSportsPa(ReadOnlySportsPa addressBook) throws IOException {
        saveSportsPa(addressBook, sportsPaStorage.getSportsPaFilePath());
    }

    @Override
    public void saveSportsPa(ReadOnlySportsPa addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        sportsPaStorage.saveSportsPa(addressBook, filePath);
    }

}
