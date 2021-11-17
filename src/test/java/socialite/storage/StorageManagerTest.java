package socialite.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import socialite.commons.core.GuiSettings;
import socialite.model.ContactList;
import socialite.model.ReadOnlyContactList;
import socialite.model.UserPrefs;
import socialite.testutil.TypicalPersons;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonContactListStorage contactListStorage = new JsonContactListStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonCommandHistoryStorage commandHistoryStorage = new JsonCommandHistoryStorage(getTempFilePath("ch"));
        ProfilePictureStorage profilePictureStorage = ProfilePictureStorageManager.getInstance();
        storageManager = new StorageManager(
                contactListStorage, userPrefsStorage, commandHistoryStorage, profilePictureStorage);
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
    public void contactListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonContactListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonContactListStorageTest} class.
         */
        ContactList original = TypicalPersons.getTypicalContactList();
        storageManager.saveContactList(original);
        ReadOnlyContactList retrieved = storageManager.readContactList().get();
        assertEquals(original, new ContactList(retrieved));
    }

    @Test
    public void getContactListFilePath() {
        assertNotNull(storageManager.getContactListFilePath());
    }

    @Test
    public void getProfilePictureFilePath() {
        assertNotNull(storageManager.getProfilePictureFolderPath());
    }

    @Test
    public void addAndDeleteProfilePicture() throws Exception {
        File testFile = Paths.get("src", "test", "data", "profilepictures", "simu.jpeg").toFile();
        storageManager.saveProfilePicture(testFile, "testPicture");
        String[] files = ProfilePictureStorageManager.PROFILE_PIC_FOLDER_PATH.toFile().list();
        assertTrue(Arrays.stream(files).anyMatch(file -> file.equals("testPicture")));
        storageManager.deleteProfilePicture(Path.of("testPicture"));
        files = ProfilePictureStorageManager.PROFILE_PIC_FOLDER_PATH.toFile().list();
        if (files != null) {
            assertTrue(Arrays.stream(files).noneMatch(file -> file.equals("testPicture")));
        }
    }

}
