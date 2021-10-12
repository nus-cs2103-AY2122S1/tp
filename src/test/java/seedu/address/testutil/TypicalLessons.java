package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_MATHS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_SCIENCE;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonTime;
import seedu.address.model.lesson.Price;
import seedu.address.model.lesson.Subject;
import seedu.address.model.person.Grade;

public class TypicalLessons {

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

    // Test Lessons
    public static final Lesson SCIENCE_P2 = new Lesson(new Subject("Science"), new Grade("P2"),
            new LessonTime(DayOfWeek.WEDNESDAY, LocalTime.of(12, 30)), new Price(10.5));

    public static final Lesson MATH_S2 = new Lesson(new Subject("Mathematics"), new Grade("S2"),
            new LessonTime(DayOfWeek.TUESDAY, LocalTime.of(9, 30)), new Price(15.3));
    public static final Lesson PHYSICS_S2 = new Lesson(new Subject("Physics"), new Grade("S2"),
            new LessonTime(DayOfWeek.TUESDAY, LocalTime.of(9, 30)), new Price(15.3));

    private TypicalLessons() {} //prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical lessons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Lesson lesson : getTypicalLessons()) {
            ab.addLesson(lesson);
        }
        return ab;
    }

    private static List<Lesson> getTypicalLessons() {
        return new ArrayList<>(Arrays.asList(SCIENCE_P2.createClone(),
                MATH_S2.createClone(),
                PHYSICS_S2.createClone()));
    }
}
