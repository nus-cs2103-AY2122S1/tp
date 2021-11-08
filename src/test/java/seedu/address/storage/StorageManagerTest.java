package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.PathUtil.addToPath;
import static seedu.address.testutil.TypicalBookkeeping.getTypicalBookkeeping;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;
import static seedu.address.testutil.TypicalOrders.getTypicalTransactionList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.BookKeeping;
import seedu.address.model.Inventory;
import seedu.address.model.ReadOnlyBookKeeping;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.ReadOnlyTransactionList;
import seedu.address.model.TransactionList;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonInventoryStorage jsonInventoryStorage =
                new JsonInventoryStorage(addToPath(testFolder, "ab"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(addToPath(testFolder, "prefs"));
        JsonBookKeepingStorage bookKeepingStorage =
                new JsonBookKeepingStorage(addToPath(testFolder, "bookKeeping"));
        JsonTransactionStorage transactionStorage =
                new JsonTransactionStorage(addToPath(testFolder, "transactions"));
        storageManager = new StorageManager(jsonInventoryStorage, userPrefsStorage,
                transactionStorage, bookKeepingStorage);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void getUserPrefsFilePath() {
        assertNotNull(storageManager.getUserPrefsFilePath());
    }

    @Test
    public void inventoryReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonInventoryStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonInventoryStorageTest} class.
         */
        Inventory original = getTypicalInventory();
        storageManager.saveInventory(original);
        ReadOnlyInventory retrieved = storageManager.readInventory().get();
        assertEquals(original, new Inventory(retrieved));
    }

    @Test
    public void getInventoryFilePath() {
        assertNotNull(storageManager.getInventoryFilePath());
    }

    @Test
    public void bookKeepingReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonBookKeepingStorage} class.
         * More extensive testing of Bookkeeping saving/reading is done in {@link JsonBookkeepingStorageTest} class.
         */
        BookKeeping original = getTypicalBookkeeping();
        storageManager.saveBookKeeping(original);
        ReadOnlyBookKeeping retrieved = storageManager.readBookKeeping().get();
        assertEquals(original, new BookKeeping(retrieved));
    }

    @Test
    public void getBookKeepingFilePath() {
        assertNotNull(storageManager.getBookKeepingPath());
    }

    @Test
    public void transactionReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTransactionStorage} class.
         * More extensive testing of TransactionStorage saving/reading is done in
         * {@link JsonTransactionStorageTest} class.
         */
        TransactionList original = getTypicalTransactionList();
        storageManager.saveTransactionList(original);
        ReadOnlyTransactionList retrieved = storageManager.readTransactionList().get();
        boolean test = original.equalTestsTransactionLists(new TransactionList(retrieved));
        assertTrue(test);
    }

    @Test
    public void getTransactionFilePath() {
        assertNotNull(storageManager.getTransactionFilePath());
    }

}
