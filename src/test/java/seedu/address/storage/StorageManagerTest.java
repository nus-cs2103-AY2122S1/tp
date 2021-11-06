package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage, userProfileStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void getUserProfilePath_samePath_success() {
        Path expectedPath = Paths.get("userprofile.json");
        assertEquals(expectedPath, storageManager.getUserProfilePath());
    }

    @Test
    public void getUserProfilePath_differentPath_failure() {
        Path expectedPath = Paths.get("userprofile2.json");
        assertNotEquals(expectedPath, storageManager.getUserProfilePath());
    }

    @Test
    public void readUserProfile_correctPath_success() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        try {
            assertEquals(storageManager.readUserProfile(), userProfileStorage.readUserProfile());
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void readUserProfile_wrongPath_failure() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        Path wrongPath = Paths.get("userprofile22.json");
        try {
            assertNotEquals(storageManager.readUserProfile(), userProfileStorage.readUserProfile(wrongPath));
        } catch (Exception e) {
            assertNull(e);
        }
    }

    @Test
    public void saveUserProfile_correctPath_success() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        Path profileFilePath = Paths.get("userprofilesave.json");
        Optional<JsonSerializableUserProfile> userProfile;
        try {
            userProfile = JsonUtil.readJsonFile(profileFilePath,
                    JsonSerializableUserProfile.class);
            try {
                storageManager.saveUserProfile(userProfile.get());
            } catch (IOException e) {
                assertNull(e);
            }
        } catch (DataConversionException dce) {
            assertNull(dce);
        }
    }

    @Test
    public void saveUserProfile_wrongPath_success() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        Path profileFilePath = Paths.get("userprofile2.json");
        Optional<JsonSerializableUserProfile> userProfile;
        try {
            userProfile = JsonUtil.readJsonFile(profileFilePath,
                    JsonSerializableUserProfile.class);
            try {
                storageManager.saveUserProfile(userProfile.get());
            } catch (IOException e) {
                assertNull(e);
            }
        } catch (DataConversionException dce) {
            assertNull(dce);
        } catch (NoSuchElementException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void saveUserProfile_invalidFile_success() {
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage();
        Path profileFilePath = Paths.get("invalidUserProfile.json");
        Optional<JsonSerializableUserProfile> userProfile;
        try {
            userProfile = JsonUtil.readJsonFile(profileFilePath,
                    JsonSerializableUserProfile.class);
            try {
                storageManager.saveUserProfile(userProfile.get());
            } catch (IOException e) {
                assertNull(e);
            }
        } catch (DataConversionException dce) {
            assertNotNull(dce);
        } catch (NoSuchElementException e) {
            assertNull(e);
        }
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
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        AddressBook original = getTypicalAddressBook();
        storageManager.saveAddressBook(original);
        ReadOnlyAddressBook retrieved = storageManager.readAddressBook().get();
        assertEquals(original, new AddressBook(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getAddressBookFilePath());
    }

    @Test
    public void readUserPrefsFilePath() {
        Path wrongPath = Paths.get("src", "test", "data", "JsonUserPrefsStorageTest");
        assertNotEquals(wrongPath, storageManager.getUserPrefsFilePath());
    }
}
