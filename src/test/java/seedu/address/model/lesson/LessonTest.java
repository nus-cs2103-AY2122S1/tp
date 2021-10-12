package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.person.Student;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.PersonBuilder;

public class LessonTest {

    private Lesson defaultLesson;

    @BeforeEach
    public void setUp() {
        defaultLesson = LessonBuilder.getDefault(); // reset defaultLesson
    }

    @Test
    public void constructor_validSubject_returnsSubject() {
        Lesson sameLesson = LessonBuilder.getDefault();
        assertEquals(defaultLesson, sameLesson);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lesson(null, null, null, null));
    }

    @Test
    public void isSameLesson() {
        // this -> true
        assertTrue(defaultLesson.isSameLesson(defaultLesson));

        // new instance with same (default) lesson details -> true
        Lesson otherLesson = new LessonBuilder().build();
        assertTrue(defaultLesson.isSameLesson(otherLesson));

        // different lesson code -> false
        Lesson differentLesson = new LessonBuilder().withSubject("Different").build(); // change subject to change code
        assertFalse(defaultLesson.isSameLesson(differentLesson));
    }

    @Test
    public void isAbleToEnroll() {
        // null -> false
        assertFalse(defaultLesson.isAbleToEnroll(null));

        // eligible student -> true
        Student eligibleStudent = new PersonBuilder().build();
        assertTrue(defaultLesson.isAbleToEnroll(eligibleStudent));

        // different grade student -> false
        Student differentGradeStudent = new PersonBuilder().withGrade("P5").build();
        assertFalse(defaultLesson.isAbleToEnroll(differentGradeStudent));

        // student with clashing timing -> false
        Student busyStudent = new PersonBuilder().build();
        Lesson clashingLesson = LessonBuilder.getDefault();
        clashingLesson.addStudent(busyStudent);
        assertFalse(defaultLesson.isAbleToEnroll(busyStudent));

        // student already enrolled -> false
        Student enrolledStudent = new PersonBuilder().build();
        defaultLesson.addStudent(enrolledStudent);
        assertFalse(defaultLesson.isAbleToEnroll(enrolledStudent));
    }

    @Test
    public void isAbleToUnenroll() {}

    @Test
    public void containsStudent() {
        Student enrolledStudent = new PersonBuilder().build();

        // student not present
        assertFalse(defaultLesson.containsStudent(enrolledStudent));

        // student present
        defaultLesson.addStudent(enrolledStudent);
        assertTrue(defaultLesson.containsStudent(enrolledStudent));
    }

    @Test
    public void addStudent() {}

    @Test
    public void removeStudent() {
        Student toRemove = new PersonBuilder().build();
        // student not present
        String notPresentMessage = String.format(Lesson.STUDENT_NOT_ENROLLED, toRemove, defaultLesson);
        assertThrows(IllegalArgumentException.class, notPresentMessage, () -> defaultLesson.removeStudent(toRemove));
        assertEquals(0, defaultLesson.getLessonSize());

        // student present and to remove
        defaultLesson.addStudent(toRemove);
        assertEquals(1, defaultLesson.getLessonSize());
        assertEquals(1, toRemove.getLessonCodes().size());

        defaultLesson.removeStudent(toRemove);
        assertEquals(0, defaultLesson.getLessonSize());
    }

    @Test
    public void removeAll() {}

    @Test
    public void createClone() {
        assertEquals(defaultLesson, defaultLesson.createClone());
    }

    @Test
    public void equals() {
        // this -> true
        assertEquals(defaultLesson, defaultLesson);

        // null -> false
        assertNotEquals(defaultLesson, null);

        // different instance -> false
        assertNotEquals(defaultLesson, 5);

        // instance of but different params -> false
        Lesson differentSubjectLesson = new LessonBuilder().withSubject("English").build();
        assertNotEquals(defaultLesson, differentSubjectLesson);

        Lesson differentGradeLesson = new LessonBuilder().withGrade("S3").build();
        assertNotEquals(defaultLesson, differentGradeLesson);

        Lesson differentLessonTimeLesson = new LessonBuilder()
                .withLessonTime(new LessonTime(DayOfWeek.MONDAY, LocalTime.NOON)).build();
        assertNotEquals(defaultLesson, differentLessonTimeLesson);

        Lesson differentPriceLesson = new LessonBuilder().withPrice(13.9).build();
        assertNotEquals(defaultLesson, differentPriceLesson);

        Lesson lessonWithDifferentStudents = new LessonBuilder().build();
        lessonWithDifferentStudents.addStudent(
            new PersonBuilder().withGrade(defaultLesson.getGrade().value).build()
        );
        assertNotEquals(defaultLesson, lessonWithDifferentStudents);

        // same lesson but different instance
        Lesson sameLesson = LessonBuilder.getDefault();
        assertEquals(defaultLesson, sameLesson);
    }
}
