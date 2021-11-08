package seedu.tuitione.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.tuitione.testutil.StudentBuilder;

public class StudentIsOfSpecifiedGradeTest {

    @Test
    public void equals() {

        Grade firstGrade = new Grade("S1");
        Grade secondGrade = new Grade("S2");

        StudentIsOfSpecifiedGrade firstPredicate = new StudentIsOfSpecifiedGrade(firstGrade);
        StudentIsOfSpecifiedGrade secondPredicate = new StudentIsOfSpecifiedGrade(secondGrade);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentIsOfSpecifiedGrade firstPredicateCopy = new StudentIsOfSpecifiedGrade(firstGrade);
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
        // Secondary prefix
        StudentIsOfSpecifiedGrade predicate = new StudentIsOfSpecifiedGrade(new Grade("S1"));
        assertTrue(predicate.test(new StudentBuilder().withGrade("S1").build()));

        // Primary prefix
        predicate = new StudentIsOfSpecifiedGrade(new Grade("P1"));
        assertTrue(predicate.test(new StudentBuilder().withGrade("P1").build()));
    }

    @Test
    public void test_gradeDoesNotMatch_returnsFalse() {
        // Non-matching grade
        StudentIsOfSpecifiedGrade predicate = new StudentIsOfSpecifiedGrade(new Grade("S1"));
        assertFalse(predicate.test(new StudentBuilder().withGrade("S2").build()));

    }
}
