package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class for testing {@link CsvAddressBookStorage}.
 */
public class CsvAddressBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "CsvAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    // Helper methods
    private java.util.Optional<ReadOnlyAddressBook> readAddressBook(String filePath) throws Exception {
        return new CsvAddressBookStorage(Paths.get(filePath)).readAddressBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyAddressBook addressBook, String filePath) {
        try {
            new CsvAddressBookStorage(Paths.get(filePath))
                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    // Test cases
    @Test
    public void getAddressBookFilePath_testFilePath_correctFilePath() {
        Path filePath = addToTestDataPathIfNotNull("invalidPersonAddressBook.csv");
        assertEquals(filePath, new CsvAddressBookStorage(filePath).getAddressBookFilePath());
    }

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.csv").isPresent());
    }

    @Test
    public void read_notCsvFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notCsvFormatAddressBook.csv"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonAddressBook.csv"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonAddressBook.csv"));
    }


    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        // Csv is only used for Export.
        // Export is not allowed to overwrite existing files, hence requiring a second filePath.
        Path filePath = testFolder.resolve("TempAddressBook.csv");
        Path filePathExported = testFolder.resolve("TempAddressBookExported.csv");
        AddressBook original = getTypicalAddressBook();
        CsvAddressBookStorage csvAddressBookStorage = new CsvAddressBookStorage(filePath);

        // Save in new file, read back, and compare.
        csvAddressBookStorage.saveAddressBook(original, filePathExported);
        ReadOnlyAddressBook readBack = csvAddressBookStorage.readAddressBook(filePathExported).get();
        assertEquals(original, new AddressBook(readBack));
        try {
            FileUtil.deleteFileIfExists(filePathExported);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error deleting the file.", ioe);
        }

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        csvAddressBookStorage.saveAddressBook(original, filePathExported);
        readBack = csvAddressBookStorage.readAddressBook(filePathExported).get();
        assertEquals(original, new AddressBook(readBack));
        try {
            FileUtil.deleteFileIfExists(filePathExported);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error deleting the file.", ioe);
        }

        // Save and read without specifying file path. The original file will be read.
        original.addPerson(IDA);
        csvAddressBookStorage.saveAddressBook(original); // file path not specified
        readBack = csvAddressBookStorage.readAddressBook().get(); // file path not specified
        assertEquals(original, new AddressBook(readBack));
    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.csv"));
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new AddressBook(), null));
    }
}
