package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns the current client counter
     */
    String getClientCounter();

    /**
     * Increments the clientCounter of the address book by 1 {@code clientCounter}.
     */
    void incrementClientCounter();

    void setClientCounter(String s);
}
