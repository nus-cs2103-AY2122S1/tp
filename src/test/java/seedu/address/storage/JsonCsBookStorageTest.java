package seedu.address.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalStudents.ALICE;
//import static seedu.address.testutil.TypicalStudents.HOON;
//import static seedu.address.testutil.TypicalStudents.IDA;
//import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CsBook;
import seedu.address.model.ReadOnlyCsBook;

public class JsonCsBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCsBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCsBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCsBook(null));
    }

    private java.util.Optional<ReadOnlyCsBook> readCsBook(String filePath) throws Exception {
        return new JsonCsBookStorage(Paths.get(filePath)).readCsBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCsBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readCsBook("notJsonFormatCsBook.json"));
    }

    @Test
    public void readCsBook_invalidStudentCsBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCsBook("invalidStudentCsBook.json"));
    }

    @Test
    public void readCsBook_invalidAndValidStudentCsBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCsBook("invalidAndValidStudentCsBook.json"));
    }

    //TODO Fix test case
    /*
    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        CsBook original = getTypicalAddressBook();
        JsonCsBookStorage jsonAddressBookStorage = new JsonCsBookStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        ReadOnlyCsBook readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new CsBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
        assertEquals(original, new CsBook(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonAddressBookStorage.saveAddressBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new CsBook(readBack));

    }

     */

    @Test
    public void saveCsBook_nullCsBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCsBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code csBook} at the specified {@code filePath}.
     */
    private void saveCsBook(ReadOnlyCsBook csBook, String filePath) {
        try {
            new JsonCsBookStorage(Paths.get(filePath))
                    .saveCsBook(csBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCsBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCsBook(new CsBook(), null));
    }
}
