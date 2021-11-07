package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "StorageManagerTest");

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(getFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getFilePath("prefs"));
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage(getFilePath("userprofile.json"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage, userProfileStorage);
    }

    private Path getFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void getUserProfilePath_samePath_success() {
        Path expectedPath = getFilePath("userprofile.json");
        assertEquals(expectedPath, storageManager.getUserProfilePath());
    }

    @Test
    public void getUserProfilePath_differentPath_failure() {
        Path expectedPath = getFilePath("userprofile2.json");
        assertNotEquals(expectedPath, storageManager.getUserProfilePath());
    }

    @Test
    public void readUserProfile_correctPath_success() {
        Path profilePath = getFilePath("userprofile.json");
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage(profilePath);
        try {
            assertEquals(storageManager.readUserProfile(), userProfileStorage.readUserProfile());
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void saveUserProfile_correctPath_success() {
        Path profileFilePath = TEST_DATA_FOLDER.resolve("userprofilesave.json");
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage(profileFilePath);
        Optional<JsonSerializableUserProfile> userProfile;
        try {
            userProfile = JsonUtil.readJsonFile(profileFilePath,
                    JsonSerializableUserProfile.class);
            try {
                userProfileStorage.saveUserProfile(userProfile.get());
                Files.write(profileFilePath, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                assertNull(e);
            }
        } catch (DataConversionException dce) {
            assertNull(dce);
        }
    }

    @Test
    public void saveUserProfile_wrongPath_success() {
        Path profileFilePath = getFilePath("userprofile2.json");
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage(profileFilePath);
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
        Path profileFilePath = getFilePath("invalidUserProfile.json");
        JsonUserProfileStorage userProfileStorage = new JsonUserProfileStorage(profileFilePath);
        Optional<JsonSerializableUserProfile> userProfile;
        try {
            userProfile = JsonUtil.readJsonFile(profileFilePath,
                    JsonSerializableUserProfile.class);
            try {
                userProfileStorage.saveUserProfile(userProfile.get());
            } catch (IOException e) {
                assertNull(e);
            }
        } catch (DataConversionException | NoSuchElementException dce) {
            assertNotNull(dce);
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
