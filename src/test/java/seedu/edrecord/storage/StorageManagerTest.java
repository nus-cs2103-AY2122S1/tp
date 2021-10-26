package seedu.edrecord.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.edrecord.testutil.TypicalModules.getTypicalModuleSystem;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.edrecord.commons.core.GuiSettings;
import seedu.edrecord.model.UserPrefs;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleSystem;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;
import seedu.edrecord.storage.module.JsonModuleSystemStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonEdRecordStorage edRecordStorage = new JsonEdRecordStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonModuleSystemStorage moduleSystemStorage = new JsonModuleSystemStorage(getTempFilePath("ms"));
        storageManager = new StorageManager(edRecordStorage, moduleSystemStorage, userPrefsStorage);
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
    public void getPrefsFilePath() {
        assertNotNull(storageManager.getUserPrefsFilePath());
    }

    // TODO: Add assignments to a person's modules in json format, so that this tests can pass.
    /*     @Test
    public void edRecordReadSave() throws Exception {
         // Note: This is an integration test that verifies the StorageManager is properly wired to the
         // {@link JsonEdRecordStorage} class.
         // More extensive testing of EdRecord saving/reading is done in {@link JsonEdRecordStorageTest} class.
        EdRecord original = getTypicalEdRecord();
        setTypicalModuleSystem();
        storageManager.saveEdRecord(original);
        ReadOnlyEdRecord retrieved = storageManager.readEdRecord().get();
        assertEquals(original, new EdRecord(retrieved));
    }
     */
    @Test
    public void getEdRecordFilePath() {
        assertNotNull(storageManager.getEdRecordFilePath());
    }

    @Test
    public void moduleSystemReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonModuleSystemStorage} class.
         * More extensive testing of ModuleSystem saving/reading is done in {@link JsonModuleSystemStorageTest} class.
         */
        ModuleSystem original = getTypicalModuleSystem();
        storageManager.saveModuleSystem(original);

        // Reset the MODULE_SYSTEM static variable before reading data
        Module.MODULE_SYSTEM.clear();

        ReadOnlyModuleSystem retrieved = storageManager.readModuleSystem().get();
        assertEquals(original, new ModuleSystem(retrieved));
    }

    @Test
    public void getModuleSystemFilePath() {
        assertNotNull(storageManager.getModuleSystemFilePath());
    }
}
