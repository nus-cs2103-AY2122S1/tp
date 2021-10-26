package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OUTSTANDING_FEES;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.LastUpdatedDate;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.OutstandingFees;
import seedu.address.testutil.LessonBuilder;

class FeesCalculatorTest {
    private final LastUpdatedDate lastUpdatedDate = new LastUpdatedDate("2021-10-23T00:00");
    private FeesCalculator feesCalculator =
            new FeesCalculator(lastUpdatedDate, LocalDateTime.parse("2021-10-27T03:00"));

    @Test
    public void updateLessonOutstandingFeesField_recurringLessons() {
        // Last updated within the same week, lesson has passed since last updated

        // Last updated within the same week, lesson has not passed since last updated
        Lesson sameWeekNotPassedLesson = new LessonBuilder().withDate("8 SEP 2021").buildRecurring();
        assertEquals(sameWeekNotPassedLesson, feesCalculator.updateLessonOutstandingFeesField(sameWeekNotPassedLesson));

        // Last updated several weeks ago

        // Last updated today

        //MakeUp Lesson in the past do not update
        Lesson pastMakeUpLesson = new LessonBuilder().withDate("12 OCT 2021").build();
        assertEquals(pastMakeUpLesson, feesCalculator.updateLessonOutstandingFeesField(pastMakeUpLesson));
    }

    @Test
    public void getUpdateOutstandingFees_success() {
        OutstandingFees outstandingFees = new OutstandingFees(VALID_OUTSTANDING_FEES); // start out at 100.00
    }
}
