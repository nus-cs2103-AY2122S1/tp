package seedu.tuitione.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tuitione.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.tuitione.commons.exceptions.IllegalValueException;
import seedu.tuitione.commons.util.JsonUtil;
import seedu.tuitione.model.Tuitione;
import seedu.tuitione.testutil.TypicalLessons;
import seedu.tuitione.testutil.TypicalStudents;
import seedu.tuitione.testutil.TypicalTuition;

public class JsonSerializableTuitioneTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTuitioneTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsTuitione.json");
    private static final Path TYPICAL_LESSONS_FILE = TEST_DATA_FOLDER.resolve("typicalLessonsTuitione.json");
    private static final Path TYPICAL_TUITION_FILE = TEST_DATA_FOLDER.resolve("typicalTuitionTuitione.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentTuitione.json");
    private static final Path INVALID_LESSON_FILE = TEST_DATA_FOLDER.resolve("invalidLessonTuitione.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentTuitione.json");
    private static final Path DUPLICATE_LESSON_FILE = TEST_DATA_FOLDER.resolve("duplicateLessonTuitione.json");
    private static final Path DUPLICATE_ENROLLMENT_FILE = TEST_DATA_FOLDER
            .resolve("duplicateEnrollmentTuitione.json");
    private static final Path ENROLLED_LESSON_NOT_FOUND_FILE = TEST_DATA_FOLDER
            .resolve("enrolledLessonNotFoundTuitione.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableTuitione dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableTuitione.class).get();
        Tuitione tuitioneFromFile = dataFromFile.toModelType();
        Tuitione typicalStudentsTuitione = TypicalStudents.getTypicalTuitione();
        assertEquals(typicalStudentsTuitione, tuitioneFromFile);
    }

    @Test
    public void toModelType_typicalLessonsFile_success() throws Exception {
        JsonSerializableTuitione dataFromFile = JsonUtil.readJsonFile(TYPICAL_LESSONS_FILE,
                JsonSerializableTuitione.class).get();
        Tuitione tuitioneFromFile = dataFromFile.toModelType();
        Tuitione typicalLessonsTuitione = TypicalLessons.getTypicalTuitione();
        assertEquals(typicalLessonsTuitione, tuitioneFromFile);
    }

    @Test
    public void toModelType_typicalTuitionFile_success() throws Exception {
        JsonSerializableTuitione dataFromFile = JsonUtil.readJsonFile(TYPICAL_TUITION_FILE,
                JsonSerializableTuitione.class).get();
        Tuitione tuitioneFromFile = dataFromFile.toModelType();
        Tuitione typicalTuitionTuitione = TypicalTuition.getTypicalTuitione();
        assertEquals(typicalTuitionTuitione, tuitioneFromFile);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTuitione dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableTuitione.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidLessonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTuitione dataFromFile = JsonUtil.readJsonFile(INVALID_LESSON_FILE,
                JsonSerializableTuitione.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableTuitione dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableTuitione.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTuitione.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateLessons_throwsIllegalValueException() throws Exception {
        JsonSerializableTuitione dataFromFile = JsonUtil.readJsonFile(DUPLICATE_LESSON_FILE,
                JsonSerializableTuitione.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTuitione.MESSAGE_DUPLICATE_LESSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_doubleEnrollment_throwsIllegalValueException() throws Exception {
        JsonSerializableTuitione dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ENROLLMENT_FILE,
                JsonSerializableTuitione.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTuitione.MESSAGE_DUPLICATE_ENROLLMENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_lessonNotFound_throwsIllegalValueException() throws Exception {
        JsonSerializableTuitione dataFromFile = JsonUtil.readJsonFile(ENROLLED_LESSON_NOT_FOUND_FILE,
                JsonSerializableTuitione.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTuitione.MESSAGE_INVALID_LESSON_CODE,
                dataFromFile::toModelType);
    }
}
