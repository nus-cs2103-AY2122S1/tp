package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentNumber(null));
    }

    @Test
    public void constructor_invalidStudentNumber_throwsIllegalArgumentException() {
        String invalidStudentNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentNumber(invalidStudentNumber));
    }

    @Test
    public void isValidStudentNumber() {
        // null student number
        assertThrows(NullPointerException.class, () -> StudentNumber.isValidNumber(null));

        // blank student number
        assertFalse(StudentNumber.isValidNumber("")); // empty string
        assertFalse(StudentNumber.isValidNumber(" ")); // spaces only

        // missing parts
        assertFalse(StudentNumber.isValidNumber("0123456A")); // missing starting character
        assertFalse(StudentNumber.isValidNumber("A0123456")); // missing ending character
        assertFalse(StudentNumber.isValidNumber("A012345A")); // missing number

        // invalid parts
        assertFalse(StudentNumber.isValidNumber("@1234567B")); // invalid character
        assertFalse(StudentNumber.isValidNumber("A01234_56B")); // underscore in student number
        assertFalse(StudentNumber.isValidNumber("A0 123456B")); // spaces in student number
        assertFalse(StudentNumber.isValidNumber(" A0123456A")); // leading space
        assertFalse(StudentNumber.isValidNumber("A0123456A ")); // trailing space
        assertFalse(StudentNumber.isValidNumber("AA0123456A")); // double upper case character at the front
        assertFalse(StudentNumber.isValidNumber("A0123456AA")); // double upper case character at the back
        assertFalse(StudentNumber.isValidNumber("aA0123456A")); // double lower case character at the front
        assertFalse(StudentNumber.isValidNumber("A0123456Aa")); // double lower case character at the back


        // valid Number
        assertTrue(StudentNumber.isValidNumber("a0123456A")); // lower case starting character
        assertTrue(StudentNumber.isValidNumber("A0123456a")); // lower case ending character
        assertTrue(StudentNumber.isValidNumber("a0123456a")); // lower case starting and ending character

    }
}
