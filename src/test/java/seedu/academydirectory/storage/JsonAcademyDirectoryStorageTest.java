package seedu.academydirectory.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.academydirectory.testutil.Assert.assertThrows;
import static seedu.academydirectory.testutil.TypicalPersons.ALICE;
import static seedu.academydirectory.testutil.TypicalPersons.HOON;
import static seedu.academydirectory.testutil.TypicalPersons.IDA;
import static seedu.academydirectory.testutil.TypicalPersons.getTypicalAcademyDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.academydirectory.commons.exceptions.DataConversionException;
import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.model.ReadOnlyAcademyDirectory;

public class JsonAcademyDirectoryStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyAcademyDirectory> readAddressBook(String filePath) throws Exception {
        return new JsonAcademyDirectoryStorage(Paths.get(filePath)).readAcademyDirectory(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAcademyDirectory.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonAcademyDirectory.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonAcademyDirectory.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        AcademyDirectory original = getTypicalAcademyDirectory();
        JsonAcademyDirectoryStorage jsonAddressBookStorage = new JsonAcademyDirectoryStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAcademyDirectory(original, filePath);
        ReadOnlyAcademyDirectory readBack = jsonAddressBookStorage.readAcademyDirectory(filePath).get();
        assertEquals(original, new AcademyDirectory(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveAcademyDirectory(original, filePath);
        readBack = jsonAddressBookStorage.readAcademyDirectory(filePath).get();
        assertEquals(original, new AcademyDirectory(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.saveAcademyDirectory(original); // file path not specified
        readBack = jsonAddressBookStorage.readAcademyDirectory().get(); // file path not specified
        assertEquals(original, new AcademyDirectory(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAcademyDirectory addressBook, String filePath) {
        try {
            new JsonAcademyDirectoryStorage(Paths.get(filePath))
                    .saveAcademyDirectory(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AcademyDirectory(), null));
    }
}
