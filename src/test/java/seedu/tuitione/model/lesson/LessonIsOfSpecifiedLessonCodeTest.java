package seedu.tuitione.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.tuitione.model.student.Grade;
import seedu.tuitione.testutil.LessonBuilder;
import seedu.tuitione.testutil.TypicalLessons;

public class LessonIsOfSpecifiedLessonCodeTest {

    @Test
    public void equals() {
        LessonCode firstLessonCode = TypicalLessons.MATH_S2.getLessonCode();
        LessonCode secondLessonCode = TypicalLessons.PHYSICS_S2.getLessonCode();

        LessonIsOfSpecifiedLessonCode firstPredicate = new LessonIsOfSpecifiedLessonCode(firstLessonCode);
        LessonIsOfSpecifiedLessonCode secondPredicate = new LessonIsOfSpecifiedLessonCode(secondLessonCode);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LessonIsOfSpecifiedLessonCode firstPredicateCopy = new LessonIsOfSpecifiedLessonCode(firstLessonCode);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> return false
        assertFalse(firstPredicate.equals(1));

        // null -> return false
        assertFalse(firstPredicate.equals(null));

        // different lessonCode -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_lessonCodeMatches_returnsTrue() {
        LessonIsOfSpecifiedLessonCode predicate = new LessonIsOfSpecifiedLessonCode(
                TypicalLessons.MATH_S2.getLessonCode());
        // matching lesson details
        assertTrue(predicate.test(new LessonBuilder()
                .withSubject(new Subject("Mathematics"))
                .withGrade(new Grade("S2"))
                .withLessonTime(new LessonTime(DayOfWeek.TUESDAY, LocalTime.of(9, 30)))
                .withPrice(new Price(15.3))
                .build()));
    }

    @Test
    public void test_lessonCodeDoesNotMatch_returnFalse() {
        LessonIsOfSpecifiedLessonCode predicate = new LessonIsOfSpecifiedLessonCode(
                TypicalLessons.MATH_S2.getLessonCode());
        // non-matching subject
        assertFalse(predicate.test(new LessonBuilder()
                .withSubject(new Subject("Science"))
                .withGrade(new Grade("S2"))
                .withLessonTime(new LessonTime(DayOfWeek.TUESDAY, LocalTime.of(9, 30)))
                .withPrice(new Price(15.3))
                .build()));

        // non-matching grade
        assertFalse(predicate.test(new LessonBuilder()
                .withSubject(new Subject("Mathematics"))
                .withGrade(new Grade("S1"))
                .withLessonTime(new LessonTime(DayOfWeek.TUESDAY, LocalTime.of(9, 30)))
                .withPrice(new Price(15.3))
                .build()));

        // non-matching DayOfWeek
        assertFalse(predicate.test(new LessonBuilder()
                .withSubject(new Subject("Mathematics"))
                .withGrade(new Grade("S2"))
                .withLessonTime(new LessonTime(DayOfWeek.MONDAY, LocalTime.of(9, 30)))
                .withPrice(new Price(15.3))
                .build()));

        // non-matching hour
        assertFalse(predicate.test(new LessonBuilder()
                .withSubject(new Subject("Mathematics"))
                .withGrade(new Grade("S2"))
                .withLessonTime(new LessonTime(DayOfWeek.TUESDAY, LocalTime.of(10, 30)))
                .withPrice(new Price(15.3))
                .build()));

        // non-matching minute
        assertFalse(predicate.test(new LessonBuilder()
                .withSubject(new Subject("Mathematics"))
                .withGrade(new Grade("S2"))
                .withLessonTime(new LessonTime(DayOfWeek.TUESDAY, LocalTime.of(9, 00)))
                .withPrice(new Price(15.3))
                .build()));

        // non-matching price
        assertFalse(predicate.test(new LessonBuilder()
                .withSubject(new Subject("Mathematics"))
                .withGrade(new Grade("S2"))
                .withLessonTime(new LessonTime(DayOfWeek.TUESDAY, LocalTime.of(9, 00)))
                .withPrice(new Price(15.4))
                .build()));
    }
}
