package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.lesson.LessonTime.TIME_MESSAGE_CONSTRAINTS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.LessonBuilder.DEFAULT_LESSON_TIME;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class LessonTimeTest {

    private static final String[] DAY_SHORT_FORMS = new String[] {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    @Test
    public void constructor_validSubject_returnsSubject() {
        LessonTime otherLessonTime = new LessonTime(DEFAULT_LESSON_TIME.dayOfWeek, DEFAULT_LESSON_TIME.startTime);
        assertEquals(DEFAULT_LESSON_TIME, otherLessonTime);
    }

    @Test
    public void constructor_invalidSubject_throwsIllegalArgumentException() {
        LocalTime beforeEarliestBoundTime = LessonTime.BOUNDED_EARLIEST_START_TIME.minusMinutes(1);
        assertThrows(IllegalArgumentException.class, TIME_MESSAGE_CONSTRAINTS, () ->
                new LessonTime(DEFAULT_LESSON_TIME.dayOfWeek, beforeEarliestBoundTime));

        LocalTime pastLatestBoundTime = LessonTime.BOUNDED_LATEST_START_TIME.plusMinutes(1);
        assertThrows(IllegalArgumentException.class, TIME_MESSAGE_CONSTRAINTS, () ->
                new LessonTime(DEFAULT_LESSON_TIME.dayOfWeek, pastLatestBoundTime));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonTime(null, null));
    }

    @Test
    public void isValidTime() {
        // null
        assertFalse(LessonTime.isValidTime(null));

        // before start bound
        LocalTime beforeEarliestBoundTime = LessonTime.BOUNDED_EARLIEST_START_TIME.minusMinutes(1);
        assertFalse(LessonTime.isValidTime(beforeEarliestBoundTime));

        // after end bound
        LocalTime pastLatestBoundTime = LessonTime.BOUNDED_LATEST_START_TIME.plusMinutes(1);
        assertFalse(LessonTime.isValidTime(pastLatestBoundTime));

        // in between
        assertTrue(LessonTime.isValidTime(DEFAULT_LESSON_TIME.startTime));
    }

    @Test
    public void hasOverlappedTiming() {
        // exact same timing -> true
        LessonTime clashingLessonTime = new LessonTime(DEFAULT_LESSON_TIME.dayOfWeek, DEFAULT_LESSON_TIME.startTime);
        assertTrue(DEFAULT_LESSON_TIME.hasOverlappedTiming(clashingLessonTime));

        // different day -> false
        LessonTime otherDayLessonTime = new LessonTime(DayOfWeek.MONDAY, DEFAULT_LESSON_TIME.startTime);
        assertFalse(DEFAULT_LESSON_TIME.hasOverlappedTiming(otherDayLessonTime));

        // start timing clashes -> true
        LessonTime startTimeClashLessonTime = new LessonTime(DEFAULT_LESSON_TIME.dayOfWeek, LocalTime.of(16, 0));
        assertTrue(DEFAULT_LESSON_TIME.hasOverlappedTiming(startTimeClashLessonTime));

        // end timing clashes -> true
        LessonTime endTimeClashLessonTime = new LessonTime(DEFAULT_LESSON_TIME.dayOfWeek, LocalTime.of(14, 0));
        assertTrue(DEFAULT_LESSON_TIME.hasOverlappedTiming(endTimeClashLessonTime));

        // same day and no clash in timing -> false
        LessonTime differentTimingLessonTime = new LessonTime(DEFAULT_LESSON_TIME.dayOfWeek, LocalTime.of(13, 0));
        assertFalse(DEFAULT_LESSON_TIME.hasOverlappedTiming(differentTimingLessonTime));
    }

    @Test
    public void parseStringToDayOfWeek() {
        // null
        assertThrows(NullPointerException.class, () -> LessonTime.parseStringToDay(null));

        // invalid
        String invalid = "";
        assertThrows(NoSuchElementException.class, () -> LessonTime.parseStringToDay(invalid).orElseThrow());

        // valid
        for (int idx = 0; idx < DAY_SHORT_FORMS.length; idx++) {
            assertEquals(DayOfWeek.of(idx + 1),
                    LessonTime.parseStringToDay(DAY_SHORT_FORMS[idx]).orElseThrow());
        }
    }

    @Test
    public void parseDayToString() {
        // null
        assertThrows(NullPointerException.class, () -> LessonTime.parseDayToString(null));

        // valid
        for (int idx = 0; idx < DAY_SHORT_FORMS.length; idx++) {
            assertEquals(DAY_SHORT_FORMS[idx], LessonTime.parseDayToString(DayOfWeek.of(idx + 1)));
        }
    }

    @Test
    public void equals() {
        // this -> true
        assertEquals(DEFAULT_LESSON_TIME, DEFAULT_LESSON_TIME);

        // null -> false
        assertNotEquals(DEFAULT_LESSON_TIME, null);

        // different values -> null
        LessonTime differentLessonTime = new LessonTime(DEFAULT_LESSON_TIME.dayOfWeek, LocalTime.of(10, 30));
        assertNotEquals(DEFAULT_LESSON_TIME, differentLessonTime);
        LessonTime differentLessonDay = new LessonTime(DayOfWeek.MONDAY, DEFAULT_LESSON_TIME.startTime);
        assertNotEquals(DEFAULT_LESSON_TIME, differentLessonDay);

        // different instance but same values -> true
        LessonTime otherLessonTime = new LessonTime(DEFAULT_LESSON_TIME.dayOfWeek, DEFAULT_LESSON_TIME.startTime);
        assertEquals(DEFAULT_LESSON_TIME, otherLessonTime);
    }
}
