package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentIdTest {

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
        // null StudentId
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // blank StudentId
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only

        // invalid StudentId because a or A followed by 7 digits then one alphabet
        assertFalse(StudentId.isValidStudentId("e1234567A")); // invalid because first character is not a or A
        assertFalse(StudentId.isValidStudentId("a1234567")); // invalid because last alphabet is missing
        assertFalse(StudentId.isValidStudentId("A12345678A")); // invalid because there are 8 digits instead of 7
        assertFalse(StudentId.isValidStudentId("A-1234567A")); // invalid because special characters not allowed

        // valid StudentId
        assertTrue(StudentId.isValidStudentId("a0000000X"));
        assertTrue(StudentId.isValidStudentId("A1234567A"));
    }
}
