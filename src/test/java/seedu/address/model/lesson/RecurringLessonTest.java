package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_RATES;
import static seedu.address.model.lesson.Date.MAX_DATE;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

class RecurringLessonTest {

    /** Arbitrary constants for lesson */
    private static final String DATE = "14 Jan 2021";
    private static final String TIME_RANGE = "1430-1530";
    private static final String SUBJECT = "Math";
    private static final Set<Homework> HOMEWORK = new HashSet<>();

    @Test
    public void getNextDate_dateNotOver_sameDate() {
        Lesson lesson = new RecurringLesson(new Date(LocalDate.now().format(Date.FORMATTER)),
            MAX_DATE,
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK,
            new LessonRates(VALID_LESSON_RATES), new HashSet<>());
        assertEquals(LocalDate.now(), lesson.getDisplayDate().getLocalDate());

        Date cancelled = new Date(LocalDate.now().format(Date.FORMATTER));
        Set<Date> dates = new HashSet<>();
        dates.add(cancelled);
        Lesson lesson1 = new MakeUpLesson(new Date(LocalDate.now().format(Date.FORMATTER)),
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK,
            new LessonRates(VALID_LESSON_RATES), dates);

        Date cancelled2 = new Date(LocalDate.now().format(Date.FORMATTER));
        Set<Date> dates2 = new HashSet<>();
        dates2.add(cancelled2);
        Lesson lesson2 = new MakeUpLesson(new Date(LocalDate.now().format(Date.FORMATTER)),
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK,
            new LessonRates(VALID_LESSON_RATES), dates2);

        TreeSet<Lesson> lessonSet = new TreeSet<>();
        lessonSet.add(lesson1);
        lessonSet.add(lesson2);
        SortedSet<Lesson> unmod = Collections.unmodifiableSortedSet(lessonSet);
        assertTrue(unmod.size() == 2);
    }

    @Test
    public void isClashing() {
        Date oneWeekLaterDate = new Date("21 Jan 2021");
        Lesson lesson = new RecurringLesson(new Date(DATE),
            MAX_DATE,
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK,
            new LessonRates(VALID_LESSON_RATES), new HashSet<>());
        Lesson clashingLesson = new RecurringLesson(oneWeekLaterDate,
            MAX_DATE,
            new TimeRange(TIME_RANGE),
            new Subject(SUBJECT), HOMEWORK,
            new LessonRates(VALID_LESSON_RATES), new HashSet<>());

        assertTrue(lesson.isClashing(clashingLesson));
    }

//    @Test
//    public void getNextDate_dateNotOver_sameDate() {
//        Lesson lesson = new RecurringLesson(new Date(LocalDate.now().format(Date.FORMATTER)),
//            new TimeRange(TIME_RANGE),
//            new Subject(SUBJECT), HOMEWORK,
//            new LessonRates(VALID_LESSON_RATES));
//        assertEquals(LocalDate.now(), lesson.getDisplayDate().getLocalDate());
//    }
//
//
//    @Test
//    public void isClashing() {
//        Date oneWeekLaterDate = new Date("21 Jan 2021");
//        Lesson lesson = new RecurringLesson(new Date(DATE),
//            new TimeRange(TIME_RANGE),
//            new Subject(SUBJECT), HOMEWORK,
//            new LessonRates(VALID_LESSON_RATES));
//        Lesson clashingLesson = new RecurringLesson(oneWeekLaterDate,
//            new TimeRange(TIME_RANGE),
//            new Subject(SUBJECT), HOMEWORK,
//            new LessonRates(VALID_LESSON_RATES));
//
//        assertTrue(lesson.isClashing(clashingLesson));
//    }
}
