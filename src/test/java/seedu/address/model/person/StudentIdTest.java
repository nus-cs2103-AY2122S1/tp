package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
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

    @Test
    public void isEqualStudentId() {
        StudentId studentId = new StudentId("A1234567A");
        StudentId different_studentId = new StudentId("A7654321A");
        StudentId same_studentId = new StudentId("A1234567A");

        // Different StudentId
        assertFalse(studentId.equals(different_studentId));

        // Same Object
        assertTrue(studentId.equals(studentId));

        // Different Objects Same StudentId
        assertTrue(studentId.equals(same_studentId));
    }

    @Test
    public void hashCodeTest() {
        assertEquals(VALID_STUDENT_ID_AMY.hashCode(), VALID_STUDENT_ID_AMY.hashCode());
        assertNotEquals(VALID_STUDENT_ID_AMY.hashCode(), VALID_STUDENT_ID_BOB.hashCode());
    }
}
