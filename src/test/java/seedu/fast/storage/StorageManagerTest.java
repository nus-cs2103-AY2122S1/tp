package seedu.fast.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.fast.testutil.TypicalPersons.getTypicalFast;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.fast.commons.core.GuiSettings;
import seedu.fast.model.Fast;
import seedu.fast.model.ReadOnlyFast;
import seedu.fast.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFastStorage fastStorage = new JsonFastStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(fastStorage, userPrefsStorage);
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
    public void fastReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFastStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonFastStorageTest} class.
         */
        Fast original = getTypicalFast();
        storageManager.saveFast(original);
        ReadOnlyFast retrieved = storageManager.readFast().get();
        assertEquals(original, new Fast(retrieved));
    }

    @Test
    public void getFastFilePath() {
        assertNotNull(storageManager.getFastFilePath());
    }

}
