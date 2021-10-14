package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.MON_10_12_BIOLOGY;
import static seedu.address.testutil.TypicalLessons.MON_11_13_MATH;
import static seedu.address.testutil.TypicalLessons.MON_16_18_MATH;
import static seedu.address.testutil.TypicalLessons.TUE_16_18_ENGLISH;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

class LessonTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lesson(null, null, null));
    }

    @Test
    void doLessonsOverlap_overlappingLessons_returnsTrue() {
        // overlapping timing same day
        assertTrue(MON_10_12_BIOLOGY.doLessonsOverlap(MON_11_13_MATH));
    }

    @Test
    void doLessonsOverlap_noOverlapLessons_returnsFalse() {
        // totally different
        assertFalse(MON_10_12_BIOLOGY.doLessonsOverlap(TUE_16_18_ENGLISH));
        // same timing different day
        assertFalse(MON_16_18_MATH.doLessonsOverlap(TUE_16_18_ENGLISH));
        // same day different timing
        assertFalse(MON_10_12_BIOLOGY.doLessonsOverlap(MON_16_18_MATH));
        // same name different timing
        assertFalse(MON_11_13_MATH.doLessonsOverlap(MON_16_18_MATH));
    }

    @Test
    void testEquals_sameDetails_returnsTrue() {
        Lesson l1 = new Lesson(new Timeslot("10:00", "14:00"), new Subject("Math"), DayOfWeek.of(5));
        Lesson l2 = new Lesson(new Timeslot("10:00", "14:00"), new Subject("Math"), DayOfWeek.of(5));
        assertTrue(l1.equals(l2));
    }
}
