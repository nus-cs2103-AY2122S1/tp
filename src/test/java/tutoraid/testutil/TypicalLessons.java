package tutoraid.testutil;

import static tutoraid.logic.commands.CommandTestUtil.VALID_CAPACITY_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_CAPACITY_SCIENCE_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_LESSON_NAME_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_LESSON_NAME_SCIENCE_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PRICE_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PRICE_SCIENCE_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_TIMING_MATHS_TWO;
import static tutoraid.logic.commands.CommandTestUtil.VALID_TIMING_SCIENCE_TWO;

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

    public static final Student ALICE = new StudentBuilder()
            .withStudentName("Alice Pauline")
            .withStudentPhone("94351234")
            .withParentName("Mrs Tan")
            .withParentPhone("94351253")
            .withProgressList(new ArrayList<>())
            .withLessons(new ArrayList<>(Arrays.asList("Maths 1", "Science 1")))
            .build();

    public static final Lesson MATHS_ONE = new LessonBuilder()
            .withLessonName("Maths 1")
            .withCapacity("50")
            .withPrice("100")
            .withStudents(new ArrayList<>(Arrays.asList(ALICE)))
            .withTiming("1000-1200")
            .build();
    public static final Lesson SCIENCE_ONE = new LessonBuilder()
            .withLessonName("Science 1")
            .withCapacity("50")
            .withPrice("125")
            .withStudents(new ArrayList<>(Arrays.asList(ALICE)))
            .withTiming("1400-1600")
            .build();

    // Manually added
    public static final Lesson ENGLISH_ONE = new LessonBuilder()
            .withLessonName("English 1")
            .withCapacity("40")
            .withPrice("110")
            .withStudents(new ArrayList<>(Arrays.asList(ALICE)))
            .withTiming("1300-1400")
            .build();
    public static final Lesson ENGLISH_TWO = new LessonBuilder()
            .withLessonName("English 2")
            .withCapacity("30")
            .withPrice("130")
            .withStudents(new ArrayList<>(Arrays.asList(ALICE)))
            .withTiming("1800-1900")
            .build();

    // Manually added - Lesson's details found in {@code CommandTestUtil}
    public static final Lesson MATHS_TWO = new LessonBuilder()
            .withLessonName(VALID_LESSON_NAME_MATHS_TWO)
            .withCapacity(VALID_CAPACITY_MATHS_TWO)
            .withPrice(VALID_PRICE_MATHS_TWO)
            .withTiming(VALID_TIMING_MATHS_TWO)
            .withStudents(new ArrayList<>())
            .build();
    public static final Lesson SCIENCE_TWO = new LessonBuilder()
            .withLessonName(VALID_LESSON_NAME_SCIENCE_TWO)
            .withCapacity(VALID_CAPACITY_SCIENCE_TWO)
            .withPrice(VALID_PRICE_SCIENCE_TWO)
            .withTiming(VALID_TIMING_SCIENCE_TWO)
            .withStudents(new ArrayList<>())
            .build();

    public static final Lesson MATHS_TWO_NO_STUDENTS = new LessonBuilder()
            .withLessonName(VALID_LESSON_NAME_MATHS_TWO)
            .withCapacity(VALID_CAPACITY_MATHS_TWO)
            .withPrice(VALID_PRICE_MATHS_TWO)
            .withTiming(VALID_TIMING_MATHS_TWO)
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

    /**
     * Returns an {@code LessonBook} with all the typical lessons but no students.
     */
    public static LessonBook getTypicalLessonBookWithoutStudents() {
        LessonBook lb = new LessonBook();
        for (Lesson lesson : getTypicalLessonsWithoutStudents()) {
            lb.addLesson(lesson);
        }
        return lb;
    }

    public static List<Lesson> getTypicalLessons() {
        return new ArrayList<>(Arrays.asList(MATHS_ONE, SCIENCE_ONE));
    }

    public static List<Lesson> getTypicalLessonsWithoutStudents() {
        return new ArrayList<>(List.of(MATHS_TWO_NO_STUDENTS));
    }
}
