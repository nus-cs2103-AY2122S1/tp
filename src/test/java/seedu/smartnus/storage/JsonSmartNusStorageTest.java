package seedu.smartnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalQuestions.MCQ_QUESTION_1;
import static seedu.smartnus.testutil.TypicalQuestions.SAQ_QUESTION_3;
import static seedu.smartnus.testutil.TypicalQuestions.STORAGE_QUESTION_2;
import static seedu.smartnus.testutil.TypicalSmartNus.getTypicalSmartNus;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.smartnus.commons.exceptions.DataConversionException;
import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.SmartNus;

public class JsonSmartNusStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSmartNusStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSmartNus_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSmartNus(null));
    }

    private java.util.Optional<ReadOnlySmartNus> readSmartNus(String filePath) throws Exception {
        return new JsonSmartNusStorage(Paths.get(filePath)).readSmartNus(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSmartNus("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSmartNus("notJsonFormatSmartNus.json"));
    }

    @Test
    public void readSmartNus_invalidQuestionSmartNus_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSmartNus("invalidQuestionSmartNus.json"));
    }

    @Test
    public void readSmartNus_invalidAndValidQuestionSmartNus_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSmartNus(
                "invalidAndValidQuestionSmartNus.json"));
    }

    @Test
    public void readAndSaveSmartNus_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSmartNus.json");
        SmartNus original = getTypicalSmartNus();
        JsonSmartNusStorage jsonSmartNusStorage = new JsonSmartNusStorage(filePath);

        // Save in new file and read back
        jsonSmartNusStorage.saveSmartNus(original, filePath);
        ReadOnlySmartNus readBack = jsonSmartNusStorage.readSmartNus(filePath).get();
        assertEquals(original, new SmartNus(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addQuestion(STORAGE_QUESTION_2);
        original.removeQuestion(MCQ_QUESTION_1);
        jsonSmartNusStorage.saveSmartNus(original, filePath);
        readBack = jsonSmartNusStorage.readSmartNus(filePath).get();
        assertEquals(original, new SmartNus(readBack));

        // Save and read without specifying file path
        original.addQuestion(SAQ_QUESTION_3);
        jsonSmartNusStorage.saveSmartNus(original); // file path not specified
        readBack = jsonSmartNusStorage.readSmartNus().get(); // file path not specified
        assertEquals(original, new SmartNus(readBack));

    }

    @Test
    public void saveSmartNus_nullSmartNus_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSmartNus(null, "SomeFile.json"));
    }

    /**
     * Saves {@code smartNus} at the specified {@code filePath}.
     */
    private void saveSmartNus(ReadOnlySmartNus smartNus, String filePath) {
        try {
            new JsonSmartNusStorage(Paths.get(filePath))
                    .saveSmartNus(smartNus, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSmartNus_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSmartNus(new SmartNus(), null));
    }

    @Test
    public void readSmartNus_invalidMcq_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSmartNus("invalidMcqSmartNus.json"));
    }

    @Test
    public void readSmartNus_invalidTfq_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSmartNus("invalidTfqSmartNus.json"));
    }

    @Test
    public void readSmartNus_invalidSaq_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSmartNus("invalidSaqSmartNus.json"));
    }

    @Test
    public void readSmartNus_nullInJsonFile_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSmartNus("onlyNullValue.json"));
    }
}
