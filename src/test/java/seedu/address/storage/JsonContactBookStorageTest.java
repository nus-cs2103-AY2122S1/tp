package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalContactBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ContactBook;
import seedu.address.model.ReadOnlyContactBook;

public class JsonContactBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonContactBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readContactBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readContactBook(null));
    }

    private java.util.Optional<ReadOnlyContactBook> readContactBook(String filePath) throws Exception {
        return new JsonContactBookStorage(Paths.get(filePath)).readContactBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readContactBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readContactBook("notJsonFormatContactBook.json"));
    }

    @Test
    public void readContactBook_invalidPersonContactBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readContactBook("invalidPersonContactBook.json"));
    }

    @Test
    public void readContactBook_invalidAndValidPersonContactBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readContactBook("invalidAndValidPersonContactBook.json"));
    }

    @Test
    public void readAndSaveContactBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempContactBook.json");
        ContactBook original = getTypicalContactBook();
        JsonContactBookStorage jsonContactBookStorage = new JsonContactBookStorage(filePath);

        // Save in new file and read back
        jsonContactBookStorage.saveContactBook(original, filePath);
        ReadOnlyContactBook readBack = jsonContactBookStorage.readContactBook(filePath).get();
        assertEquals(original, new ContactBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonContactBookStorage.saveContactBook(original, filePath);
        readBack = jsonContactBookStorage.readContactBook(filePath).get();
        assertEquals(original, new ContactBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonContactBookStorage.saveContactBook(original); // file path not specified
        readBack = jsonContactBookStorage.readContactBook().get(); // file path not specified
        assertEquals(original, new ContactBook(readBack));

    }

    @Test
    public void saveContactBook_nullContactBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveContactBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code contactBook} at the specified {@code filePath}.
     */
    private void saveContactBook(ReadOnlyContactBook contactBook, String filePath) {
        try {
            new JsonContactBookStorage(Paths.get(filePath))
                    .saveContactBook(contactBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveContactBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveContactBook(new ContactBook(), null));
    }
}
