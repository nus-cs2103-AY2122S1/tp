package seedu.fast.model;

import javafx.collections.ObservableList;
import seedu.fast.model.person.Person;

/**
 * Unmodifiable view of FAST
 */
public interface ReadOnlyFast {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
