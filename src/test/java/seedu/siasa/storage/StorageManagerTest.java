package seedu.siasa.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.testutil.TypicalSiasa.getTypicalSiasa;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.siasa.commons.core.GuiSettings;
import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.Siasa;
import seedu.siasa.model.UserPrefs;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.Policy;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonSiasaStorage addressBookStorage = new JsonSiasaStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getSiasaFilePath());
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
    public void siasaReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        Siasa original = getTypicalSiasa();
        storageManager.saveSiasa(original);
        ReadOnlySiasa retrieved = storageManager.readSiasa().get();
        for (Contact contact : retrieved.getContactList()) {
            assertTrue(original.hasContact(contact));
        }
        for (Policy policy : retrieved.getPolicyList()) {
            assertTrue(original.hasPolicy(policy));
        }
    }

}
