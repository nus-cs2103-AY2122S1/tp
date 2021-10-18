package tutoraid.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.commons.util.JsonUtil;
import tutoraid.model.LessonBook;
import tutoraid.testutil.Assert;
import tutoraid.testutil.TypicalLessons;

public class JsonSerializableLessonBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableLessonBookTest");
    private static final Path TYPICAL_LESSONS_FILE = TEST_DATA_FOLDER.resolve("typicalLessonsLessonBook.json");
    private static final Path INVALID_LESSON_FILE = TEST_DATA_FOLDER.resolve("invalidLessonLessonBook.json");
    private static final Path DUPLICATE_LESSON_FILE = TEST_DATA_FOLDER.resolve("duplicateLessonLessonBook.json");

    @Test
    public void toModelType_typicalLessonsFile_success() throws Exception {
        JsonSerializableLessonBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_LESSONS_FILE,
                JsonSerializableLessonBook.class).get();
        LessonBook lessonBookFromFile = dataFromFile.toModelType();
        LessonBook typicalLessonsLessonBook = TypicalLessons.getTypicalLessonBook();
        assertEquals(lessonBookFromFile, typicalLessonsLessonBook);
    }

    @Test
    public void toModelType_invalidLessonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableLessonBook dataFromFile = JsonUtil.readJsonFile(INVALID_LESSON_FILE,
                JsonSerializableLessonBook.class).get();
        Assert.assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateLessons_throwsIllegalValueException() throws Exception {
        JsonSerializableLessonBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_LESSON_FILE,
                JsonSerializableLessonBook.class).get();
        Assert.assertThrows(IllegalValueException.class, JsonSerializableLessonBook.MESSAGE_DUPLICATE_LESSON,
                dataFromFile::toModelType);
    }
}
