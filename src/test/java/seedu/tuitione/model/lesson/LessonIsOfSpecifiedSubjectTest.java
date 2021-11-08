package seedu.tuitione.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.tuitione.testutil.LessonBuilder;

public class LessonIsOfSpecifiedSubjectTest {

    private Subject firstSubject = new Subject("English");
    private Subject secondSubject = new Subject("Science");

    private LessonIsOfSpecifiedSubject firstPredicate = new LessonIsOfSpecifiedSubject(firstSubject);
    private LessonIsOfSpecifiedSubject secondPredicate = new LessonIsOfSpecifiedSubject(secondSubject);

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LessonIsOfSpecifiedSubject firstPredicateCopy = new LessonIsOfSpecifiedSubject(firstSubject);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different subject -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_subjectMatches_returnsTrue() {
        assertTrue(firstPredicate.test(new LessonBuilder().withSubject("English").build()));
    }

    @Test
    public void test_subjectDoesNotMatch_returnsFalse() {
        // Non-matching subject
        assertFalse(firstPredicate.test(new LessonBuilder().withSubject("Science").build()));

    }
}
