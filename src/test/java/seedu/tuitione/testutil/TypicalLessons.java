package seedu.tuitione.testutil;

import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_GRADE_MATHS;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_GRADE_SCIENCE;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_PRICE_MATHS;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_PRICE_SCIENCE;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_SUBJECT_MATHS;
import static seedu.tuitione.logic.commands.CommandTestUtil.VALID_SUBJECT_SCIENCE;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.tuitione.model.Tuitione;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonTime;
import seedu.tuitione.model.lesson.Price;
import seedu.tuitione.model.lesson.Subject;
import seedu.tuitione.model.student.Grade;

public class TypicalLessons {

    // Test Lessons, direct copy from json equivalent
    public static final Lesson SCIENCE_P2 = new Lesson(new Subject("Science"), new Grade("P2"),
            new LessonTime(DayOfWeek.WEDNESDAY, LocalTime.of(12, 30)), new Price(10.5));
    public static final Lesson MATH_S2 = new Lesson(new Subject("Mathematics"), new Grade("S2"),
            new LessonTime(DayOfWeek.TUESDAY, LocalTime.of(9, 30)), new Price(15.3));
    public static final Lesson PHYSICS_S2 = new Lesson(new Subject("Physics"), new Grade("S2"),
            new LessonTime(DayOfWeek.TUESDAY, LocalTime.of(9, 30)), new Price(15.3));
    public static final Lesson ENGLISH_S1 = new Lesson(new Subject("English"), new Grade("S1"),
            new LessonTime(DayOfWeek.THURSDAY, LocalTime.of(13, 00)), new Price(17.0));
    public static final Lesson MATH_S1 = new Lesson(new Subject("Mathematics"), new Grade("S1"),
            new LessonTime(DayOfWeek.FRIDAY, LocalTime.of(10, 30)), new Price(19.2));

    // Manually added - Lesson's details found in {@code CommandTestUtil}
    public static final Lesson MATHS = new LessonBuilder()
            .withSubject(VALID_SUBJECT_MATHS)
            .withGrade(VALID_GRADE_MATHS)
            .withLessonTime(new LessonTime(DayOfWeek.FRIDAY, LocalTime.of(13, 00)))
            .withPrice(Double.parseDouble(VALID_PRICE_MATHS))
            .build();

    public static final Lesson SCIENCE = new LessonBuilder()
            .withSubject(VALID_SUBJECT_SCIENCE)
            .withGrade(VALID_GRADE_SCIENCE)
            .withLessonTime(new LessonTime(DayOfWeek.WEDNESDAY, LocalTime.of(12, 00)))
            .withPrice(Double.parseDouble(VALID_PRICE_SCIENCE))
            .build();

    private TypicalLessons() {} //prevents instantiation

    /**
     * Returns an {@code Tuitione} with all the typical lessons.
     */
    public static Tuitione getTypicalTuitione() {
        Tuitione ab = new Tuitione();
        for (Lesson lesson : getTypicalLessons()) {
            ab.addLesson(lesson);
        }
        return ab;
    }

    private static List<Lesson> getTypicalLessons() {
        return new ArrayList<>(Arrays.asList(new LessonBuilder(SCIENCE_P2).build(),
                new LessonBuilder(MATH_S2).build(),
                new LessonBuilder(PHYSICS_S2).build(),
                new LessonBuilder(ENGLISH_S1).build(),
                new LessonBuilder(MATH_S1).build()));
    }
}
