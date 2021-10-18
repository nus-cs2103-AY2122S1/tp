package seedu.tuitione.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.model.lesson.Subject.MAXIMUM_SUBJECT_LENGTH;
import static seedu.tuitione.model.lesson.Subject.SUBJECT_MESSAGE_CONSTRAINTS;
import static seedu.tuitione.testutil.Assert.assertThrows;
import static seedu.tuitione.testutil.LessonBuilder.DEFAULT_SUBJECT;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class SubjectTest {

    @Test
    public void constructor_validSubject_returnsSubject() {
        Subject otherSubject = new Subject(DEFAULT_SUBJECT.value);
        assertEquals(DEFAULT_SUBJECT, otherSubject);
    }

    @Test
    public void constructor_invalidSubject_throwsIllegalArgumentException() {
        // non alphanum
        String invalidSubject = "~!a2";
        assertThrows(IllegalArgumentException.class, SUBJECT_MESSAGE_CONSTRAINTS, () -> new Subject(invalidSubject));

        // empty
        String emptySubject = "";
        assertThrows(IllegalArgumentException.class, SUBJECT_MESSAGE_CONSTRAINTS, () -> new Subject(invalidSubject));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Subject(null));
    }

    @Test
    public void isValidSubject() {
        // valid string
        String valid = "Maths";
        assertTrue(Subject.isValidSubject(valid));

        // non-alphanumeric string
        String nonAlphanumeric = "~12P";
        assertFalse(Subject.isValidSubject(nonAlphanumeric));

        // empty string
        String empty = "";
        assertFalse(Subject.isValidSubject(empty));

        // lengthy string
        StringBuilder lengthyBuilder = new StringBuilder();
        IntStream.range(0, MAXIMUM_SUBJECT_LENGTH + 1)
                .forEach(num -> lengthyBuilder.append('a'));
        assertFalse(Subject.isValidSubject(lengthyBuilder.toString()));

        // null string
        assertFalse(Subject.isValidSubject(null));
    }

    @Test
    public void equals() {
        // this -> true
        assertEquals(DEFAULT_SUBJECT, DEFAULT_SUBJECT);

        // null -> false
        assertNotEquals(DEFAULT_SUBJECT, null);

        // different instance -> false
        assertNotEquals(DEFAULT_SUBJECT, 5);

        // diff value -> false
        Subject differentSubject = new Subject("Maths");
        assertNotEquals(DEFAULT_SUBJECT, differentSubject);

        // same value but diff instance -> true
        Subject otherSubject = new Subject(DEFAULT_SUBJECT.value);
        assertEquals(DEFAULT_SUBJECT, otherSubject);
    }
}
