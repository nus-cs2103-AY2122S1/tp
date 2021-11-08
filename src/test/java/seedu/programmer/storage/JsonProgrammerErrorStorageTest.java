package seedu.programmer.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.programmer.testutil.Assert.assertThrows;
import static seedu.programmer.testutil.TypicalStudents.ALICE;
import static seedu.programmer.testutil.TypicalStudents.HOON;
import static seedu.programmer.testutil.TypicalStudents.IDA;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.programmer.commons.exceptions.DataConversionException;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.ReadOnlyProgrammerError;

public class JsonProgrammerErrorStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonProgrammerErrorStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readProgrammerError_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readProgrammerError(null));
    }

    private java.util.Optional<ReadOnlyProgrammerError> readProgrammerError(String filePath) throws Exception {
        return new JsonProgrammerErrorStorage(Paths.get(filePath))
                .readProgrammerError(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readProgrammerError("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readProgrammerError("notJsonFormatProgrammerError.json"));
    }

    @Test
    public void readProgrammerError_invalidPersonProgrammerError_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readProgrammerError("invalidStudentProgrammerError.json"));
    }

    @Test
    public void readProgrammerError_invalidAndValidPersonProgrammerError_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readProgrammerError(
                "invalidAndValidStudentProgrammerError.json"));
    }

    @Test
    public void readAndSaveProgrammerError_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempProgrammerError.json");
        ProgrammerError original = getTypicalProgrammerError();
        JsonProgrammerErrorStorage jsonProgrammerErrorStorage = new JsonProgrammerErrorStorage(filePath);

        // Save in new file and read back
        jsonProgrammerErrorStorage.saveProgrammerError(original, filePath);
        ReadOnlyProgrammerError readBack = jsonProgrammerErrorStorage.readProgrammerError(filePath).get();
        assertEquals(original, new ProgrammerError(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonProgrammerErrorStorage.saveProgrammerError(original, filePath);
        readBack = jsonProgrammerErrorStorage.readProgrammerError(filePath).get();
        assertEquals(original, new ProgrammerError(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonProgrammerErrorStorage.saveProgrammerError(original); // file path not specified
        readBack = jsonProgrammerErrorStorage.readProgrammerError().get(); // file path not specified
        assertEquals(original, new ProgrammerError(readBack));

    }

    @Test
    public void saveProgrammerError_nullProgrammerError_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProgrammerError(null, "SomeFile.json"));
    }

    /**
     * Saves {@code programmerError} at the specified {@code filePath}.
     */
    private void saveProgrammerError(ReadOnlyProgrammerError programmerError, String filePath) {
        try {
            new JsonProgrammerErrorStorage(Paths.get(filePath))
                    .saveProgrammerError(programmerError, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveProgrammerError_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveProgrammerError(new ProgrammerError(), null));
    }
}
