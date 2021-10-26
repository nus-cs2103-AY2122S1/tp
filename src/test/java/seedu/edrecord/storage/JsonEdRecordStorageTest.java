package seedu.edrecord.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.edrecord.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.edrecord.commons.exceptions.DataConversionException;
import seedu.edrecord.model.EdRecord;
import seedu.edrecord.model.ReadOnlyEdRecord;

public class JsonEdRecordStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonEdRecordStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readEdRecord_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readEdRecord(null));
    }

    private java.util.Optional<ReadOnlyEdRecord> readEdRecord(String filePath) throws Exception {
        return new JsonEdRecordStorage(Paths.get(filePath)).readEdRecord(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readEdRecord("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readEdRecord("notJsonFormatEdRecord.json"));
    }

    @Test
    public void readEdRecord_invalidPersonEdRecord_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEdRecord("invalidPersonEdRecord.json"));
    }

    @Test
    public void readEdRecord_invalidAndValidPersonEdRecord_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readEdRecord("invalidAndValidPersonEdRecord.json"));
    }

    // TODO: Add assignments to a person's modules in json format, so that this tests can pass.
    /*     @Test
        public void readAndSaveEdRecord_allInOrder_success() throws Exception {
            Path filePath = testFolder.resolve("TempEdRecord.json");
            EdRecord original = getTypicalEdRecord();
            for (Person typicalPerson : original.getPersonList()) {
                for (Map.Entry<Module, Group> modGroupPair : typicalPerson.getModules().getMapping().entrySet()) {
                    modGroupPair.getKey().setGroupSystem(getTypicalGroupSystem());
                }
            }
            JsonEdRecordStorage jsonEdRecordStorage = new JsonEdRecordStorage(filePath);
            setTypicalModuleSystem();

            // Save in new file and read back
            jsonEdRecordStorage.saveEdRecord(original, filePath);
            ReadOnlyEdRecord readBack = jsonEdRecordStorage.readEdRecord(filePath).get();
            assertEquals(original, new EdRecord(readBack));

            // Modify data, overwrite exiting file, and read back
            for (Map.Entry<Module, Group> modGroupPair : HOON.getModules().getMapping().entrySet()) {
                modGroupPair.getKey().setGroupSystem(getTypicalGroupSystem());
            }
            original.addPerson(HOON);
            original.removePerson(ALICE);
            jsonEdRecordStorage.saveEdRecord(original, filePath);
            readBack = jsonEdRecordStorage.readEdRecord(filePath).get();
            assertEquals(original, new EdRecord(readBack));

            // Save and read without specifying file path
            for (Map.Entry<Module, Group> modGroupPair : IDA.getModules().getMapping().entrySet()) {
                modGroupPair.getKey().setGroupSystem(getTypicalGroupSystem());
            }
            original.addPerson(IDA);
            jsonEdRecordStorage.saveEdRecord(original); // file path not specified
            readBack = jsonEdRecordStorage.readEdRecord().get(); // file path not specified
            assertEquals(original, new EdRecord(readBack));
        }
     */
    @Test
    public void saveEdRecord_nullEdRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEdRecord(null, "SomeFile.json"));
    }

    /**
     * Saves {@code edRecord} at the specified {@code filePath}.
     */
    private void saveEdRecord(ReadOnlyEdRecord edRecord, String filePath) {
        try {
            new JsonEdRecordStorage(Paths.get(filePath))
                    .saveEdRecord(edRecord, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveEdRecord_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEdRecord(new EdRecord(), null));
    }
}
