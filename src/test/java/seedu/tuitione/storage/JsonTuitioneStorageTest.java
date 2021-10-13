package seedu.tuitione.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.tuitione.testutil.Assert.assertThrows;
import static seedu.tuitione.testutil.TypicalStudents.ALICE;
import static seedu.tuitione.testutil.TypicalStudents.HOON;
import static seedu.tuitione.testutil.TypicalStudents.IDA;
import static seedu.tuitione.testutil.TypicalStudents.getTypicalTuitione;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.tuitione.commons.exceptions.DataConversionException;
import seedu.tuitione.model.ReadOnlyTuitione;
import seedu.tuitione.model.Tuitione;

public class JsonTuitioneStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTuitioneStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTuitione_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTuitione(null));
    }

    private java.util.Optional<ReadOnlyTuitione> readTuitione(String filePath) throws Exception {
        return new JsonTuitioneStorage(Paths.get(filePath)).readTuitione(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTuitione("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTuitione("notJsonFormatTuitione.json"));
    }

    @Test
    public void readTuitione_invalidStudentTuitione_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTuitione("invalidStudentTuitione.json"));
    }

    @Test
    public void readTuitione_invalidAndValidStudentTuitione_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTuitione("invalidAndValidStudentTuitione.json"));
    }

    @Test
    public void readAndSaveTuitione_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTuitione.json");
        Tuitione original = getTypicalTuitione();
        JsonTuitioneStorage jsonTuitioneStorage = new JsonTuitioneStorage(filePath);

        // Save in new file and read back
        jsonTuitioneStorage.saveTuitione(original, filePath);
        ReadOnlyTuitione readBack = jsonTuitioneStorage.readTuitione(filePath).get();
        assertEquals(original, new Tuitione(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonTuitioneStorage.saveTuitione(original, filePath);
        readBack = jsonTuitioneStorage.readTuitione(filePath).get();
        assertEquals(original, new Tuitione(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonTuitioneStorage.saveTuitione(original); // file path not specified
        readBack = jsonTuitioneStorage.readTuitione().get(); // file path not specified
        assertEquals(original, new Tuitione(readBack));

    }

    @Test
    public void saveTuitione_nullTuitione_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTuitione(null, "SomeFile.json"));
    }

    /**
     * Saves {@code tuitione} at the specified {@code filePath}.
     */
    private void saveTuitione(ReadOnlyTuitione tuitione, String filePath) {
        try {
            new JsonTuitioneStorage(Paths.get(filePath))
                    .saveTuitione(tuitione, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTuitione_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTuitione(new Tuitione(), null));
    }
}
