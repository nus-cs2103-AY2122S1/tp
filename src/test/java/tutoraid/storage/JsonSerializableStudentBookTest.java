package tutoraid.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import tutoraid.commons.exceptions.IllegalValueException;
import tutoraid.commons.util.JsonUtil;
import tutoraid.model.StudentBook;
import tutoraid.testutil.Assert;
import tutoraid.testutil.TypicalLessons;
import tutoraid.testutil.TypicalStudents;

public class JsonSerializableStudentBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStudentBookTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsStudentBook.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidPersonStudentBook.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonStudentBook.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableStudentBook.class).get();
        StudentBook studentBookFromFile = dataFromFile.toModelType(TypicalLessons.getTypicalLessonBook());
        StudentBook typicalPersonsStudentBook = TypicalStudents.getTypicalStudentBook();
        assertEquals(studentBookFromFile, typicalPersonsStudentBook);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableStudentBook.class).get();
        Assert.assertThrows(IllegalValueException.class, () -> dataFromFile
                .toModelType(TypicalLessons.getTypicalLessonBook()));
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableStudentBook.class).get();
        Assert.assertThrows(
                IllegalValueException.class,
                JsonSerializableStudentBook.MESSAGE_DUPLICATE_STUDENT, () -> dataFromFile.toModelType(
                        TypicalLessons.getTypicalLessonBook()));
    }

}
