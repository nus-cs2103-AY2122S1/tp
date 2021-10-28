package seedu.tuitione.testutil;

import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIFTH_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FOURTH_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FOURTH_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_THIRD_LESSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.tuitione.model.Tuitione;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.student.Student;

public class TypicalTuition {

    // Test Lessons, direct copy from json equivalent
    public static final Student ALICE = TypicalStudents.ALICE;
    public static final Student BENSON = TypicalStudents.BENSON;
    public static final Student CARL = TypicalStudents.CARL;
    public static final Student ELLE = TypicalStudents.ELLE;
    public static final Student FIONA = TypicalStudents.FIONA;
    public static final Student GEORGE = TypicalStudents.GEORGE;
    public static final Lesson SCIENCE_P2 = TypicalLessons.SCIENCE_P2;
    public static final Lesson MATH_S2 = TypicalLessons.MATH_S2;
    public static final Lesson PHYSICS_S2 = TypicalLessons.PHYSICS_S2;
    public static final Lesson ENGLISH_S1 = TypicalLessons.ENGLISH_S1;
    public static final Lesson MATH_S1 = TypicalLessons.MATH_S1;

    private TypicalTuition() {} //prevents instantiation

    /**
     * Returns an {@code Tuitione} with a typical tuition setup.
     */
    public static Tuitione getTypicalTuitione() {
        Tuitione tuitione = new Tuitione();

        // set up associations
        List<Student> students = getTypicalStudents();
        List<Lesson> lessons = getTypicalLessons();

        // BENSON enrolled in MATH_S2 lesson
        lessons.get(INDEX_FOURTH_LESSON.getZeroBased())
                .enrollStudent(students.get(INDEX_SECOND_STUDENT.getZeroBased()));

        // ELLE enrolled in MATH_S1 lesson
        lessons.get(INDEX_THIRD_LESSON.getZeroBased())
                .enrollStudent(students.get(INDEX_FOURTH_STUDENT.getZeroBased()));

        // FIONA enrolled in ENGLISH_S1 lesson
        lessons.get(INDEX_SECOND_LESSON.getZeroBased())
                .enrollStudent(students.get(INDEX_FIFTH_STUDENT.getZeroBased()));

        // FIONA enrolled in MATH_S1 lesson
        lessons.get(INDEX_THIRD_LESSON.getZeroBased())
                .enrollStudent(students.get(INDEX_FIFTH_STUDENT.getZeroBased()));

        for (Student student : students) {
            tuitione.addStudent(student);
        }
        for (Lesson lesson : lessons) {
            tuitione.addLesson(lesson);
        }
        return tuitione;
    }

    private static List<Lesson> getTypicalLessons() {
        return new ArrayList<>(Arrays.asList(new LessonBuilder(SCIENCE_P2).build(),
                new LessonBuilder(ENGLISH_S1).build(), new LessonBuilder(MATH_S1).build(),
                new LessonBuilder(MATH_S2).build(), new LessonBuilder(PHYSICS_S2).build()));
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(new StudentBuilder(ALICE).build(),
                new StudentBuilder(BENSON).build(), new StudentBuilder(CARL).build(),
                new StudentBuilder(ELLE).build(), new StudentBuilder(FIONA).build(),
                new StudentBuilder(GEORGE).build()));
    }
}
