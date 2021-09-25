package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class LessonTest {

    /** Arbitrary constants for lesson */
    private static final String DATE = "14 Jan 2022";
    private static final String START_TIME = "14:30";
    private static final String END_TIME = "15:30";
    private static final String SUBJECT = "Math";
    private static final Set<Homework> HOMEWORK = new HashSet<>();


    @Test
    public void updateDateWithWeek_validDateString_success() {
        DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd MMM uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
        String validOneWeekLaterDateString = "21 Jan 2022";
        Lesson lesson = new RecurringLesson(new Date(DATE), new Time(START_TIME), new Time(END_TIME),
            new Subject(SUBJECT), HOMEWORK);
        assertEquals(new Date(validOneWeekLaterDateString), lesson.updateDateWithWeek());
    }

}

