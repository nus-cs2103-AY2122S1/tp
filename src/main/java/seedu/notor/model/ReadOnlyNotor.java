package seedu.notor.model;

import javafx.collections.ObservableList;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Person;

/**
 * Unmodifiable view of Notor
 */
public interface ReadOnlyNotor extends Notable {

    /**
     * Returns an unmodifiable view of the Person list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the Person archive list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonArchiveList();

    /**
     * Returns an unmodifiable view of the SuperGroup list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<SuperGroup> getSuperGroups();
}
