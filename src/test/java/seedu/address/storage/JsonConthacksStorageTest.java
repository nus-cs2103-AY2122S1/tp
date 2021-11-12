package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalConthacks;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Conthacks;
import seedu.address.model.ReadOnlyConthacks;

public class JsonConthacksStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonConthacksStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readConthacks_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readConthacks(null));
    }

    private java.util.Optional<ReadOnlyConthacks> readConthacks(String filePath) throws Exception {
        return new JsonConthacksStorage(Paths.get(filePath)).readConthacks(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readConthacks("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readConthacks("notJsonFormatConthacks.json"));
    }

    @Test
    public void readConthacks_invalidPersonConthacks_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readConthacks("invalidPersonConthacks.json"));
    }

    @Test
    public void readConthacks_invalidAndValidPersonConthacks_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readConthacks("invalidAndValidPersonConthacks.json"));
    }

    @Test
    public void readAndSaveConthacks_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        Conthacks original = getTypicalConthacks();
        JsonConthacksStorage jsonAddressBookStorage = new JsonConthacksStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveConthacks(original, filePath);
        ReadOnlyConthacks readBack = jsonAddressBookStorage.readConthacks(filePath).get();
        assertEquals(original, new Conthacks(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveConthacks(original, filePath);
        readBack = jsonAddressBookStorage.readConthacks(filePath).get();
        assertEquals(original, new Conthacks(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.saveConthacks(original); // file path not specified
        readBack = jsonAddressBookStorage.readConthacks().get(); // file path not specified
        assertEquals(original, new Conthacks(readBack));

    }

    @Test
    public void saveConthacks_nullConthacks_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveConthacks(null, "SomeFile.json"));
    }

    /**
     * Saves {@code conthacks} at the specified {@code filePath}.
     */
    private void saveConthacks(ReadOnlyConthacks conthacks, String filePath) {
        try {
            new JsonConthacksStorage(Paths.get(filePath))
                    .saveConthacks(conthacks, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveConthacks_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveConthacks(new Conthacks(), null));
    }
}
