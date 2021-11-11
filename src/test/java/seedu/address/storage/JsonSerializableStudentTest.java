package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Classmate;
import seedu.address.testutil.TypicalStudents;

public class JsonSerializableStudentTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStudentTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsClassmate.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentClassmate.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentClassmate.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableStudent dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableStudent.class).get();
        Classmate classmateFromFile = dataFromFile.toModelType();
        Classmate typicalStudentsClassmate = TypicalStudents.getTypicalClassmate();
        assertEquals(classmateFromFile, typicalStudentsClassmate);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStudent dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableStudent.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableStudent dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableStudent.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStudent.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
