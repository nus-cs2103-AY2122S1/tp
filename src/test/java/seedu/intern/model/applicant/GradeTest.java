package seedu.intern.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GradeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Grade(null));
    }

    @Test
    public void constructor_invalidGrade_throwsIllegalArgumentException() {
        String invalidGrade = "";
        assertThrows(IllegalArgumentException.class, () -> new Grade(invalidGrade));
    }

    @Test
    public void isValidGrade() {
        // null email
        assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null));

        // blank grade
        assertFalse(Grade.isValidGrade("")); // empty string
        assertFalse(Grade.isValidGrade(" ")); // spaces only

        // invalid parts
        assertFalse(Grade.isValidGrade("A")); // letter grade
        assertFalse(Grade.isValidGrade("8.9")); // grade above 5.0
        assertFalse(Grade.isValidGrade("3 .00")); // spaces before decimal point
        assertFalse(Grade.isValidGrade("3. 0")); // spaces after decimal point
        assertFalse(Grade.isValidGrade(" 2.9")); // leading space
        assertFalse(Grade.isValidGrade("4.8 ")); // trailing space
        assertFalse(Grade.isValidGrade("4..9")); // double '.' symbol
        assertFalse(Grade.isValidGrade("-4.9")); // negative number
        assertFalse(Grade.isValidGrade("5.02")); // out of range
        assertFalse(Grade.isValidGrade("5")); // no 2 d.p
        assertFalse(Grade.isValidGrade("0")); // no 2 d.p
        assertFalse(Grade.isValidGrade("4.9")); // no 2 d.p
        // valid grade
        assertTrue(Grade.isValidGrade("3.00")); //Decimal number within 0.00 - 5.00
        assertTrue(Grade.isValidGrade("4.27")); // period in local part
    }
}
