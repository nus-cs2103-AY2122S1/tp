package seedu.address.model;

import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.address.model.group.SubGroup;
import seedu.address.model.group.SuperGroup;
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

    HashMap<String, SuperGroup> getSuperGroups();

    HashMap<String, SubGroup> getSubGroups();
}
