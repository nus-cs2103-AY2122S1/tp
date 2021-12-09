package seedu.times.model;

import javafx.collections.ObservableList;
import seedu.times.model.person.Student;
import seedu.times.model.tuitionclass.TuitionClass;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTimesTable {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the class list.
     * This list will not contain any duplicate classes.
     * @return
     */
    ObservableList<TuitionClass> getTuitionClassList();

}
