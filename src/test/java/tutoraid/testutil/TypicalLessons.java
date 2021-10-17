package tutoraid.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tutoraid.model.LessonBook;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Student;

/**
 * A utility class containing a list of {@code Lesson} objects to be used in tests.
 */
public class TypicalLessons {

    private static final Student ALICE = new StudentBuilder()
            .withStudentName("Alice Pauline")
            .withStudentPhone("94351234")
            .withParentName("Mrs Tan")
            .withParentPhone("94351253")
            .withProgress("No Progress")
            .withPaymentStatus(false)
            .build();
    private static ArrayList<Student> typicalStudentsInLesson = new ArrayList<>(Arrays.asList(ALICE));

    public static final Lesson MATHS_ONE = new LessonBuilder()
            .withLessonName("Maths 1")
            .withCapacity("50")
            .withPrice("100")
            .withStudents(typicalStudentsInLesson)
            .withTiming("1000-1200")
            .build();
    public static final Lesson SCIENCE_ONE = new LessonBuilder()
            .withLessonName("Science 1")
            .withCapacity("50")
            .withPrice("125")
            .withStudents(typicalStudentsInLesson)
            .withTiming("1400-1600")
            .build();

    private TypicalLessons() {
    } // prevents instantiation

    /**
     * Returns an {@code LessonBook} with all the typical lessons.
     */
    public static LessonBook getTypicalLessonBook() {
        LessonBook lb = new LessonBook();
        for (Lesson lesson : getTypicalLessons()) {
            lb.addLesson(lesson);
        }
        return lb;
    }

    public static List<Lesson> getTypicalLessons() {
        return new ArrayList<>(Arrays.asList(MATHS_ONE, SCIENCE_ONE));
    }
}
