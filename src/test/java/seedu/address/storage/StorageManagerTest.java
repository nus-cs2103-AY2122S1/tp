package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalAccount.getTypicalAccount;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Account;
import seedu.address.model.EzFoodie;
import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.UserPrefs;

/**
 * Tests the functionalities of
 * {@code StorageManager}.
 */
public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonAccountStorage accountStorage = new JsonAccountStorage(getTempFilePath("a"));
        JsonEzFoodieStorage ezFoodieStorage = new JsonEzFoodieStorage(getTempFilePath("ef"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(accountStorage, ezFoodieStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
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
    public void ezFoodieReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonEzFoodieStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonEzFoodieStorageTest} class.
         */
        EzFoodie original = getTypicalEzFoodie();
        storageManager.saveEzFoodie(original);
        ReadOnlyEzFoodie retrieved = storageManager.readEzFoodie().get();
        assertEquals(original, new EzFoodie(retrieved));
    }

    @Test
    public void getEzFoodieFilePath() {
        assertNotNull(storageManager.getEzFoodieFilePath());
    }

    @Test
    public void accountReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAccountStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAccountStorageTest} class.
         */
        Account original = getTypicalAccount();
        storageManager.saveAccount(original);
        ReadOnlyAccount retrieved = storageManager.readAccount().get();
        assertEquals(original, new Account(retrieved));
    }

    @Test
    public void getAccountFilePath() {
        assertNotNull(storageManager.getAccountFilePath());
    }

}
