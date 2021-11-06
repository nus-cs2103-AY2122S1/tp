package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.PathUtil.addToPath;
import static seedu.address.testutil.TypicalBookkeeping.getTypicalBookkeeping;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.BookKeeping;
import seedu.address.model.ReadOnlyBookKeeping;

public class JsonBookkeepingStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonBookkeepingStorageTest");

    @TempDir
    public Path testFolder;

    private java.util.Optional<ReadOnlyBookKeeping> readBookkeeping(String filePath) throws Exception {
        return new JsonBookKeepingStorage(Paths.get(filePath))
                .readBookKeeping(addToPath(TEST_DATA_FOLDER, filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    /**
     * Saves {@code bookkeeping} at the specified {@code filePath}.
     */
    private void saveBookkeeping(ReadOnlyBookKeeping bookKeeping, String filePath) {
        try {
            new JsonBookKeepingStorage(Paths.get(filePath))
                    .saveBookKeeping(bookKeeping, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void readBookkeeping_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readBookkeeping(null));
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readBookkeeping("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readBookkeeping("notJsonFormatBookkeeping.json"));
    }

    @Test
    public void readBookkeeping_invalidValueBookkeeping_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readBookkeeping("invalidValueBookkeeping.json"));
    }

    @Test
    public void readAndSaveBookkeeping_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempBookkeeping.json");
        BookKeeping original = getTypicalBookkeeping();
        JsonBookKeepingStorage jsonBookKeepingStorage = new JsonBookKeepingStorage(filePath);

        // Save in new file and read back
        jsonBookKeepingStorage.saveBookKeeping(original, filePath);
        ReadOnlyBookKeeping readBack = jsonBookKeepingStorage.readBookKeeping(filePath).get();
        assertEquals(original, new BookKeeping(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addCost(5.0, 1);
        original.addRevenue(10.0, 1);
        jsonBookKeepingStorage.saveBookKeeping(original, filePath);
        readBack = jsonBookKeepingStorage.readBookKeeping(filePath).get();
        assertEquals(original, new BookKeeping(readBack));

        // Save and read without specifying file path
        original.addCost(5.0, 1);
        jsonBookKeepingStorage.saveBookKeeping(original); // file path not specified
        readBack = jsonBookKeepingStorage.readBookKeeping().get(); // file path not specified
        assertEquals(original, new BookKeeping(readBack));

    }

    @Test
    public void saveBookkeeping_nullBookkeeping_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBookkeeping(null, "SomeFile.json"));
    }

    @Test
    public void saveBookkeeping_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveBookkeeping(new BookKeeping(), null));
    }
}
