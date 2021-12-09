package seedu.times.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.times.commons.core.GuiSettings;
import seedu.times.model.ReadOnlyTimesTable;
import seedu.times.model.TimesTable;
import seedu.times.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTimesTableStorage timesTableStorage = new JsonTimesTableStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(timesTableStorage, userPrefsStorage);
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
    public void timesTableReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTimesTableStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTimesTableStorageTest} class.
         */
        TimesTable original = getTypicalTimesTable();
        storageManager.saveTimesTable(original);
        ReadOnlyTimesTable retrieved = storageManager.readTimesTable().get();
        assertEquals(original, new TimesTable(retrieved));
    }

    @Test
    public void getTimesTableFilePath() {
        assertNotNull(storageManager.getTimesTableFilePath());
    }

}
