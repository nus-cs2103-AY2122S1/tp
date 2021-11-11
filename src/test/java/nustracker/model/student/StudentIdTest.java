package nustracker.model.student;

import static nustracker.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalArgumentException() {
        String invalidStudentId = "8471132";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidStudentId));
    }

    @Test
    public void isValidStudentId() {
        //null studentId
        assertThrows(NullPointerException.class, () -> new StudentId(null));

        // invalid studentIds
        assertFalse(StudentId.isValidStudentId("e012323"));
        assertFalse(StudentId.isValidStudentId("e123133"));
        assertFalse(StudentId.isValidStudentId("e931138"));
        assertFalse(StudentId.isValidStudentId("e831639"));
        assertFalse(StudentId.isValidStudentId("a123199"));
        assertFalse(StudentId.isValidStudentId("b981239"));
        assertFalse(StudentId.isValidStudentId("e918329"));
        assertFalse(StudentId.isValidStudentId("e912812"));

        // valid majors
        assertTrue(StudentId.isValidStudentId("e1234897"));
        assertTrue(StudentId.isValidStudentId("e1234890"));
        assertTrue(StudentId.isValidStudentId("e8971232"));
        assertTrue(StudentId.isValidStudentId("e8912372"));
    }

}
