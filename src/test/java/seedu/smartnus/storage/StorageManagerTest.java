package seedu.smartnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.smartnus.commons.core.GuiSettings;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonSmartNusStorage smartNusStorage = new JsonSmartNusStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(smartNusStorage, userPrefsStorage);
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
    public void smartNusReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonSmartNusStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonSmartNusStorageTest} class.
         */
        SmartNus original = getTypicalSmartNus();
        storageManager.saveSmartNus(original);
        ReadOnlySmartNus retrieved = storageManager.readSmartNus().get();
        assertEquals(original, new SmartNus(retrieved));
    }

    @Test
    public void getSmartNusFilePath() {
        assertNotNull(storageManager.getSmartNusFilePath());
    }

}
