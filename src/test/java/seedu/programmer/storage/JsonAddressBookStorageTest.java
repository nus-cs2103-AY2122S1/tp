//package seedu.programmer.storage;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static seedu.programmer.testutil.Assert.assertThrows;
//import static seedu.programmer.testutil.Typicalstudents.ALICE;
//import static seedu.programmer.testutil.Typicalstudents.HOON;
//import static seedu.programmer.testutil.Typicalstudents.IDA;
//import static seedu.programmer.testutil.Typicalstudents.getTypicalProgrammerError;
//
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.io.TempDir;
//
//import seedu.programmer.commons.exceptions.DataConversionException;
//import seedu.programmer.model.ProgrammerError;
//import seedu.programmer.model.ReadOnlyProgrammerError;
//
//public class JsonProgrammerErrorStorageTest {
//    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonProgrammerErrorStorageTest");
//
//    @TempDir
//    public Path testFolder;
//
//    @Test
//    public void readProgrammerError_nullFilePath_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> readProgrammerError(null));
//    }
//
//    private java.util.Optional<ReadOnlyProgrammerError> readOnlyProgrammerError(String filePath) throws Exception {
//   return new JsonProgrammerErrorStorage(Paths.get(filePath))
//   .readProgrammerError(addToTestDataPathIfNotNull(filePath));
//    }
//
//    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
//        return prefsFileInTestDataFolder != null
//                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
//                : null;
//    }
//
//    @Test
//    public void read_missingFile_emptyResult() throws Exception {
//        assertFalse(readProgrammerError("NonExistentFile.json").isPresent());
//    }
//
//    @Test
//    public void read_notJsonFormat_exceptionThrown() {
//        assertThrows(DataConversionException.class, () -> readProgrammerError("notJsonFormatProgrammerError.json"));
//    }
//
//    public void readProgrammerError_invalidstudentProgrammerError_throwDataConversionException() {
//        assertThrows(DataConversionException.class, () -> readProgrammerError("invalidstudentProgrammerError.json"));
//    }
//
//    @Test
//    public void readProgrammerError_invalidAndValidstudentProgrammerError_throwDataConversionException() {
//        assertThrows(DataConversionException.class,
//        () -> readProgrammerError("invalidAndValidstudentProgrammerError.json"));
//    }
//
//    @Test
//    public void readAndSaveProgrammerError_allInOrder_success() throws Exception {
//        Path filePath = testFolder.resolve("TempProgrammerError.json");
//        ProgrammerError original = getTypicalProgrammerError();
//        JsonProgrammerErrorStorage jsonProgrammerErrorStorage = new JsonProgrammerErrorStorage(filePath);
//
//        // Save in new file and read back
//        jsonProgrammerErrorStorage.saveProgrammerError(original, filePath);
//        ReadOnlyProgrammerError readBack = jsonProgrammerErrorStorage.readProgrammerError(filePath).get();
//        assertEquals(original, new ProgrammerError(readBack));
//
//        // Modify data, overwrite exiting file, and read back
//        original.addstudent(HOON);
//        original.removestudent(ALICE);
//        jsonProgrammerErrorStorage.saveProgrammerError(original, filePath);
//        readBack = jsonProgrammerErrorStorage.readProgrammerError(filePath).get();
//        assertEquals(original, new ProgrammerError(readBack));
//
//        // Save and read without specifying file path
//        original.addstudent(IDA);
//        jsonProgrammerErrorStorage.saveProgrammerError(original); // file path not specified
//        readBack = jsonProgrammerErrorStorage.readProgrammerError().get(); // file path not specified
//        assertEquals(original, new ProgrammerError(readBack));
//
//    }
//
//    @Test
//    public void saveProgrammerError_nullProgrammerError_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> saveProgrammerError(null, "SomeFile.json"));
//    }
//
//    /**
//     * Saves {@code ProgrammerError} at the specified {@code filePath}.
//     */
//    private void saveProgrammerError(ReadOnlyProgrammerError ProgrammerError, String filePath) {
//        try {
//            new JsonProgrammerErrorStorage(Paths.get(filePath))
//                    .saveProgrammerError(ProgrammerError, addToTestDataPathIfNotNull(filePath));
//        } catch (IOException ioe) {
//            throw new AssertionError("There should not be an error writing to the file.", ioe);
//        }
//    }
//
//    @Test
//    public void saveProgrammerError_nullFilePath_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> saveProgrammerError(new ProgrammerError(), null));
//    }
//}
