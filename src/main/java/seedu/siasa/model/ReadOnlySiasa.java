package seedu.siasa.model;

import javafx.collections.ObservableList;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.Policy;

/**
 * Unmodifiable view of an SIASA
 */
public interface ReadOnlySiasa {

    /**
     * Returns an unmodifiable view of the contacts list.
     * This list will not contain any duplicate contacts.
     */
    ObservableList<Contact> getContactList();

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Policy> getPolicyList();

}
