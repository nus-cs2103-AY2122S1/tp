package seedu.tuitione.model;

import javafx.collections.ObservableList;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.student.Student;

/**
 * Unmodifiable view of an tuitione book
 */
public interface ReadOnlyTuitione {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns list of lessons.
     */
    ObservableList<Lesson> getLessonList();
}
