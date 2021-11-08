package seedu.programmer.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.programmer.testutil.Assert.assertThrows;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.programmer.commons.exceptions.IllegalValueException;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;

public class FileUtilTest {

    private static final String TEST_DATA_FOLDER = "src/test/data/FileUtilTest/";
    private static final String VALID_CSV = TEST_DATA_FOLDER + "validPE.csv";
    private static final String WRONG_NUM_COLS_CSV = TEST_DATA_FOLDER + "programmerError.csv";
    private static final String INVALID_FIELD_CSV = TEST_DATA_FOLDER + "invalidStudentIdPE.csv";
    private static final String MISSING_FIELD_CSV = TEST_DATA_FOLDER + "missingClassIdPE.csv";
    private static final String COMMAS_WITH_SPACES_CSV = TEST_DATA_FOLDER + "commasWithSpacesPE.csv";
    private static final String SWAPPED_COLUMN_NAME_CSV = TEST_DATA_FOLDER + "swappedColumnNamePE.csv";
    private static final String WRONG_COLUMN_ORDER_CSV = TEST_DATA_FOLDER + "wrongColumnOrderPE.csv";
    private static final String MISSING_HEADERS_CSV = TEST_DATA_FOLDER + "missingHeadersPE.csv";
    private static final int DEFAULT_NUM_OF_STUDENTS = 36;

    @Test
    public void isValidPath() {
        // valid path
        assertTrue(FileUtil.isValidPath("valid/file/path"));

        // invalid path
        assertFalse(FileUtil.isValidPath("a\0"));

        // null path -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> FileUtil.isValidPath(null));
    }

    @Test
    public void getStudentsFromCsv_validCsv_returnsCorrectNumberOfStudents() throws IOException, IllegalValueException {
        File testFile = new File(VALID_CSV);
        List<Student> stuList = FileUtil.getStudentsFromCsv(testFile);
        assertEquals(stuList.size(), DEFAULT_NUM_OF_STUDENTS);
    }

    @Test
    public void getStudentsFromCsv_wrongNumberOfColumns_throwsIllegalValueException() {
        String expectedMessage = "Sorry! Your CSV file header should be: `studentId,classId,name,email`";
        File testFile = new File(WRONG_NUM_COLS_CSV);
        assertThrows(IllegalValueException.class, expectedMessage, () -> FileUtil.getStudentsFromCsv(testFile));
    }

    @Test
    public void getStudentsFromCsv_invalidField_throwsIllegalArgumentException() {
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        File testFile = new File(INVALID_FIELD_CSV);
        assertThrows(IllegalArgumentException.class, expectedMessage, () -> FileUtil.getStudentsFromCsv(testFile));
    }

    @Test
    public void getStudentsFromCsv_missingField_throwsIllegalArgumentException() {
        String expectedMessage = ClassId.MESSAGE_CONSTRAINTS;
        File testFile = new File(MISSING_FIELD_CSV);
        assertThrows(IllegalArgumentException.class, expectedMessage, () -> FileUtil.getStudentsFromCsv(testFile));
    }

    @Test
    public void getStudentsFromCsv_commasWithSpaces_throwsIllegalArgumentException() {
        String expectedMessage = "Sorry! Your CSV file header should be: `studentId,classId,name,email`";
        File testFile = new File(COMMAS_WITH_SPACES_CSV);
        assertThrows(IllegalValueException.class, expectedMessage, () -> FileUtil.getStudentsFromCsv(testFile));
    }

    @Test
    public void getStudentsFromCsv_swappedColumnName_throwsIllegalArgumentException() {
        String expectedMessage = "Sorry! Your CSV file header should be: `studentId,classId,name,email`";
        File testFile = new File(SWAPPED_COLUMN_NAME_CSV);
        assertThrows(IllegalValueException.class, expectedMessage, () -> FileUtil.getStudentsFromCsv(testFile));
    }

    @Test
    public void getStudentsFromCsv_wrongOrder_throwsIllegalValueException() {
        String expectedMessage = "Sorry! Your CSV file header should be: `studentId,classId,name,email`";
        File testFile = new File(WRONG_COLUMN_ORDER_CSV);
        assertThrows(IllegalValueException.class, expectedMessage, () -> FileUtil.getStudentsFromCsv(testFile));
    }

    @Test
    public void getStudentsFromCsv_missingHeaders_throwsIllegalValueException() {
        String expectedMessage = "Sorry! Your CSV file header should be: `studentId,classId,name,email`";
        File testFile = new File(MISSING_HEADERS_CSV);
        assertThrows(IllegalValueException.class, expectedMessage, () -> FileUtil.getStudentsFromCsv(testFile));
    }
}
