package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of EzFoodie data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AccountStorage accountStorage;
    private EzFoodieStorage ezFoodieStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Constructs a {@code StorageManager} with the given {@code AccountStorage}, {@code ezFoodieStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(AccountStorage accountStorage, EzFoodieStorage ezFoodieStorage,
            UserPrefsStorage userPrefsStorage) {
        super();
        this.accountStorage = accountStorage;
        this.ezFoodieStorage = ezFoodieStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    /**
     * Returns the file path of the UserPrefs data file.
     */
    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    /**
     * Returns UserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    /**
     * Saves the given {@link seedu.address.model.ReadOnlyUserPrefs} to the storage.
     *
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================== Account methods ===============================

    /**
     * Returns the file path of the account file.
     */
    @Override
    public Path getAccountFilePath() {
        return accountStorage.getAccountFilePath();
    }

    /**
     * Returns Account as a {@link ReadOnlyAccount}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    public Optional<ReadOnlyAccount> readAccount() throws DataConversionException, IOException {
        return accountStorage.readAccount();
    }

    /**
     * @see #getAccountFilePath()
     */
    @Override
    public Optional<ReadOnlyAccount> readAccount(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read account from file: " + filePath);
        return accountStorage.readAccount(filePath);
    }

    /**
     * Saves the given {@link ReadOnlyAccount} to the storage.
     *
     * @param account cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveAccount(ReadOnlyAccount account) throws IOException {
        accountStorage.saveAccount(account);
    }

    /**
     * @see #saveAccount(ReadOnlyAccount)
     */
    @Override
    public void saveAccount(ReadOnlyAccount account, Path filePath) throws IOException {
        logger.fine("Attempting to write to account file: " + filePath);
        accountStorage.saveAccount(account, filePath);
    }

    // ================== EzFoodie methods ===============================

    /**
     * Returns the file path of the data file.
     */
    @Override
    public Path getEzFoodieFilePath() {
        return ezFoodieStorage.getEzFoodieFilePath();
    }

    /**
     * Returns ezFoodie data as a {@link ReadOnlyEzFoodie}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    @Override
    public Optional<ReadOnlyEzFoodie> readEzFoodie() throws DataConversionException, IOException {
        return readEzFoodie(ezFoodieStorage.getEzFoodieFilePath());
    }

    /**
     * @see #getEzFoodieFilePath()
     */
    @Override
    public Optional<ReadOnlyEzFoodie> readEzFoodie(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return ezFoodieStorage.readEzFoodie(filePath);
    }

    /**
     * Saves the given {@link ReadOnlyEzFoodie} to the storage.
     *
     * @param ezFoodie cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveEzFoodie(ReadOnlyEzFoodie ezFoodie) throws IOException {
        saveEzFoodie(ezFoodie, ezFoodieStorage.getEzFoodieFilePath());
    }

    /**
     * @see #saveEzFoodie(ReadOnlyEzFoodie)
     */
    @Override
    public void saveEzFoodie(ReadOnlyEzFoodie ezFoodie, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        ezFoodieStorage.saveEzFoodie(ezFoodie, filePath);
    }

}
