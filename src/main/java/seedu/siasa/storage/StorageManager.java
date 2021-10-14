package seedu.siasa.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.siasa.commons.core.LogsCenter;
import seedu.siasa.commons.exceptions.DataConversionException;
import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.ReadOnlyUserPrefs;
import seedu.siasa.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SiasaStorage siasaStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage}, {@code UserPrefStorage}
     * and {@code PolicyBookStorage}.
     */
    public StorageManager(SiasaStorage siasaStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.siasaStorage = siasaStorage;
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getSiasaFilePath() {
        return siasaStorage.getSiasaFilePath();
    }

    @Override
    public Optional<ReadOnlySiasa> readSiasa() throws DataConversionException, IOException {
        return readSiasa(siasaStorage.getSiasaFilePath());
    }

    @Override
    public Optional<ReadOnlySiasa> readSiasa(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return siasaStorage.readSiasa(filePath);
    }

    @Override
    public void saveSiasa(ReadOnlySiasa siasa) throws IOException {
        saveSiasa(siasa, siasaStorage.getSiasaFilePath());
    }

    @Override
    public void saveSiasa(ReadOnlySiasa addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        siasaStorage.saveSiasa(addressBook, filePath);
    }

}
