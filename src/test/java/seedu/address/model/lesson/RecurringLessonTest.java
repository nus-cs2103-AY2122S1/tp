package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_RATES;

import java.time.LocalDate;
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
    public void getDisplayDate_dateIsOver_sameDate() {
        LocalDate currDate = LocalDate.now();
        LocalDate dateOneWeekAgo = currDate.plusDays(-7);
        Lesson lesson = new RecurringLesson(new Date(dateOneWeekAgo.format(Date.FORMATTER)),
                new TimeRange(TIME_RANGE),
                new Subject(SUBJECT), HOMEWORK,
                new LessonRates(VALID_LESSON_RATES));
        assertEquals(new Date(currDate.format(Date.FORMATTER)), lesson.getDisplayDate());
    }

    @Test
    public void getDisplayDate_dateNotOver_sameDate() {
        Date currDate = new Date(LocalDate.now().format(Date.FORMATTER));
        Lesson lesson = new RecurringLesson(currDate,
                new TimeRange(TIME_RANGE),
                new Subject(SUBJECT), HOMEWORK,
                new LessonRates(VALID_LESSON_RATES));
        assertEquals(currDate, lesson.getDisplayDate());
    }

    @Test
    public void getDisplayLocalDate_dateIsOver_sameDate() {
        LocalDate currDate = LocalDate.now();
        LocalDate dateOneWeekAgo = currDate.plusDays(-7);
        Lesson lesson = new RecurringLesson(new Date(dateOneWeekAgo.format(Date.FORMATTER)),
                new TimeRange(TIME_RANGE),
                new Subject(SUBJECT), HOMEWORK,
                new LessonRates(VALID_LESSON_RATES));
        assertEquals(currDate, lesson.getDisplayLocalDate());
    }

    @Test
    public void getDisplayLocalDate_dateNotOver_sameDate() {
        Lesson lesson = new RecurringLesson(new Date(LocalDate.now().format(Date.FORMATTER)),
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK,
            new LessonRates(VALID_LESSON_RATES));
        assertEquals(LocalDate.now(), lesson.getDisplayLocalDate());
    }


    @Test
    public void isClashing() {
        Date oneWeekLaterDate = new Date("21 Jan 2021");
        Lesson lesson = new RecurringLesson(new Date(DATE),
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK,
            new LessonRates(VALID_LESSON_RATES));
        Lesson clashingLesson = new RecurringLesson(oneWeekLaterDate,
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK,
            new LessonRates(VALID_LESSON_RATES));

        assertTrue(lesson.isClashing(clashingLesson));
    }
}
