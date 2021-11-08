package seedu.tuitione.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.tuitione.model.student.Grade;
import seedu.tuitione.testutil.LessonBuilder;

public class LessonIsOfSpecifiedGradeTest {

    private Grade firstGrade = new Grade("S1");
    private Grade secondGrade = new Grade("S2");

    private LessonIsOfSpecifiedGrade firstPredicate = new LessonIsOfSpecifiedGrade(firstGrade);
    private LessonIsOfSpecifiedGrade secondPredicate = new LessonIsOfSpecifiedGrade(secondGrade);

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LessonIsOfSpecifiedGrade firstPredicateCopy = new LessonIsOfSpecifiedGrade(firstGrade);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different grade -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_gradeMatches_returnsTrue() {
        // Grade with secondary prefix
        LessonIsOfSpecifiedGrade predicate = new LessonIsOfSpecifiedGrade(new Grade("S3"));
        assertTrue(predicate.test(new LessonBuilder().withGrade("S3").build()));

        // Grade with primary prefix
        predicate = new LessonIsOfSpecifiedGrade(new Grade("P1"));
        assertTrue(predicate.test(new LessonBuilder().withGrade("P1").build()));
    }

    @Test
    public void test_gradeDoesNotMatch_returnsFalse() {
        // Non-matching grade
        assertFalse(firstPredicate.test(new LessonBuilder().withGrade("P1").build()));

    }
}
