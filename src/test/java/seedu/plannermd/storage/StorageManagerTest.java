package seedu.plannermd.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.plannermd.commons.core.GuiSettings;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.ReadOnlyPlannerMd;
import seedu.plannermd.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonPlannerMdStorage plannerMdStorage = new JsonPlannerMdStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(plannerMdStorage, userPrefsStorage);
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
    public void plannerMdReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPlannerMdStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPlannerMdStorageTest} class.
         */
        PlannerMd original = getTypicalPlannerMd();
        storageManager.savePlannerMd(original);
        ReadOnlyPlannerMd retrieved = storageManager.readPlannerMd().get();
        assertEquals(original, new PlannerMd(retrieved));
    }

    @Test
    public void getPlannerMdFilePath() {
        assertNotNull(storageManager.getPlannerMdFilePath());
    }

}
