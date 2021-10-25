package seedu.programmer.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static seedu.programmer.testutil.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.programmer.model.student.Student;

class FileManagerTest {
    public static final String TEST_DATA_FOLDER = "src/test/data/FileManagerTest/";
    private static final String VALID_JSON = TEST_DATA_FOLDER + "programmerError.json";
    private static final String INVALID_JSON = TEST_DATA_FOLDER + "invalidProgrammerError.json";
    private static final String VALID_CSV = TEST_DATA_FOLDER + "PE.csv";
    private static final String WRONG_NUM_COLS_CSV = TEST_DATA_FOLDER + "programmerError.csv";
    private static final String INVALID_FIELD_CSV = TEST_DATA_FOLDER + "invalidStudentIdPE.csv";
    private static final String MISSING_FIELD_CSV = TEST_DATA_FOLDER + "missingClassIdPE.csv";
    private static final int DEFAULT_NUM_OF_STUDENTS = 36;
    private FileManager fm;

    @BeforeEach
    public void setUpTests() {
        fm = new FileManager();
    }

    @Test
    public void getJsonData_validJson_returnsCorrectNumberOfElements() {
        JSONArray result = fm.getJsonData(VALID_JSON);
        assertEquals(result.length(), DEFAULT_NUM_OF_STUDENTS);
    }

    @Test
    public void getJsonData_invalidJson_returnsNull() {
        JSONArray result = fm.getJsonData(INVALID_JSON);
        assertNull(result);
    }

    @Test
    public void getStudentsFromCsv_validCsv_returnsCorrectNumberOfStudents() throws IOException {
        File testFile = new File(VALID_CSV);
        List<Student> stuList = fm.getStudentsFromCsv(testFile);
        assertEquals(stuList.size(), DEFAULT_NUM_OF_STUDENTS);
    }

    @Test
    public void getStudentsFromCsv_wrongNumberOfColumns_returnsNull() throws IOException {
        File testFile = new File(WRONG_NUM_COLS_CSV);
        List<Student> stuList = fm.getStudentsFromCsv(testFile);
        assertNull(stuList);
    }

    @Test
    public void getStudentsFromCsv_invalidField_throwsIllegalArgumentException() {
        File testFile = new File(INVALID_FIELD_CSV);
        assertThrows(IllegalArgumentException.class, () -> {
            fm.getStudentsFromCsv(testFile);
        });
    }

    @Test
    public void getStudentsFromCsv_missingField_throwsIllegalArgumentException() {
        File testFile = new File(MISSING_FIELD_CSV);
        assertThrows(IllegalArgumentException.class, () -> {
            fm.getStudentsFromCsv(testFile);
        });
    }
}

