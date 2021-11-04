package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBookKeeping;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ReadOnlyTransactionList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Inventory data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private InventoryStorage inventoryStorage;
    private UserPrefsStorage userPrefsStorage;
    private TransactionStorage transactionStorage;
    private BookKeepingStorage bookKeepingStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code InventoryStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(InventoryStorage inventoryStorage, UserPrefsStorage userPrefsStorage,
                          TransactionStorage transactionStorage, BookKeepingStorage bookKeepingStorage) {
        super();
        this.inventoryStorage = inventoryStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.transactionStorage = transactionStorage;
        this.bookKeepingStorage = bookKeepingStorage;
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


    // ================ Inventory methods ==============================

    @Override
    public Path getInventoryFilePath() {
        return inventoryStorage.getInventoryFilePath();
    }

    @Override
    public Optional<ReadOnlyInventory> readInventory() throws DataConversionException, IOException {
        return readInventory(inventoryStorage.getInventoryFilePath());
    }

    @Override
    public Optional<ReadOnlyInventory> readInventory(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return inventoryStorage.readInventory(filePath);
    }

    @Override
    public void saveInventory(ReadOnlyInventory inventory) throws IOException {
        saveInventory(inventory, inventoryStorage.getInventoryFilePath());
    }

    @Override
    public void saveInventory(ReadOnlyInventory inventory, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        inventoryStorage.saveInventory(inventory, filePath);
    }

    // ================ Transaction methods ==============================

    @Override
    public Path getTransactionFilePath() {
        return transactionStorage.getTransactionFilePath();
    }

    @Override
    public Optional<ReadOnlyTransactionList> readTransactionList() throws DataConversionException, IOException {
        return readTransactionList(transactionStorage.getTransactionFilePath());
    }

    @Override
    public Optional<ReadOnlyTransactionList> readTransactionList(Path filePath)
            throws DataConversionException, IOException {
        return transactionStorage.readTransactionList(filePath);
    }

    @Override
    public void saveTransactionList(ReadOnlyTransactionList transactionList) throws IOException {
        saveTransactionList(transactionList, transactionStorage.getTransactionFilePath());
    }

    @Override
    public void saveTransactionList(ReadOnlyTransactionList transactionList, Path filePath) throws IOException {
        transactionStorage.saveTransactionList(transactionList, filePath);
    }

    // ================ BookKeeping methods ==============================

    @Override
    public Path getBookKeepingPath() {
        return bookKeepingStorage.getBookKeepingPath();
    }

    @Override
    public Optional<ReadOnlyBookKeeping> readBookKeeping() throws DataConversionException, IOException {
        return readBookKeeping(bookKeepingStorage.getBookKeepingPath());
    }

    @Override
    public Optional<ReadOnlyBookKeeping> readBookKeeping(Path filePath) throws DataConversionException, IOException {
        return bookKeepingStorage.readBookKeeping(filePath);
    }

    @Override
    public void saveBookKeeping(ReadOnlyBookKeeping bookKeeping) throws IOException {
        saveBookKeeping(bookKeeping, bookKeepingStorage.getBookKeepingPath());
    }

    @Override
    public void saveBookKeeping(ReadOnlyBookKeeping bookKeeping, Path filePath) throws IOException {
        bookKeepingStorage.saveBookKeeping(bookKeeping, filePath);
    }
}
