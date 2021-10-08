package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class RecurringLessonTest {

    /** Arbitrary constants for lesson */
    private static final String DATE = "14 Jan 2021";
    private static final String TIME_RANGE = "1430-1530";
    private static final String SUBJECT = "Math";
    private static final Set<Homework> HOMEWORK = new HashSet<>();

    @Test
    void updateDate_validLesson_success() {
        Lesson lesson = new RecurringLesson(new Date(DATE),
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK);
        Lesson updatedLesson = lesson.updateDate();

        assertEquals(lesson.getDate(), updatedLesson.getStartDate());
        assertEquals(lesson.getStartDate(), updatedLesson.getStartDate());
    }

    @Test
    void getStartDate_outdatedLessonDate_success() {
        Lesson lesson = new RecurringLesson(new Date(DATE),
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK);
        Lesson updatedLesson = lesson.updateDate();
        assertEquals(updatedLesson.getStartDate(), new Date(DATE));
    }

    @Test
    void isClashing() {
        Date oneWeekLaterDate = new Date("21 Jan 2021");
        Lesson lesson = new RecurringLesson(new Date(DATE),
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK);
        Lesson clashingLesson = new RecurringLesson(oneWeekLaterDate,
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK);

        assertTrue(lesson.isClashing(clashingLesson));
    }
}
