package seedu.address.logic;

import org.junit.jupiter.api.Test;
import seedu.address.model.lesson.LessonRates;
import seedu.address.model.lesson.OutstandingFees.LastAddedDate;
import seedu.address.model.lesson.TimeRange;

import java.time.DayOfWeek;

class FeesCalculatorTest {

    @Test
    public void getCostPerLesson() {
        LessonRates lessonRates = new LessonRates("50.00");
        TimeRange timeRange = new TimeRange("1030-1200");
        System.out.println("Cost per Lesson: " + FeesCalculator.getCostPerLesson(timeRange, lessonRates));
    }

    @Test
    public void numberOfLessonsSinceLastAdded() {
        FeesCalculator feesCalculator = new FeesCalculator();

        //LocalDate testLastAddedDate = LocalDate.of(2021, 10, 10);
        LastAddedDate lastAddedDate = new LastAddedDate("2021-10-22");
        DayOfWeek updateDay = DayOfWeek.MONDAY;
        System.out.println("Number of Weeks: "
                + feesCalculator.numberOfLessonsSinceLastAdded(lastAddedDate, updateDay));
    }

}