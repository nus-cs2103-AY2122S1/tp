package seedu.tuitione.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.tuitione.model.ReadOnlyTuitione;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.student.Student;

public class SampleDataUtilTest {

    @Test
    public void getSampleStudents() {
        assertEquals(6, (SampleDataUtil.getSampleStudents()).length);
    }

    @Test
    public void getSampleLessons() {
        assertEquals(4, (SampleDataUtil.getSampleLessons()).length);
    }

    @Test
    public void getSampleTuitione() {
        ReadOnlyTuitione readOnlyTuitione = SampleDataUtil.getSampleTuitione();

        // check entity count
        assertEquals(6, (readOnlyTuitione.getStudentList()).size());
        assertEquals(4, (readOnlyTuitione.getLessonList()).size());

        // check entity relations
        Student firstStudent = readOnlyTuitione.getStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student secondStudent = readOnlyTuitione.getStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        Lesson firstLesson = readOnlyTuitione.getLessonList().get(INDEX_FIRST_LESSON.getZeroBased());
        Lesson secondLesson = readOnlyTuitione.getLessonList().get(INDEX_SECOND_LESSON.getZeroBased());
        assertTrue(firstStudent.containsLesson(firstLesson));
        assertTrue(secondStudent.containsLesson(secondLesson));
        assertTrue(firstLesson.containsStudent(firstStudent));
        assertTrue(secondLesson.containsStudent(secondStudent));
    }
}
