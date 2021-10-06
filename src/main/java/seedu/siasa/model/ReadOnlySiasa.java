package seedu.siasa.model;

import javafx.collections.ObservableList;
import seedu.siasa.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlySiasa {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
