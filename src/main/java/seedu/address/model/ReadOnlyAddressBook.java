package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialclass.TutorialClass;
//import seedu.address.model.tutorialclass.TutorialClass;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the tutorial classes list.
     * This list will not contain any duplicate tutorial classes.
     */
    ObservableList<TutorialClass> getTutorialClassList();

}
