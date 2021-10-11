package tutoraid.model;

import javafx.collections.ObservableList;
import tutoraid.model.student.Student;

/**
 * Unmodifiable view of a student book
 */
public interface ReadOnlyStudentBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

}
