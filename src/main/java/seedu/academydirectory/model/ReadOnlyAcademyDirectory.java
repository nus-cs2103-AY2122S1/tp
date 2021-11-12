package seedu.academydirectory.model;

import javafx.collections.ObservableList;
import seedu.academydirectory.model.student.Student;

/**
 * Unmodifiable view of an academy directory
 */
public interface ReadOnlyAcademyDirectory {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

}
