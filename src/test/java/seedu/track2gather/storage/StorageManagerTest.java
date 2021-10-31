package seedu.track2gather.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.track2gather.testutil.TypicalPersons.getTypicalTrack2Gather;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.track2gather.commons.core.GuiSettings;
import seedu.track2gather.model.ReadOnlyTrack2Gather;
import seedu.track2gather.model.Track2Gather;
import seedu.track2gather.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonTrack2GatherStorage track2GatherStorage = new JsonTrack2GatherStorage(getTempFilePath("t2g"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(track2GatherStorage, userPrefsStorage);
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
    public void track2GatherReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTrack2GatherStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTrack2GatherStorageTest} class.
         */
        Track2Gather original = getTypicalTrack2Gather();
        storageManager.saveTrack2Gather(original);
        ReadOnlyTrack2Gather retrieved = storageManager.readTrack2Gather().get();
        assertEquals(original, new Track2Gather(retrieved));
    }

    @Test
    public void getTrack2GatherFilePath() {
        assertNotNull(storageManager.getTrack2GatherFilePath());
    }

}
