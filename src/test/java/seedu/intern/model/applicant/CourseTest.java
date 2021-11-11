package seedu.intern.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intern.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CourseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Course(null));
    }

    @Test
    public void isValidCourse() {
        // null course
        assertThrows(NullPointerException.class, () -> Course.isValidCourse(null));

        // invalid course
        assertFalse(Course.isValidCourse("^21Business Analytics")); // contains non-alphabetic characters
        assertFalse(Course.isValidCourse("*")); // only non-alphanumeric characters
        assertFalse(Course.isValidCourse("")); // empty string

        // valid course
        assertTrue(Course.isValidCourse("CS")); // alphabets only
        assertTrue(Course.isValidCourse("Computer Science")); // spaces in between characters
        assertTrue(Course.isValidCourse("Business Analytics")); // with capital letters
        assertTrue(Course.isValidCourse("Double Degree in Computer Science and Mathematics")); // long names
    }
}
