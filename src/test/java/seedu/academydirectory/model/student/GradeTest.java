package seedu.academydirectory.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GradeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Grade(null));
    }

    @Test
    public void constructor_invalidGrade_failure() {
        String invalidGrade = "999";
        assertThrows(IllegalArgumentException.class, () -> new Grade(invalidGrade));
    }

    @Test
    void isValidGrade() {
        // null grade
        assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null));

        // blank grade
        assertFalse(Grade.isValidGrade("")); // empty string
        assertFalse(Grade.isValidGrade(" ")); // spaces only

        // out of range
        assertFalse(Grade.isValidGrade("-1"));
        assertFalse(Grade.isValidGrade("101"));

        // contains non-digits
        assertFalse(Grade.isValidGrade("ten"));
        assertFalse(Grade.isValidGrade("1one"));

        // correct input
        assertTrue(Grade.isValidGrade("0"));
        assertTrue(Grade.isValidGrade("50"));
        assertTrue(Grade.isValidGrade("100"));
    }
}
