package seedu.edrecord.model;

import javafx.collections.ObservableList;
import seedu.edrecord.model.person.Person;

/**
 * Unmodifiable view of an edrecord
 */
public interface ReadOnlyEdRecord {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
