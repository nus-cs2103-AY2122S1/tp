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
        // null phone number
        assertThrows(NullPointerException.class, () -> StudentId.isValidId(null));

        // invalid phone numbers
        assertFalse(StudentId.isValidId("")); // empty string
        assertFalse(StudentId.isValidId(" ")); // spaces only
        assertFalse(StudentId.isValidId("91")); // less than 3 numbers
        assertFalse(StudentId.isValidId("phone")); // non-numeric
        assertFalse(StudentId.isValidId("9011p041")); // alphabets within digits
        assertFalse(StudentId.isValidId("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(StudentId.isValidId("911")); // exactly 3 numbers
        assertTrue(StudentId.isValidId("93121534"));
        assertTrue(StudentId.isValidId("124293842033123")); // long phone numbers
    }
}
