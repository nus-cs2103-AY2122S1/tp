package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASHING_TIME_RANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_NEXT_MON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_PREV_MON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NON_CLASHING_TIME_RANGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_RANGE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LessonBuilder;

class MakeupLessonTest {

    @Test
    public void isCancelled_returnsFalse() {
        Lesson makeup = new LessonBuilder().withDate(VALID_DATE_MON).build();

        assertFalse(makeup.isCancelled());
    }

    @Test
    public void isCancelled_returnsTrue() {
        Lesson makeup = new LessonBuilder().withDate(VALID_DATE_MON).withCancelledDatesSet(VALID_DATE_MON).build();

        assertTrue(makeup.isCancelled());
    }

    @Test
    public void isClashing_isCancelled_returnsFalse() {
        // does not clash with other makeup or recurring lesson if lesson isCancelled
        Lesson makeupCancelled = new LessonBuilder().withDate(VALID_DATE_MON)
                .withCancelledDatesSet(VALID_DATE_MON).build();
        Lesson recurringLesson = new LessonBuilder().withDate(VALID_DATE_PREV_MON).buildRecurring();
        Lesson makeupLesson = new LessonBuilder().withDate(VALID_DATE_MON).build();

        assertFalse(makeupCancelled.isClashing(makeupLesson));
        assertFalse(makeupCancelled.isClashing(recurringLesson));

        // does not clash with another cancelled makeup lesson
        assertFalse(makeupLesson.isClashing(makeupCancelled));
    }

    @Test
    public void isClashing_withMakeupLesson_returnsTrue() {
        // clashes with makeup lesson on same day, with clashing time
        Lesson makeupLesson = new LessonBuilder().withTimeRange(VALID_TIME_RANGE).build();
        Lesson makeupLessonClashing = new LessonBuilder().withTimeRange(VALID_CLASHING_TIME_RANGE).build();

        assertTrue(makeupLesson.isClashing(makeupLessonClashing));
    }

    @Test
    public void isClashing_withMakeupLesson_returnsFalse() {
        // does not clash with makeup lesson on same day, with non-clashing time
        Lesson makeupLesson = new LessonBuilder().withTimeRange(VALID_TIME_RANGE).build();
        Lesson makeupLessonNonClashing = new LessonBuilder().withTimeRange(VALID_NON_CLASHING_TIME_RANGE).build();

        assertFalse(makeupLesson.isClashing(makeupLessonNonClashing));
    }

    @Test
    public void isClashing_withRecurringLesson_returnsTrue() {
        // clashes with recurring lesson on same day of week, with clashing time
        Lesson makeupLesson = new LessonBuilder().withDate(VALID_DATE_MON).withTimeRange(VALID_TIME_RANGE).build();
        Lesson recurringLessonClashing = new LessonBuilder().withDate(VALID_DATE_PREV_MON)
                .withTimeRange(VALID_CLASHING_TIME_RANGE).buildRecurring();

        assertTrue(makeupLesson.isClashing(recurringLessonClashing));
    }

    @Test
    public void isClashing_withRecurringLesson_returnsFalse() {
        // does not clash with recurring lesson on same day, with non-clashing time
        Lesson makeupLesson = new LessonBuilder().withDate(VALID_DATE_MON).withTimeRange(VALID_TIME_RANGE).build();
        Lesson recurringLessonNonClashing = new LessonBuilder().withDate(VALID_DATE_PREV_MON)
                .withTimeRange(VALID_NON_CLASHING_TIME_RANGE).buildRecurring();

        assertFalse(makeupLesson.isClashing(recurringLessonNonClashing));
    }

    @Test
    public void isClashing_withRecurringLessonCancelled_returnsFalse() {
        // does not clash with recurring lesson on a cancelled recurring day
        Lesson makeupLesson = new LessonBuilder().withDate(VALID_DATE_MON).build();
        Lesson recurringLesson = new LessonBuilder().withDate(VALID_DATE_PREV_MON)
                .withCancelledDatesSet(VALID_DATE_MON).buildRecurring();

        assertFalse(makeupLesson.isClashing(recurringLesson));
    }

    @Test
    public void hasLessonOnDate_returnsTrue() {
        // date lies on makeup lesson date and not cancelled
        Lesson makeupLesson = new LessonBuilder().withDate(VALID_DATE_MON).build();
        Date date = new Date(VALID_DATE_MON);

        assertTrue(makeupLesson.hasLessonOnDate(date));
    }

    @Test
    public void hasLessonOnDate_returnsFalse() {
        // date does not lie on makeup lesson date
        Lesson makeupLessonMon = new LessonBuilder().withDate(VALID_DATE_MON).build();
        Date dateTue = new Date(VALID_DATE_TUE);

        assertFalse(makeupLessonMon.hasLessonOnDate(dateTue));

        // date lies on makeup lesson date and is cancelled
        Lesson makeupLessonCancelled = new LessonBuilder().withDate(VALID_DATE_MON)
                .withCancelledDatesSet(VALID_DATE_MON).build();

        Date dateMon = new Date(VALID_DATE_NEXT_MON);
        assertFalse(makeupLessonCancelled.hasLessonOnDate(dateMon));
    }

    @Test
    public void compareTo_equalsCancelledMakeupLesson_notZero() {
        // Cancelled makeup lessons which are equals should not be equal in compareTo
        Lesson cancelled = new LessonBuilder().withDate(VALID_DATE_MON).withCancelledDatesSet(VALID_DATE_MON).build();
        Lesson cancelledCopy =
                new LessonBuilder().withDate(VALID_DATE_MON).withCancelledDatesSet(VALID_DATE_MON).build();

        assertTrue(cancelled.equals(cancelledCopy));
        assertFalse(cancelled.compareTo(cancelledCopy) == 0);

        // Non-cancelled makeup lessons which are equals should be equal in compareTo
        Lesson nonCancelled = new LessonBuilder().withDate(VALID_DATE_MON).build();
        Lesson nonCancelledCopy = new LessonBuilder().withDate(VALID_DATE_MON).build();

        assertTrue(nonCancelled.equals(nonCancelledCopy));
        assertTrue(nonCancelled.compareTo(nonCancelledCopy) == 0);
    }
}
