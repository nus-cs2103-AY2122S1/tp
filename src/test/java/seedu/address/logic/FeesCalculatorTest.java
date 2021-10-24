package seedu.address.logic;

import org.junit.jupiter.api.Test;
import seedu.address.model.LastUpdatedDate;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonRates;
import seedu.address.model.lesson.OutstandingFees;
import seedu.address.model.lesson.RecurringLesson;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.TimeRange;

import java.time.DayOfWeek;
import java.util.HashSet;

class FeesCalculatorTest {
    private LastUpdatedDate lastUpdatedDate = new LastUpdatedDate("2021-10-18");
    private Lesson lesson = new RecurringLesson(new Date("09 DEC 2021"), new TimeRange("1030-1230"),
            new Subject("Math"), new HashSet<>(), new LessonRates("37.50"), new OutstandingFees("0.00"));
    private FeesCalculator feesCalculator = new FeesCalculator(new LastUpdatedDate("2021-10-18"));

    @Test
    public void getUpdatedOutstandingFees() {
        feesCalculator.getUpdatedOutstandingFees(lesson.getDayOfWeek(), lesson.getTimeRange(), lesson.getLessonRates());
    }
}