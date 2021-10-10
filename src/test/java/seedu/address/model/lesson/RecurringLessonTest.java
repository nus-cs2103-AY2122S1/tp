package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    public void getNextDate() {
        Lesson lesson = new RecurringLesson(new Date(DATE),
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK);
        long weeks = ChronoUnit.WEEKS.between(lesson.getLocalDate(), LocalDate.now());
        LocalDate recentDate = lesson.getLocalDate().plusWeeks(weeks);
        assertEquals(recentDate, lesson.getUpcomingDate().getLocalDate());
    }


    @Test
    public void isClashing() {
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
