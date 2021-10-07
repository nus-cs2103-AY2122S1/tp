package seedu.siasa.model;

import javafx.collections.ObservableList;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Policy;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlySiasa {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Policy> getPolicyList();

}
