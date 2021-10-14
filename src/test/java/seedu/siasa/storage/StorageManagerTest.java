package seedu.siasa.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.siasa.testutil.TypicalSiasa.getTypicalSiasa;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.siasa.commons.core.GuiSettings;
import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.Siasa;
import seedu.siasa.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonPolicyBookStorage policyBookStorage = new JsonPolicyBookStorage(getTempFilePath("pb"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage, policyBookStorage);
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
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        Siasa original = getTypicalSiasa();
        storageManager.saveAddressBook(original);
        ReadOnlySiasa retrieved = storageManager.readAddressBook().get();
        assertEquals(original, new Siasa(retrieved));
    }

    /* TODO: Fix this test case
    @Test
    public void policyBookReadSave() throws Exception {

         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPolicyBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPolicyBookStorageTest} class.

        Siasa original = getTypicalPolicyBook();
        storageManager.savePolicyBook(original);
        ReadOnlySiasa retrieved = storageManager.readPolicyBook().get();

        assertEquals(original, new Siasa(retrieved));
    }
    */

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getAddressBookFilePath());
    }

    @Test
    public void getPolicyBookFilePath() {
        assertNotNull(storageManager.getPolicyBookFilePath());
    }
}
