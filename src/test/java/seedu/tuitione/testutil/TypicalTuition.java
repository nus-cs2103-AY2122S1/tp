package seedu.tuitione.testutil;

import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

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
    public static final Lesson SCIENCE_P2 = TypicalLessons.SCIENCE_P2;
    public static final Lesson MATH_S2 = TypicalLessons.MATH_S2;
    public static final Lesson PHYSICS_S2 = TypicalLessons.PHYSICS_S2;

    private TypicalTuition() {} //prevents instantiation

    /**
     * Returns an {@code Tuitione} with a typical tuition setup.
     */
    public static Tuitione getTypicalTuitione() {
        Tuitione tuitione = new Tuitione();

        // set up associations
        List<Student> students = getTypicalStudents();
        List<Lesson> lessons = getTypicalLessons();
        lessons.get(INDEX_SECOND_LESSON.getZeroBased())
                .enrollStudent(students.get(INDEX_SECOND_STUDENT.getZeroBased())); //BENSON enrolled in MATH_S2 lesson

        for (Student student : students) {
            tuitione.addStudent(student);
        }
        for (Lesson lesson : lessons) {
            tuitione.addLesson(lesson);
        }
        return tuitione;
    }

    private static List<Lesson> getTypicalLessons() {
        return new ArrayList<>(Arrays.asList(new LessonBuilder(SCIENCE_P2).build(), new LessonBuilder(MATH_S2).build(),
                new LessonBuilder(PHYSICS_S2).build()));
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(new StudentBuilder(ALICE).build(),
                new StudentBuilder(BENSON).build(), new StudentBuilder(CARL).build()));
    }
}
