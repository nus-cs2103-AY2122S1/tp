package seedu.address.model.module.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Link;
import seedu.address.model.module.ModBookDate;
import seedu.address.model.module.ModBookTime;
import seedu.address.model.module.Timeslot;
import seedu.address.model.module.Venue;

public class ExamTest {
    private static final ExamName EXAM_NAME = new ExamName("Midterms");
    private static final ModBookDate DATE = new ModBookDate("11/12/2021");
    private static final ModBookTime START_TIME = new ModBookTime("09:00");
    private static final ModBookTime END_TIME = new ModBookTime("11:00");
    private static final Timeslot TIMESLOT = new Timeslot(START_TIME, END_TIME);
    private static final Optional<Venue> VENUE = Optional.of(new Venue("University Sports Centre"));
    private static final Optional<Link> LINK = Optional.of(new Link("http://nus-sg.zoom.us/248501794"));
    private static final Exam EXAM = new Exam(EXAM_NAME, DATE, TIMESLOT, VENUE, LINK);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Exam(
                EXAM_NAME, DATE, null, null, null));
        assertThrows(NullPointerException.class, () -> new Exam(
                EXAM_NAME, null, TIMESLOT, VENUE, LINK));
        assertThrows(NullPointerException.class, () -> new Exam(
                null, null, null, null, null));
    }

    @Test
    void isSameExam() {
        // Same object
        assertTrue(EXAM.isSameExam(EXAM));

        // Different object, same name
        assertTrue(EXAM.isSameExam(new Exam(EXAM_NAME, DATE, TIMESLOT, VENUE, LINK)));

        // Different name
        assertFalse(EXAM.isSameExam(new Exam(new ExamName("Finals"), DATE, TIMESLOT, VENUE, LINK)));
    }

    @Test
    void compareTo() {
        ModBookDate dateTwo = new ModBookDate("11/11/2021");
        ModBookTime startTimeTwo = new ModBookTime("09:30");
        ModBookTime endTimeTwo = new ModBookTime("11:30");
        Timeslot timeslotTwo = new Timeslot(startTimeTwo, endTimeTwo);

        // different date, same time
        assertTrue(EXAM.compareTo(new Exam(EXAM_NAME, dateTwo, TIMESLOT, VENUE, LINK)) > 0);

        // same date, different time
        assertTrue(EXAM.compareTo(new Exam(EXAM_NAME, DATE, timeslotTwo, VENUE, LINK)) < 0);

        // different date, different time
        assertTrue(EXAM.compareTo(new Exam(EXAM_NAME, dateTwo, timeslotTwo, VENUE, LINK)) > 0);

        // same date, same time
        assertEquals(EXAM.compareTo(new Exam(EXAM_NAME, DATE, TIMESLOT, VENUE, LINK)), 0);
    }

    @Test
    void isFuture() {
        ModBookDate today = new ModBookDate(LocalDate.now().format(ModBookDate.PRINT_FORMATTER));
        ModBookDate tomorrow = new ModBookDate(LocalDate.now().plusDays(1).format(ModBookDate.PRINT_FORMATTER));
        ModBookDate yesterday = new ModBookDate(LocalDate.now().minusDays(1).format(ModBookDate.PRINT_FORMATTER));

        Timeslot oneMinuteFromNow = new Timeslot(
                new ModBookTime(LocalTime.now().plusMinutes(1).format(ModBookTime.PRINT_FORMATTER)),
                new ModBookTime(LocalTime.now().plusMinutes(2).format(ModBookTime.PRINT_FORMATTER)));
        Timeslot oneMinuteAgo = new Timeslot(
                new ModBookTime(LocalTime.now().minusMinutes(1).format(ModBookTime.PRINT_FORMATTER)),
                new ModBookTime(LocalTime.now().format(ModBookTime.PRINT_FORMATTER)));

        assertTrue(new Exam(EXAM_NAME, today, oneMinuteFromNow, VENUE, LINK).isFuture());
        assertTrue(new Exam(EXAM_NAME, tomorrow, oneMinuteFromNow, VENUE, LINK).isFuture());
        assertTrue(new Exam(EXAM_NAME, tomorrow, oneMinuteAgo, VENUE, LINK).isFuture());

        assertFalse(new Exam(EXAM_NAME, today, oneMinuteAgo, VENUE, LINK).isFuture());
        assertFalse(new Exam(EXAM_NAME, yesterday, oneMinuteAgo, VENUE, LINK).isFuture());
        assertFalse(new Exam(EXAM_NAME, yesterday, oneMinuteFromNow, VENUE, LINK).isFuture());
    }
}
