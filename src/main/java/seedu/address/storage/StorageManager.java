package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyOrderBook;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private TaskBookStorage taskBookStorage;
    private OrderBookStorage orderBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     *  * Creates a {@code StorageManager} with the given {@code AddressBookStorage},
     *  {@code TaskBookStorage}, {@code OrderBookStorage}, and {@code UserPrefStorage}.
     */

    public StorageManager(AddressBookStorage addressBookStorage, TaskBookStorage taskListStorage,
                          OrderBookStorage orderBookStorage, UserPrefsStorage userPrefsStorage) {

        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.taskBookStorage = taskListStorage;
        this.orderBookStorage = orderBookStorage;
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

    // ================ UniqueTaskList methods ==============================

    @Override
    public Path getTaskListFilePath() {
        return taskBookStorage.getTaskListFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTaskList() throws DataConversionException, IOException {
        return readTaskList(taskBookStorage.getTaskListFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTaskList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskBookStorage.readTaskList(filePath);
    }

    @Override
    public void saveTaskBook(ReadOnlyTaskBook taskList) throws IOException {
        saveTaskBook(taskList, taskBookStorage.getTaskListFilePath());
    }

    @Override
    public void saveTaskBook(ReadOnlyTaskBook taskList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskBookStorage.saveTaskBook(taskList, filePath);
    }

    // ================ OrderBook methods ==============================

    @Override
    public Path getOrderFilePath() {
        return orderBookStorage.getOrderFilePath();
    }

    @Override
    public Optional<ReadOnlyOrderBook> readOrderBook() throws DataConversionException, IOException {
        return readOrderBook(orderBookStorage.getOrderFilePath());
    }

    @Override
    public Optional<ReadOnlyOrderBook> readOrderBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return orderBookStorage.readOrderBook(filePath);
    }

    @Override
    public void saveOrderBook(ReadOnlyOrderBook orderBook) throws IOException {
        saveOrderBook(orderBook, orderBookStorage.getOrderFilePath());
    }

    @Override
    public void saveOrderBook(ReadOnlyOrderBook orderBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        orderBookStorage.saveOrderBook(orderBook, filePath);
    }

}
