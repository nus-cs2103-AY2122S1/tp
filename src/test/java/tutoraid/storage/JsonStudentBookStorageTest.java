package tutoraid.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static tutoraid.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import tutoraid.commons.exceptions.DataConversionException;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.StudentBook;
import tutoraid.testutil.Assert;
import tutoraid.testutil.TypicalStudents;

public class JsonStudentBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonStudentBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyStudentBook> readAddressBook(String filePath) throws Exception {
        return new JsonTutorAidStorage(Paths.get(filePath)).readStudentBook(addToTestDataPathIfNotNull(filePath));
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
        Assert.assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatStudentBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonStudentBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonStudentBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        StudentBook original = TypicalStudents.getTypicalStudentBook();
        JsonTutorAidStorage jsonAddressBookStorage = new JsonTutorAidStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveStudentBook(original, filePath);
        ReadOnlyStudentBook readBack = jsonAddressBookStorage.readStudentBook(filePath).get();
        assertEquals(original, new StudentBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(TypicalStudents.HOON);
        original.removeStudent(TypicalStudents.ALICE);
        jsonAddressBookStorage.saveStudentBook(original, filePath);
        readBack = jsonAddressBookStorage.readStudentBook(filePath).get();
        assertEquals(original, new StudentBook(readBack));

        // Save and read without specifying file path
        original.addStudent(TypicalStudents.IDA);
        jsonAddressBookStorage.saveStudentBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readStudentBook().get(); // file path not specified
        assertEquals(original, new StudentBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyStudentBook addressBook, String filePath) {
        try {
            new JsonTutorAidStorage(Paths.get(filePath))
                    .saveStudentBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveAddressBook(new StudentBook(), null));
    }
}
