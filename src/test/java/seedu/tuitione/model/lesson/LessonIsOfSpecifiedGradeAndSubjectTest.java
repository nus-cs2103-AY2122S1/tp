package seedu.tuitione.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.tuitione.model.student.Grade;
import seedu.tuitione.testutil.LessonBuilder;

public class LessonIsOfSpecifiedGradeAndSubjectTest {

    private Grade firstGrade = new Grade("S1");
    private Grade secondGrade = new Grade("S2");
    private Subject firstSubject = new Subject("English");
    private Subject secondSubject = new Subject("Science");

    private LessonIsOfSpecifiedGradeAndSubject firstPredicate =
            new LessonIsOfSpecifiedGradeAndSubject(firstGrade, firstSubject);
    private LessonIsOfSpecifiedGradeAndSubject secondPredicate =
            new LessonIsOfSpecifiedGradeAndSubject(secondGrade, secondSubject);

    @Test
    public void equals() {

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LessonIsOfSpecifiedGradeAndSubject firstPredicateCopy =
                new LessonIsOfSpecifiedGradeAndSubject(firstGrade, firstSubject);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different subject same grade -> returns false
        LessonIsOfSpecifiedGradeAndSubject testPredicateSameGradeDiffSubject =
                new LessonIsOfSpecifiedGradeAndSubject(firstGrade, secondSubject);
        assertFalse(firstPredicate.equals(testPredicateSameGradeDiffSubject));

        // different grade same subject -> returns false
        LessonIsOfSpecifiedGradeAndSubject testPredicateDiffGradeSameSubject =
                new LessonIsOfSpecifiedGradeAndSubject(secondGrade, firstSubject);
        assertFalse(firstPredicate.equals(testPredicateDiffGradeSameSubject));

        // different subject and different grade -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_subjectAndGradeMatches_returnsTrue() {
        assertTrue(firstPredicate.test(new LessonBuilder().withSubject("English").withGrade("S1").build()));
    }

    @Test
    public void test_subjectAndGradeDoesNotMatch_returnsFalse() {
        // subject does not match, grade matches
        assertFalse(firstPredicate.test(new LessonBuilder().withSubject("Science").withGrade("S1").build()));

        // subject matches, grade does not match
        assertFalse(firstPredicate.test(new LessonBuilder().withSubject("English").withGrade("S2").build()));

        // both subject and grade does not match
        assertFalse(firstPredicate.test(new LessonBuilder().withSubject("Science").withGrade("S2").build()));
    }
}
