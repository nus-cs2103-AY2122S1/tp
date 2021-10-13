package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.ClientId;
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
     * Sets the client counter stored with the new client counter
     */
    void setClientCounter(String clientCounter);

    /**
     * Sets the target {@code target} in the addressBook to {@code editperson}.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns the person with {@code clientId}
     */
    Person getPerson(ClientId clientId);

    /**
     * Increments the clientCounter of the address book by 1 {@code clientCounter}.
     *
     */
    void incrementClientCounter();

}
