package seedu.teachbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.teachbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.teachbook.commons.exceptions.IllegalValueException;
import seedu.teachbook.commons.util.JsonUtil;
import seedu.teachbook.model.TeachBook;
import seedu.teachbook.testutil.TypicalStudents;

public class JsonSerializableTeachBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTeachBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsTeachBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidStudentTeachBook.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentTeachBook.json");
    private static final Path DUPLICATE_CLASS_FILE = TEST_DATA_FOLDER.resolve("duplicateClassTeachBook.json");
    private static final Path DUPLICATE_GRADE_FILE = TEST_DATA_FOLDER.resolve("duplicateGradeTeachBook.json");
    private static final Path INVALID_GRADE_FILE = TEST_DATA_FOLDER.resolve("invalidGradeTeachBook.json");

    @Test
    public void toModelType_typicalStudentFile_success() throws Exception {
        JsonSerializableTeachBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableTeachBook.class).get();
        TeachBook teachBookFromFile = dataFromFile.toModelType();
        TeachBook typicalPersonsTeachBook = TypicalStudents.getTypicalTeachBook();
        assertEquals(teachBookFromFile, typicalPersonsTeachBook);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTeachBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableTeachBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableTeachBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableTeachBook.class).get();
        assertThrows(IllegalValueException.class, JsonAdaptedClass.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateClass_throwsIllegalValueException() throws Exception {
        JsonSerializableTeachBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLASS_FILE,
                JsonSerializableTeachBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTeachBook.MESSAGE_DUPLICATE_CLASS,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateGrade_throwsIllegalValueException() throws Exception {
        JsonSerializableTeachBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_GRADE_FILE,
                JsonSerializableTeachBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTeachBook.MESSAGE_DUPLICATE_GRADE,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidGrade_throwsIllegalValueException() throws Exception {
        JsonSerializableTeachBook dataFromFile = JsonUtil.readJsonFile(INVALID_GRADE_FILE,
                JsonSerializableTeachBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTeachBook.MESSAGE_INVALID_GRADE,
                dataFromFile::toModelType);
    }

}
