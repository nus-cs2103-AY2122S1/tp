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
    private FileManager fm;
    private final int DEFAULT_NUM_OF_STUDENTS = 36;

    @BeforeEach
    public void setUpTests() {
        fm = new FileManager();
    }

    @Test
    public void getJsonData_validJson_returnsCorrectNumberOfElements() {
        JSONArray result = fm.getJsonData("src/test/data/FileManagerTest/programmerError.json");
        assertEquals(result.length(), DEFAULT_NUM_OF_STUDENTS);
    }

    @Test
    public void getJsonData_invalidJson_returnsNull() {
        JSONArray result = fm.getJsonData("src/test/data/FileManagerTest/invalidProgrammerError.json");
        assertNull(result);
    }

    @Test
    public void getStudentsFromCsv_validCsv_returnsCorrectNumberOfStudents() throws IOException {
        File testFile = new File("src/test/data/FileManagerTest/PE.csv");
        List<Student> stuList = fm.getStudentsFromCsv(testFile);
        assertEquals(stuList.size(), DEFAULT_NUM_OF_STUDENTS);
    }

    @Test
    public void getStudentsFromCsv_incorrectNumberOfColumns_returnsNull() throws IOException {
        File testFile = new File("src/test/data/FileManagerTest/programmerError.csv");
        List<Student> stuList = fm.getStudentsFromCsv(testFile);
        assertNull(stuList);
    }

    @Test
    public void getStudentsFromCsv_invalidField_throwsIllegalArgumentException() {
        File testFile = new File("src/test/data/FileManagerTest/invalidStudentIdPE.csv");
        assertThrows(IllegalArgumentException.class, () -> {
            fm.getStudentsFromCsv(testFile);
        });
    }

    @Test
    public void getStudentsFromCsv_missingField_throwsIllegalArgumentException() {
        File testFile = new File("src/test/data/FileManagerTest/missingClassIdPE.csv");
        assertThrows(IllegalArgumentException.class, () -> {
            fm.getStudentsFromCsv(testFile);
        });
    }
}

