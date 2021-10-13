package seedu.address.testutil;

import seedu.address.model.lesson.Lesson;

/**
 * A utility class containing a list of {@code Lesson} objects to be used in tests.
 */
public class TypicalLessons {
    public static final Lesson MON_10_12_BIOLOGY = new LessonBuilder().withTimeslot("10:00", "12:00")
            .withDayOfWeek(1).withSubject("Biology").build();
    public static final Lesson MON_11_13_MATH = new LessonBuilder().withTimeslot("11:00", "13:00")
            .withDayOfWeek(1).withSubject("Math").build();
    public static final Lesson MON_16_18_MATH = new LessonBuilder().withTimeslot("16:00", "18:00")
            .withDayOfWeek(1).withSubject("Math").build();
    public static final Lesson TUE_16_18_ENGLISH = new LessonBuilder().withTimeslot("16:00", "18:00")
            .withDayOfWeek(2).withSubject("English").build();
}
