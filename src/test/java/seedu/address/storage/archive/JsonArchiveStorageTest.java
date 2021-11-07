package seedu.address.storage.archive;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.guest.TypicalGuests.JEONGYEON_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalArchive;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.ReadOnlyGuestBook;

public class JsonArchiveStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Archive",
           "JsonArchiveStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readGuestBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readArchive(null));
    }

    private java.util.Optional<ReadOnlyGuestBook> readArchive(String filePath) throws Exception {
        return new JsonArchiveStorage(Paths.get(filePath)).readArchive(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readArchive("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readArchive("notJsonFormatArchive.json"));
    }

    @Test
    public void readArchive_invalidArchive_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readArchive("invalidArchive.json"));
    }

    @Test
    public void readArchive_invalidAndValidArchive_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readArchive("invalidAndValidArchive.json"));
    }

    @Test
    public void readAndSaveArchive_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempGuestBook.json");
        Archive original = getTypicalArchive();
        JsonArchiveStorage jsonArchiveStorage = new JsonArchiveStorage(filePath);

        // Save in new file and read back
        jsonArchiveStorage.saveArchive(original, filePath);
        ReadOnlyGuestBook readBack = jsonArchiveStorage.readArchive(filePath).get();
        assertEquals(original, new Archive(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addGuest(JEONGYEON_GUEST);
        original.removeGuest(JEONGYEON_GUEST);
        jsonArchiveStorage.saveArchive(original, filePath);
        readBack = jsonArchiveStorage.readArchive(filePath).get();
        assertEquals(original, new Archive(readBack));

        // Save and read without specifying file path
        original.addGuest(JEONGYEON_GUEST);
        jsonArchiveStorage.saveArchive(original); // file path not specified
        readBack = jsonArchiveStorage.readArchive().get(); // file path not specified
        assertEquals(original, new Archive(readBack));
    }

    @Test
    public void saveArchive_nullArchive_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveArchive(null, "SomeFile.json"));
    }

    /**
     * Saves {@code archive} at the specified {@code filePath}.
     */
    private void saveArchive(ReadOnlyGuestBook archive, String filePath) {
        try {
            new JsonArchiveStorage(Paths.get(filePath))
                    .saveArchive(archive, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveArchive_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveArchive(new Archive(), null));
    }
}

