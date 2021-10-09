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
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private PolicyBookStorage policyBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage}, {@code UserPrefStorage}
     * and {@code PolicyBookStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage,
                          PolicyBookStorage policyBookStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.policyBookStorage = policyBookStorage;
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
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlySiasa> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlySiasa> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlySiasa addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlySiasa addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ PolicyBook methods ==============================

    @Override
    public Path getPolicyBookFilePath() {
        return policyBookStorage.getPolicyBookFilePath();
    }

    @Override
    public Optional<ReadOnlySiasa> readPolicyBook() throws DataConversionException, IOException {
        return readPolicyBook(policyBookStorage.getPolicyBookFilePath());
    }

    @Override
    public Optional<ReadOnlySiasa> readPolicyBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return policyBookStorage.readPolicyBook(filePath);
    }

    @Override
    public void savePolicyBook(ReadOnlySiasa policyBook) throws IOException {
        savePolicyBook(policyBook, policyBookStorage.getPolicyBookFilePath());
    }

    @Override
    public void savePolicyBook(ReadOnlySiasa policyBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        policyBookStorage.savePolicyBook(policyBook, filePath);
    }

}
