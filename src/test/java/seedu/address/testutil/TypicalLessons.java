package seedu.address.testutil;

import seedu.address.model.lesson.Lesson;

import static seedu.address.logic.commands.CommandTestUtil.*;

public class TypicalLessons {
    // all fields present
    public static final Lesson RECURRING_LESSON = new LessonBuilder().withDate("12 Jan 2000")
            .withTimeRange("1400-1900").withSubject("Science")
            .withHomeworkSet("As1").withLessonRates("50.00")
            .buildRecurring();

    public static final Lesson MAKEUP_LESSON = new LessonBuilder().withDate("12 Jan 2000")
            .withTimeRange("1400-1900").withSubject("Science")
            .withHomeworkSet("Ex3").withLessonRates("50.00")
            .build();

    // all fields present with multiple homework
    public static final Lesson RECURRING_LESSON2 = new LessonBuilder().withDate("12 Jan 2000")
            .withTimeRange("1400-1900").withSubject("Science")
            .withHomeworkSet("As1", "Ex3").withLessonRates("30.00")
            .buildRecurring();

    public static final Lesson MAKEUP_LESSON2 = new LessonBuilder().withDate("12 Jan 2000")
            .withTimeRange("1400-1900").withSubject("Science")
            .withHomeworkSet("As1", "Ex3").withLessonRates("30.00")
            .build();

    // some optional fields missing
    public static final Lesson RECURRING_LESSON3 = new LessonBuilder().withDate("12 Jan 2000")
            .withTimeRange("1400-1900").withSubject("Science")
            .withLessonRates("50.00").buildRecurring();

    public static final Lesson MAKEUP_LESSON3 = new LessonBuilder().withDate("12 Jan 2000")
            .withTimeRange("1400-1900").withSubject("Science")
            .withLessonRates("50.00").build();

    // Manually added - Lesson's details found in {@code CommandTestUtil}
    public static final Lesson PAST_RECURRING_LESSON = new LessonBuilder().withDate(VALID_DATE_PAST)
            .withTimeRange(VALID_TIME_RANGE).withSubject(VALID_SUBJECT)
            .withHomeworkSet(VALID_HOMEWORK_POETRY).withLessonRates(VALID_LESSON_RATES)
            .buildRecurring();

    public static final Lesson PAST_MAKEUP_LESSON = new LessonBuilder().withDate(VALID_DATE_PAST)
            .withTimeRange(VALID_TIME_RANGE).withSubject(VALID_SUBJECT)
            .withHomeworkSet(VALID_HOMEWORK_POETRY).withLessonRates(VALID_LESSON_RATES)
            .build();

    public static final Lesson FUTURE_RECURRING_LESSON = new LessonBuilder().withDate(VALID_DATE_FUTURE)
            .withTimeRange(VALID_TIME_RANGE).withSubject(VALID_SUBJECT)
            .withHomeworkSet(VALID_HOMEWORK_POETRY).withLessonRates(VALID_LESSON_RATES)
            .buildRecurring();

    public static final Lesson FUTURE_MAKEUP_LESSON = new LessonBuilder().withDate(VALID_DATE_FUTURE)
            .withTimeRange(VALID_TIME_RANGE).withSubject(VALID_SUBJECT)
            .withHomeworkSet(VALID_HOMEWORK_POETRY).withLessonRates(VALID_LESSON_RATES)
            .build();
}
