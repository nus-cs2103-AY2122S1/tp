package tutoraid.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import tutoraid.commons.exceptions.DataConversionException;
import tutoraid.model.ReadOnlyLessonBook;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.StudentBook;
import tutoraid.testutil.Assert;
import tutoraid.testutil.TypicalLessons;
import tutoraid.testutil.TypicalStudents;

public class JsonStudentBookStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonStudentBookStorageTest");
    private static final ReadOnlyLessonBook lb = TypicalLessons.getTypicalLessonBook();

    @TempDir
    public Path testFolder;

    @Test
    public void readStudentBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readStudentBook(null));
    }

    private java.util.Optional<ReadOnlyStudentBook> readStudentBook(String filePath) throws Exception {
        return new JsonTutorAidStudentStorage(Paths.get(filePath), TypicalLessons.getTypicalLessonBook())
                .readStudentBook(addToTestDataPathIfNotNull(filePath), TypicalLessons.getTypicalLessonBook());
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }


    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, () -> readStudentBook("notJsonFormatStudentBook.json"));
    }

    @Test
    public void readStudentBook_invalidPersonStudentBook_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readStudentBook("invalidPersonStudentBook.json"));
    }

    @Test
    public void readStudentBook_invalidAndValidPersonStudentBook_throwDataConversionException() {
        Assert.assertThrows(
                DataConversionException.class, () -> readStudentBook("invalidAndValidPersonStudentBook.json"));
    }

    @Test
    public void readAndSaveStudentBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempStudentBook.json");
        StudentBook original = TypicalStudents.getTypicalStudentBook();
        JsonTutorAidStudentStorage jsonTutorAidStudentStorage = new JsonTutorAidStudentStorage(filePath, lb);

        // Save in new file and read back
        jsonTutorAidStudentStorage.saveStudentBook(original, filePath);
        ReadOnlyStudentBook readBack = jsonTutorAidStudentStorage.readStudentBook(filePath, lb).get();
        assertEquals(original, new StudentBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(TypicalStudents.HOON);
        original.removeStudent(TypicalStudents.ALICE);
        jsonTutorAidStudentStorage.saveStudentBook(original, filePath);
        readBack = jsonTutorAidStudentStorage.readStudentBook(filePath, lb).get();
        assertEquals(original, new StudentBook(readBack));

        // Save and read without specifying file path
        original.addStudent(TypicalStudents.IDA);
        jsonTutorAidStudentStorage.saveStudentBook(original); // file path not specified
        readBack = jsonTutorAidStudentStorage.readStudentBook(lb).get(); // file path not specified
        assertEquals(original, new StudentBook(readBack));

    }

    @Test
    public void saveStudentBook_nullStudentBook_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveStudentBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code studentBook} at the specified {@code filePath}.
     */
    private void saveStudentBook(ReadOnlyStudentBook studentBook, String filePath) {
        try {
            new JsonTutorAidStudentStorage(Paths.get(filePath), lb)
                    .saveStudentBook(studentBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveStudentBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveStudentBook(new StudentBook(), null));
    }
}
