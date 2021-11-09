package seedu.address.model.module.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIdTest {
    public static final StudentId VALID_STUDENT_ID_1 = new StudentId("A1234567A");
    public static final StudentId VALID_STUDENT_ID_2 = new StudentId("A7654321A");
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalArgumentException() {
        String invalidStudentId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidStudentId));
    }

    @Test
    public void isValidStudentId() {
        // null student id
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid student ids
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only
        assertFalse(StudentId.isValidStudentId("123456789")); // numbers only
        assertFalse(StudentId.isValidStudentId("A12345678")); // missing last letter
        assertFalse(StudentId.isValidStudentId("Invalid")); // letters only
        assertFalse(StudentId.isValidStudentId("A123 4567A")); // spaces within digits
        assertFalse(StudentId.isValidStudentId("Z1234567A")); // starts with Z

        // valid student ids
        assertTrue(StudentId.isValidStudentId("A1234567A"));
        assertTrue(StudentId.isValidStudentId("A1111111Z"));

    }
}
