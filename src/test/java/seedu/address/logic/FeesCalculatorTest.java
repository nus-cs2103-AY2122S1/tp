package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.LastUpdatedDate;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Lesson;
import seedu.address.testutil.LessonBuilder;

class FeesCalculatorTest {
    private final LastUpdatedDate lastUpdatedDate = new LastUpdatedDate("2021-10-23T00:00");
    private FeesCalculator feesCalculator;

    @Test
    public void updateLessonOutstandingFeesField_recurringLessons() {
        Lesson expected;
        // Last Updated within same week, lesson has passed since
        // Last updated: Monday 1200, Current: Tuesday 1200, Lesson: 1400-1500
        feesCalculator = new FeesCalculator(new LastUpdatedDate("2021-10-25T12:00"),
                LocalDateTime.parse("2021-10-27T12:00"));
        Lesson lessonOnMondayAfterLastUpdate = new LessonBuilder().withDate("25 OCT 2021").buildRecurring();
        expected = new LessonBuilder().withDate("25 OCT 2021").withOutstandingFees("150").buildRecurring();
        assertEquals(expected, feesCalculator.updateLessonOutstandingFeesField(lessonOnMondayAfterLastUpdate));


        // Last updated within the same week, lesson has not passed since last updated
        feesCalculator = new FeesCalculator(new LastUpdatedDate("2021-10-26T12:00"),
                LocalDateTime.parse("2021-10-27T12:00"));
        Lesson sameWeekNotPassedLesson = new LessonBuilder().withDate("9 SEP 2021").buildRecurring();
        assertEquals(sameWeekNotPassedLesson, feesCalculator.updateLessonOutstandingFeesField(sameWeekNotPassedLesson));

        // Last updated today
        feesCalculator = new FeesCalculator(new LastUpdatedDate("2021-10-27T12:00"),
                LocalDateTime.parse("2021-10-27T15:02"));
        // before lesson
        Lesson sameDayToBeUpdated = new LessonBuilder().withDate("27 OCT 2021").buildRecurring();
        expected = new LessonBuilder().withDate("27 OCT 2021").withOutstandingFees("150").buildRecurring();
        assertEquals(sameDayToBeUpdated, feesCalculator.updateLessonOutstandingFeesField(sameDayToBeUpdated));
        // after lesson
        Lesson sameDayNotToBeUpdated = new LessonBuilder().withDate("27 OCT 2021").buildRecurring();
        assertEquals(sameDayNotToBeUpdated, feesCalculator.updateLessonOutstandingFeesField(sameDayNotToBeUpdated));

        // lesson ends right after last updated, should update
        feesCalculator = new FeesCalculator(new LastUpdatedDate("2021-10-23T14:59"),
                LocalDateTime.parse("2021-10-25T17:30"));
        Lesson rightAfterLastUpdate = new LessonBuilder().withDate("23 OCT 2021").buildRecurring();
        expected = new LessonBuilder().withDate("23 OCT 2021").withOutstandingFees("150").buildRecurring();
        assertEquals(expected, feesCalculator.updateLessonOutstandingFeesField(rightAfterLastUpdate));

        // lesson ends right before last updated, should not update
        feesCalculator = new FeesCalculator(new LastUpdatedDate("2021-10-23T15:01"),
                LocalDateTime.parse("2021-10-25T17:30"));
        Lesson rightBeforeLastUpdate = new LessonBuilder().withDate("23 OCT 2021").buildRecurring();
        assertEquals(rightBeforeLastUpdate, feesCalculator.updateLessonOutstandingFeesField(rightBeforeLastUpdate));

        // With cancelled date between last updated and current date
        feesCalculator = new FeesCalculator(new LastUpdatedDate("2021-10-18T12:00"),
                LocalDateTime.parse("2021-10-25T12:00"));
        Lesson withCancelledDateBetween = new LessonBuilder()
                .withDate("16 OCT 2021")
                .withCancelledDatesSet(new Date("23 OCT 2021"))
                .buildRecurring();
        assertEquals(withCancelledDateBetween,
                feesCalculator.updateLessonOutstandingFeesField(withCancelledDateBetween));
    }

    @Test
    public void updateLessonOutstandingFeesField_makeUpLesson() {
        // MakeUp Lesson in the past do not update
        feesCalculator = new FeesCalculator(new LastUpdatedDate("2021-10-23T12:00"), LocalDateTime.now());
        Lesson pastMakeUpLesson = new LessonBuilder().withDate("12 OCT 2021").build();
        assertEquals(pastMakeUpLesson, feesCalculator.updateLessonOutstandingFeesField(pastMakeUpLesson));

        // MakeUp Lesson between last update and current date, should update
        feesCalculator = new FeesCalculator(new LastUpdatedDate("2021-10-25T12:20"),
                LocalDateTime.parse("2021-10-27T22:10"));
        Lesson lessonBetweenLastUpdateAndToday = new LessonBuilder().withDate("26 OCT 2021").build();
        Lesson expectedLessonBetweenUpdateAndToday = new LessonBuilder().withDate("26 OCT 2021")
                .withOutstandingFees("150").build();
        assertEquals(expectedLessonBetweenUpdateAndToday,
                feesCalculator.updateLessonOutstandingFeesField(lessonBetweenLastUpdateAndToday));
    }

    @Test
    public void getUpdateOutstandingFees_success() {

    }
}
