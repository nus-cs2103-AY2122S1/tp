package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalGuestBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.guest.ReadOnlyGuestBook;
import seedu.address.storage.archive.JsonArchiveStorage;
import seedu.address.storage.guest.JsonGuestBookStorage;
import seedu.address.storage.vendor.JsonVendorBookStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonGuestBookStorage guestBookStorage = new JsonGuestBookStorage(getTempFilePath("gb"));
        JsonVendorBookStorage vendorBookStorage = new JsonVendorBookStorage(getTempFilePath("vb"));
        JsonArchiveStorage archiveStorage = new JsonArchiveStorage(getTempFilePath("a"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(guestBookStorage, vendorBookStorage, userPrefsStorage, archiveStorage);
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
    public void guestBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonGuestBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonGuestBookStorageTest} class.
         */
        GuestBook original = getTypicalGuestBook();
        storageManager.saveGuestBook(original);
        ReadOnlyGuestBook retrieved = storageManager.readGuestBook().get();
        assertEquals(original, new GuestBook(retrieved));
    }

    @Test
    public void getGuestBookFilePath() {
        assertNotNull(storageManager.getGuestBookFilePath());
    }

}
