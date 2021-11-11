package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.HOON;
import static seedu.address.testutil.TypicalStudents.IDA;
import static seedu.address.testutil.TypicalStudents.getTypicalClassmate;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Classmate;
import seedu.address.model.ReadOnlyClassmate;

public class JsonClassmateStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonClassmateStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readClassmate_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readClassmate(null));
    }

    private java.util.Optional<ReadOnlyClassmate> readClassmate(String filePath) throws Exception {
        return new JsonClassmateStorage(Paths.get(filePath)).readClassmate(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readClassmate("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readClassmate("notJsonFormatClassmate.json"));
    }

    @Test
    public void readClassmate_invalidStudentClassmate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readClassmate("invalidStudentClassmate.json"));
    }

    @Test
    public void readClassmate_invalidAndValidStudentClassmate_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readClassmate("invalidAndValidStudentClassmate.json"));
    }

    @Test
    public void readAndSaveClassmate_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempClassmate.json");
        Classmate original = getTypicalClassmate();
        JsonClassmateStorage jsonClassmateStorage = new JsonClassmateStorage(filePath);

        // Save in new file and read back
        jsonClassmateStorage.saveClassmate(original, filePath);
        ReadOnlyClassmate readBack = jsonClassmateStorage.readClassmate(filePath).get();
        assertEquals(original, new Classmate(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonClassmateStorage.saveClassmate(original, filePath);
        readBack = jsonClassmateStorage.readClassmate(filePath).get();
        assertEquals(original, new Classmate(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonClassmateStorage.saveClassmate(original); // file path not specified
        readBack = jsonClassmateStorage.readClassmate().get(); // file path not specified
        assertEquals(original, new Classmate(readBack));

    }

    @Test
    public void saveClassmate_nullClassmate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClassmate(null, "SomeFile.json"));
    }

    /**
     * Saves {@code classmate} at the specified {@code filePath}.
     */
    private void saveClassmate(ReadOnlyClassmate classmate, String filePath) {
        try {
            new JsonClassmateStorage(Paths.get(filePath))
                    .saveClassmate(classmate, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveClassmate_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClassmate(new Classmate(), null));
    }
}
