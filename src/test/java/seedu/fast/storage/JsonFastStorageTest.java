package seedu.fast.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.fast.testutil.Assert.assertThrows;
import static seedu.fast.testutil.TypicalPersons.ALICE;
import static seedu.fast.testutil.TypicalPersons.HOON;
import static seedu.fast.testutil.TypicalPersons.IDA;
import static seedu.fast.testutil.TypicalPersons.getTypicalFast;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.fast.commons.exceptions.DataConversionException;
import seedu.fast.model.Fast;
import seedu.fast.model.ReadOnlyFast;

public class JsonFastStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFastStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFast_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFast(null));
    }

    private java.util.Optional<ReadOnlyFast> readFast(String filePath) throws Exception {
        return new JsonFastStorage(Paths.get(filePath)).readFast(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFast("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFast("notJsonFormatFast.json"));
    }

    @Test
    public void readFast_invalidPersonFast_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFast("invalidPersonFast.json"));
    }

    @Test
    public void readFast_invalidAndValidPersonFast_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFast("invalidAndValidPersonFast.json"));
    }

    @Test
    public void readAndSaveFast_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFast.json");
        Fast original = getTypicalFast();
        JsonFastStorage jsonFastStorage = new JsonFastStorage(filePath);

        // Save in new file and read back
        jsonFastStorage.saveFast(original, filePath);
        ReadOnlyFast readBack = jsonFastStorage.readFast(filePath).get();
        assertEquals(original, new Fast(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonFastStorage.saveFast(original, filePath);
        readBack = jsonFastStorage.readFast(filePath).get();
        assertEquals(original, new Fast(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonFastStorage.saveFast(original); // file path not specified
        readBack = jsonFastStorage.readFast().get(); // file path not specified
        assertEquals(original, new Fast(readBack));

    }

    @Test
    public void saveFast_nullFast_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFast(null, "SomeFile.json"));
    }

    /**
     * Saves {@code fast} at the specified {@code filePath}.
     */
    private void saveFast(ReadOnlyFast fast, String filePath) {
        try {
            new JsonFastStorage(Paths.get(filePath))
                    .saveFast(fast, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFast_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFast(new Fast(), null));
    }
}
