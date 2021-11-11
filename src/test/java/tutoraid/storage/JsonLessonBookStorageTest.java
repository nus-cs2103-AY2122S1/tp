package tutoraid.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import tutoraid.commons.exceptions.DataConversionException;
import tutoraid.model.LessonBook;
import tutoraid.model.ReadOnlyLessonBook;
import tutoraid.testutil.Assert;
import tutoraid.testutil.TypicalLessons;

public class JsonLessonBookStorageTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonLessonBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readLessonBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> readLessonBook(null));
    }

    private java.util.Optional<ReadOnlyLessonBook> readLessonBook(String filePath) throws Exception {
        return new JsonTutorAidLessonStorage(Paths.get(filePath))
                .readLessonBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readLessonBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        Assert.assertThrows(DataConversionException.class, () -> readLessonBook("notJsonFormatLessonBook.json"));
    }

    @Test
    public void readLessonBook_invalidLessonLessonBook_throwDataConversionException() {
        Assert.assertThrows(DataConversionException.class, () -> readLessonBook("invalidLessonLessonBook.json"));
    }

    @Test
    public void readLessonBook_invalidAndValidLessonsLessonBook_throwDataConversionException() {
        Assert.assertThrows(
                DataConversionException.class, () -> readLessonBook("invalidAndValidLessonsLessonBook.json"));
    }

    @Test
    public void readAndSaveStudentBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempLessonBook.json");
        LessonBook original = TypicalLessons.getTypicalLessonBook();
        JsonTutorAidLessonStorage jsonTutorAidLessonStorage = new JsonTutorAidLessonStorage(filePath);

        // Save in new file and read back
        jsonTutorAidLessonStorage.saveLessonBook(original, filePath);
        ReadOnlyLessonBook readBack = jsonTutorAidLessonStorage.readLessonBook(filePath).get();
        assertEquals(original, new LessonBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addLesson(TypicalLessons.ENGLISH_ONE);
        original.removeLesson(TypicalLessons.MATHS_ONE);
        jsonTutorAidLessonStorage.saveLessonBook(original, filePath);
        readBack = jsonTutorAidLessonStorage.readLessonBook(filePath).get();
        assertEquals(original, new LessonBook(readBack));

        // Save and read without specifying file path
        original.addLesson(TypicalLessons.ENGLISH_TWO);
        jsonTutorAidLessonStorage.saveLessonBook(original); // file path not specified
        readBack = jsonTutorAidLessonStorage.readLessonBook().get(); // file path not specified
        assertEquals(original, new LessonBook(readBack));
    }

    @Test
    public void saveLessonBook_nullLessonBook_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveLessonBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code lessonBook} at the specified {@code filePath}.
     */
    private void saveLessonBook(ReadOnlyLessonBook lessonBook, String filePath) {
        try {
            new JsonTutorAidLessonStorage(Paths.get(filePath))
                    .saveLessonBook(lessonBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveLessonBook_nullFilePath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> saveLessonBook(new LessonBook(), null));
    }
}
