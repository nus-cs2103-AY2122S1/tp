package seedu.sourcecontrol.model;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.sourcecontrol.model.student.Assessment;
import seedu.sourcecontrol.model.student.Group;
import seedu.sourcecontrol.model.student.Student;

/**
 * Unmodifiable view of a Source Control.
 */
public interface ReadOnlySourceControl {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns a list of groups.
     */
    List<Group> getGroupList();

    /**
     * Returns a list of assessments.
     */
    List<Assessment> getAssessmentList();
}
