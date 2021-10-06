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
    public void isValidId() {
        // null Student ID
        assertThrows(NullPointerException.class, () -> StudentId.isValidId(null));

        // invalid Student IDs
        assertFalse(StudentId.isValidId("")); // empty string
        assertFalse(StudentId.isValidId(" ")); // spaces only
        assertFalse(StudentId.isValidId("91")); // less than 9 characters
        assertFalse(StudentId.isValidId("B1234567A")); // doesn't start with 'A'
        assertFalse(StudentId.isValidId("A123456Z")); // doesn't have 7 digits
        assertFalse(StudentId.isValidId("A1234567")); // doesn't end with a letter
        assertFalse(StudentId.isValidId("A 1293998Z")); // contains a space

        // valid Student IDs
        assertTrue(StudentId.isValidId("A0123456Z")); // exactly 9 characters
        assertTrue(StudentId.isValidId("A1234567A")); // can have different last letter
    }
}
