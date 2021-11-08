package seedu.awe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.awe.testutil.TypicalPersons.getTypicalAwe;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.awe.commons.core.GuiSettings;
import seedu.awe.model.Awe;
import seedu.awe.model.ReadOnlyAwe;
import seedu.awe.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonAweStorage aweStorage = new JsonAweStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(aweStorage, userPrefsStorage);
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
    public void aweReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAweStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAweStorageTest} class.
         */
        Awe original = getTypicalAwe();
        storageManager.saveAwe(original);
        ReadOnlyAwe retrieved = storageManager.readAwe().get();
        assertEquals(original, new Awe(retrieved));
    }

    @Test
    public void getAweFilePath() {
        assertNotNull(storageManager.getAweFilePath());
    }

}
