package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlySalesOrderBook;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private TaskBookStorage taskListStorage;
    private SalesOrderBookStorage salesOrderBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */

    public StorageManager(AddressBookStorage addressBookStorage, TaskBookStorage taskListStorage,
                          SalesOrderBookStorage salesOrderBookStorage, UserPrefsStorage userPrefsStorage) {

        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.taskListStorage = taskListStorage;
        this.salesOrderBookStorage = salesOrderBookStorage;
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
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ TaskList methods ==============================

    @Override
    public Path getTaskListFilePath() {
        return taskListStorage.getTaskListFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTaskList() throws DataConversionException, IOException {
        return readTaskList(taskListStorage.getTaskListFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTaskList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskListStorage.readTaskList(filePath);
    }

    @Override
    public void saveTaskBook(ReadOnlyTaskBook taskList) throws IOException {
        saveTaskBook(taskList, taskListStorage.getTaskListFilePath());
    }

    @Override
    public void saveTaskBook(ReadOnlyTaskBook taskList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskListStorage.saveTaskBook(taskList, filePath);
    }

    // ================ SalesOrderBook methods ==============================

    @Override
    public Path getSalesOrderFilePath() {
        return salesOrderBookStorage.getSalesOrderFilePath();
    }

    @Override
    public Optional<ReadOnlySalesOrderBook> readSalesOrderBook() throws DataConversionException, IOException {
        return readSalesOrderBook(salesOrderBookStorage.getSalesOrderFilePath());
    }

    @Override
    public Optional<ReadOnlySalesOrderBook> readSalesOrderBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return salesOrderBookStorage.readSalesOrderBook(filePath);
    }

    @Override
    public void saveSalesOrderBook(ReadOnlySalesOrderBook salesOrderBook) throws IOException {
        saveSalesOrderBook(salesOrderBook, salesOrderBookStorage.getSalesOrderFilePath());
    }

    @Override
    public void saveSalesOrderBook(ReadOnlySalesOrderBook salesOrderBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        salesOrderBookStorage.saveSalesOrderBook(salesOrderBook, filePath);
    }

}
