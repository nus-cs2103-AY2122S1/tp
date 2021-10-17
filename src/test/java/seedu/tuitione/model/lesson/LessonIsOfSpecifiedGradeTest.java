package seedu.tuitione.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.tuitione.model.student.Grade;
import seedu.tuitione.testutil.LessonBuilder;

public class LessonIsOfSpecifiedGradeTest {

    @Test
    public void equals() {

        Grade firstGrade = new Grade("S1");
        Grade secondGrade = new Grade("S2");

        LessonIsOfSpecifiedGrade firstPredicate = new LessonIsOfSpecifiedGrade(firstGrade);
        LessonIsOfSpecifiedGrade secondPredicate = new LessonIsOfSpecifiedGrade(secondGrade);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LessonIsOfSpecifiedGrade firstPredicateCopy = new LessonIsOfSpecifiedGrade(firstGrade);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_gradeMatches_returnsTrue() {
        // Secondary prefix
        LessonIsOfSpecifiedGrade predicate = new LessonIsOfSpecifiedGrade(new Grade("S1"));
        assertTrue(predicate.test(new LessonBuilder().withGrade("S1").build()));

        // Primary prefix
        predicate = new LessonIsOfSpecifiedGrade(new Grade("P1"));
        assertTrue(predicate.test(new LessonBuilder().withGrade("P1").build()));
    }

    @Test
    public void test_gradeDoesNotMatch_returnsFalse() {
        // Non-matching grade
        LessonIsOfSpecifiedGrade predicate = new LessonIsOfSpecifiedGrade(new Grade("S1"));
        assertFalse(predicate.test(new LessonBuilder().withGrade("S2").build()));

    }
}
