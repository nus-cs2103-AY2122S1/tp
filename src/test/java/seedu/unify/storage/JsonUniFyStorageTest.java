package seedu.unify.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.unify.testutil.Assert.assertThrows;
import static seedu.unify.testutil.TypicalTasks.ALICE;
import static seedu.unify.testutil.TypicalTasks.HOON;
import static seedu.unify.testutil.TypicalTasks.IDA;
import static seedu.unify.testutil.TypicalTasks.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.unify.commons.exceptions.DataConversionException;
import seedu.unify.model.ReadOnlyUniFy;
import seedu.unify.model.UniFy;

public class JsonUniFyStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUniFyStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readUniFy_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUniFy(null));
    }

    private java.util.Optional<ReadOnlyUniFy> readUniFy(String filePath) throws Exception {
        return new JsonUniFyStorage(Paths.get(filePath)).readUniFy(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readUniFy("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readUniFy("notJsonFormatUniFy.json"));
    }

    @Test
    public void readUniFy_invalidTaskUniFy_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readUniFy("invalidTaskUniFy.json"));
    }

    @Test
    public void readUniFy_invalidAndValidTaskUniFy_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readUniFy("invalidAndValidTaskUniFy.json"));
    }

    @Test
    public void readAndSaveUniFy_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempUniFy.json");
        UniFy original = getTypicalAddressBook();
        JsonUniFyStorage jsonUniFyStorage = new JsonUniFyStorage(filePath);

        // Save in new file and read back
        jsonUniFyStorage.saveUniFy(original, filePath);
        ReadOnlyUniFy readBack = jsonUniFyStorage.readUniFy(filePath).get();
        assertEquals(original, new UniFy(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addTask(HOON);
        original.removeTask(ALICE);
        jsonUniFyStorage.saveUniFy(original, filePath);
        readBack = jsonUniFyStorage.readUniFy(filePath).get();
        assertEquals(original, new UniFy(readBack));

        // Save and read without specifying file path
        original.addTask(IDA);
        jsonUniFyStorage.saveUniFy(original); // file path not specified
        readBack = jsonUniFyStorage.readUniFy().get(); // file path not specified
        assertEquals(original, new UniFy(readBack));

    }

    @Test
    public void saveUniFy_nullUniFy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUniFy(null, "SomeFile.json"));
    }

    /**
     * Saves {@code uniFy} at the specified {@code filePath}.
     */
    private void saveUniFy(ReadOnlyUniFy uniFy, String filePath) {
        try {
            new JsonUniFyStorage(Paths.get(filePath))
                    .saveUniFy(uniFy, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveUniFy_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUniFy(new UniFy(), null));
    }
}
