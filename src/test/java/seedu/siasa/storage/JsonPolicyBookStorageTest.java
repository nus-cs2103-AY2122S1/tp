package seedu.siasa.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.siasa.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.siasa.commons.exceptions.DataConversionException;
import seedu.siasa.model.ReadOnlySiasa;
import seedu.siasa.model.Siasa;

public class JsonPolicyBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPolicyBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPolicyBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPolicyBook(null));
    }

    private java.util.Optional<ReadOnlySiasa> readPolicyBook(String filePath) throws Exception {
        return new JsonPolicyBookStorage(Paths.get(filePath)).readPolicyBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPolicyBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPolicyBook("notJsonFormatPolicyBook.json"));
    }

    @Test
    public void readPolicyBook_invalidPolicyBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPolicyBook("invalidPolicyBook.json"));
    }

    @Test
    public void readPolicyBook_invalidAndValidPolicyBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPolicyBook("invalidAndValidPolicyBook.json"));
    }

    /*
    TODO: Fix test
    @Test
    public void readAndSavePolicyBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPolicyBook.json");
        Siasa original = getTypicalPolicyBook();
        JsonPolicyBookStorage jsonPolicyBookStorage = new JsonPolicyBookStorage(filePath);

        // Save in new file and read back
        jsonPolicyBookStorage.savePolicyBook(original, filePath);
        ReadOnlySiasa readBack = jsonPolicyBookStorage.readPolicyBook(filePath).get();
        assertEquals(original, new Siasa(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPolicy(FULL_LIFE);
        original.removePolicy(FULL_LIFE);
        jsonPolicyBookStorage.savePolicyBook(original, filePath);
        readBack = jsonPolicyBookStorage.readPolicyBook(filePath).get();
        assertEquals(original, new Siasa(readBack));

        // Save and read without specifying file path
        original.addPolicy(FULL_LIFE);
        jsonPolicyBookStorage.savePolicyBook(original); // file path not specified
        readBack = jsonPolicyBookStorage.readPolicyBook().get(); // file path not specified
        assertEquals(original, new Siasa(readBack));

    }
     */

    @Test
    public void savePolicyBook_nullPolicyBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePolicyBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void savePolicyBook(ReadOnlySiasa policyBook, String filePath) {
        try {
            new JsonPolicyBookStorage(Paths.get(filePath))
                .savePolicyBook(policyBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePolicyBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePolicyBook(new Siasa(), null));
    }
}
