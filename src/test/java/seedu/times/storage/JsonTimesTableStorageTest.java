package seedu.times.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.times.testutil.Assert.assertThrows;
import static seedu.times.testutil.TypicalTimestable.ALICE;
import static seedu.times.testutil.TypicalTimestable.HOON;
import static seedu.times.testutil.TypicalTimestable.IDA;
import static seedu.times.testutil.TypicalTimestable.getTypicalTimesTable;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.times.commons.exceptions.DataConversionException;
import seedu.times.model.ReadOnlyTimesTable;
import seedu.times.model.TimesTable;

public class JsonTimesTableStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTimesTableStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTimesTable_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTimesTable(null));
    }

    private java.util.Optional<ReadOnlyTimesTable> readTimesTable(String filePath) throws Exception {
        return new JsonTimesTableStorage(Paths.get(filePath)).readTimesTable(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTimesTable("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTimesTable("notJsonFormatTimesTable.json"));
    }

    @Test
    public void readTimesTable_invalidPersonTimesTable_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTimesTable("invalidPersonTimesTable.json"));
    }

    @Test
    public void readTimesTable_invalidAndValidPersonTimesTable_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTimesTable("invalidAndValidPersonTimesTable.json"));
    }

    @Test
    public void readAndSaveTimesTable_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTimesTable.json");
        TimesTable original = getTypicalTimesTable();
        JsonTimesTableStorage jsonTimesTableStorage = new JsonTimesTableStorage(filePath);

        // Save in new file and read back
        jsonTimesTableStorage.saveTimesTable(original, filePath);
        ReadOnlyTimesTable readBack = jsonTimesTableStorage.readTimesTable(filePath).get();
        assertEquals(original, new TimesTable(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removePerson(ALICE);
        jsonTimesTableStorage.saveTimesTable(original, filePath);
        readBack = jsonTimesTableStorage.readTimesTable(filePath).get();
        assertEquals(original, new TimesTable(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonTimesTableStorage.saveTimesTable(original); // file path not specified
        readBack = jsonTimesTableStorage.readTimesTable().get(); // file path not specified
        assertEquals(original, new TimesTable(readBack));

    }

    @Test
    public void saveTimesTable_nullTimesTable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTimesTable(null, "SomeFile.json"));
    }

    /**
     * Saves {@code timestable} at the specified {@code filePath}.
     */
    private void saveTimesTable(ReadOnlyTimesTable timesTable, String filePath) {
        try {
            new JsonTimesTableStorage(Paths.get(filePath))
                    .saveTimesTable(timesTable, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTimesTable_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTimesTable(new TimesTable(), null));
    }
}
