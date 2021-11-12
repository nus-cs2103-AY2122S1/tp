package seedu.anilist.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnimeList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.anilist.commons.core.GuiSettings;
import seedu.anilist.model.AnimeList;
import seedu.anilist.model.ReadOnlyAnimeList;
import seedu.anilist.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonAnimeListStorage animeListStorage = new JsonAnimeListStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(animeListStorage, userPrefsStorage);
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
    public void animeListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAnimeListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAnimeListStorageTest} class.
         */
        AnimeList original = getTypicalAnimeList();
        storageManager.saveAnimeList(original);
        ReadOnlyAnimeList retrieved = storageManager.readAnimeList().get();
        assertEquals(original, new AnimeList(retrieved));
    }

    @Test
    public void getAnimeListFilePath() {
        assertNotNull(storageManager.getAnimeListFilePath());
    }

}
