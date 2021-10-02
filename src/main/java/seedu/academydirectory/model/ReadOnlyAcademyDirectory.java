package seedu.academydirectory.model;

import javafx.collections.ObservableList;
import seedu.academydirectory.model.person.Person;

/**
 * Unmodifiable view of an academy directory
 */
public interface ReadOnlyAcademyDirectory {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
