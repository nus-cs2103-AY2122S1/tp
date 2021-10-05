package seedu.academydirectory.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.academydirectory.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.exceptions.IllegalValueException;
import seedu.academydirectory.commons.util.JsonUtil;
import seedu.academydirectory.model.AcademyDirectory;
import seedu.academydirectory.testutil.TypicalStudents;

public class JsonSerializableAcademyDirectoryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableAcademyDirectoryTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsAcademyDirectory.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentAcademyDirectory.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER
                                            .resolve("duplicateStudentAcademyDirectory.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableAcademyDirectory dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableAcademyDirectory.class).get();
        AcademyDirectory academyDirectoryFromFile = dataFromFile.toModelType();
        AcademyDirectory typicalStudentsAcademyDirectory = TypicalStudents.getTypicalAcademyDirectory();
        assertEquals(academyDirectoryFromFile, typicalStudentsAcademyDirectory);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAcademyDirectory dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableAcademyDirectory.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableAcademyDirectory dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableAcademyDirectory.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAcademyDirectory.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
