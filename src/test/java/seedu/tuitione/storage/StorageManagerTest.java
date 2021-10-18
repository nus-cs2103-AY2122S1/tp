package seedu.tuitione.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.tuitione.testutil.TypicalStudents.getTypicalTuitione;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.tuitione.commons.core.GuiSettings;
import seedu.tuitione.model.ReadOnlyTuitione;
import seedu.tuitione.model.Tuitione;
import seedu.tuitione.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTuitioneStorage tuitioneStorage = new JsonTuitioneStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(tuitioneStorage, userPrefsStorage);
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
    public void tuitioneReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTuitioneStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTuitioneStorageTest} class.
         */
        Tuitione original = getTypicalTuitione();
        storageManager.saveTuitione(original);
        ReadOnlyTuitione retrieved = storageManager.readTuitione().get();
        assertEquals(original, new Tuitione(retrieved));
    }

    @Test
    public void getTuitioneFilePath() {
        assertNotNull(storageManager.getTuitioneFilePath());
    }

}
