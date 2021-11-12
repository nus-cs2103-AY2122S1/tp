package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyConthacks;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Conthacks data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ConthacksStorage conthacksStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ConthacksStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ConthacksStorage conthacksStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.conthacksStorage = conthacksStorage;
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


    // ================ Conthacks methods ==============================

    @Override
    public Path getConthacksFilePath() {
        return conthacksStorage.getConthacksFilePath();
    }

    @Override
    public Optional<ReadOnlyConthacks> readConthacks() throws DataConversionException, IOException {
        return readConthacks(conthacksStorage.getConthacksFilePath());
    }

    @Override
    public Optional<ReadOnlyConthacks> readConthacks(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return conthacksStorage.readConthacks(filePath);
    }

    @Override
    public void saveConthacks(ReadOnlyConthacks conthacks) throws IOException {
        saveConthacks(conthacks, conthacksStorage.getConthacksFilePath());
    }

    @Override
    public void saveConthacks(ReadOnlyConthacks conthacks, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        conthacksStorage.saveConthacks(conthacks, filePath);
    }

}
