package seedu.address.storage.guest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.guest.TypicalGuests.JEONGYEON_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalGuestBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.guest.ReadOnlyGuestBook;

public class JsonGuestBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Guest", "JsonGuestBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readGuestBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readGuestBook(null));
    }

    private java.util.Optional<ReadOnlyGuestBook> readGuestBook(String filePath) throws Exception {
        return new JsonGuestBookStorage(Paths.get(filePath)).readGuestBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readGuestBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readGuestBook("notJsonFormatGuestBook.json"));
    }

    @Test
    public void readGuestBook_invalidGuestBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readGuestBook("invalidGuestBook.json"));
    }

    @Test
    public void readGuestBook_invalidAndValidGuestBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readGuestBook("invalidAndValidGuestBook.json"));
    }

    @Test
    public void readAndSaveGuestBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempGuestBook.json");
        GuestBook original = getTypicalGuestBook();
        JsonGuestBookStorage jsonGuestBookStorage = new JsonGuestBookStorage(filePath);

        // Save in new file and read back
        jsonGuestBookStorage.saveGuestBook(original, filePath);
        ReadOnlyGuestBook readBack = jsonGuestBookStorage.readGuestBook(filePath).get();
        assertEquals(original, new GuestBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addGuest(JEONGYEON_GUEST);
        original.removeGuest(JEONGYEON_GUEST);
        jsonGuestBookStorage.saveGuestBook(original, filePath);
        readBack = jsonGuestBookStorage.readGuestBook(filePath).get();
        assertEquals(original, new GuestBook(readBack));

        // Save and read without specifying file path
        original.addGuest(JEONGYEON_GUEST);
        jsonGuestBookStorage.saveGuestBook(original); // file path not specified
        readBack = jsonGuestBookStorage.readGuestBook().get(); // file path not specified
        assertEquals(original, new GuestBook(readBack));
    }

    @Test
    public void saveGuestBook_nullGuestBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGuestBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code guestBook} at the specified {@code filePath}.
     */
    private void saveGuestBook(ReadOnlyGuestBook guestBook, String filePath) {
        try {
            new JsonGuestBookStorage(Paths.get(filePath))
                    .saveGuestBook(guestBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveGuestBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveGuestBook(new GuestBook(), null));
    }
}
