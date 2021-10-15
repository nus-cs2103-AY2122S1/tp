package seedu.notor.model;

import javafx.collections.ObservableList;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Person;

/**
 * Unmodifiable view of Notor
 */
public interface ReadOnlyNotor {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    ObservableList<SuperGroup> getSuperGroups();
}
