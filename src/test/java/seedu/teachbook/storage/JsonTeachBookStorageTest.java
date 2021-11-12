package seedu.teachbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.teachbook.testutil.Assert.assertThrows;
import static seedu.teachbook.testutil.TypicalStudents.ALICE;
import static seedu.teachbook.testutil.TypicalStudents.HOON;
import static seedu.teachbook.testutil.TypicalStudents.IDA;
import static seedu.teachbook.testutil.TypicalStudents.getTypicalTeachBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.commons.exceptions.DataConversionException;
import seedu.teachbook.model.ReadOnlyTeachBook;
import seedu.teachbook.model.TeachBook;
import seedu.teachbook.model.student.Student;

public class JsonTeachBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTeachBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTeachBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTeachBook(null));
    }

    private java.util.Optional<ReadOnlyTeachBook> readTeachBook(String filePath) throws Exception {
        return new JsonTeachBookStorage(Paths.get(filePath)).readTeachBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTeachBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTeachBook("notJsonFormatTeachBook.json"));
    }

    @Test
    public void readTeachBook_invalidPersonTeachBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTeachBook("invalidStudentTeachBook.json"));
    }

    @Test
    public void readTeachBook_invalidAndValidPersonTeachBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTeachBook("invalidAndValidStudentTeachBook.json"));
    }

    @Test
    public void readAndSaveTeachBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        TeachBook original = getTypicalTeachBook();
        JsonTeachBookStorage jsonTeachBookStorage = new JsonTeachBookStorage(filePath);

        // Save in new file and read back
        jsonTeachBookStorage.saveTeachBook(original, filePath);
        ReadOnlyTeachBook readBack = jsonTeachBookStorage.readTeachBook(filePath).get();
        assertEquals(original, new TeachBook(readBack));

        // Modify data, overwrite exiting file, and read back
        Student test = new Student(HOON);
        test.setStudentClass(original.getClassAtIndex(GeneralIndex.fromOneBased(1)));
        original.addStudent(GeneralIndex.fromOneBased(1), test);
        original.removeStudent(GeneralIndex.fromOneBased(1), ALICE);
        jsonTeachBookStorage.saveTeachBook(original, filePath);
        readBack = jsonTeachBookStorage.readTeachBook(filePath).get();
        assertEquals(original, new TeachBook(readBack));

        // Save and read without specifying file path
        test = new Student(IDA);
        test.setStudentClass(original.getClassAtIndex(GeneralIndex.fromOneBased(1)));
        original.addStudent(GeneralIndex.fromOneBased(1), test);
        jsonTeachBookStorage.saveTeachBook(original); // file path not specified
        readBack = jsonTeachBookStorage.readTeachBook().get(); // file path not specified
        assertEquals(original, new TeachBook(readBack));

    }

    @Test
    public void saveTeachBook_nullTeachBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTeachBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveTeachBook(ReadOnlyTeachBook addressBook, String filePath) {
        try {
            new JsonTeachBookStorage(Paths.get(filePath))
                    .saveTeachBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveTeachBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTeachBook(new TeachBook(), null));
    }
}
