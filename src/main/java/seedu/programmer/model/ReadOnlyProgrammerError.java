package seedu.programmer.model;

import javafx.collections.ObservableList;
import seedu.programmer.model.person.Person;

/**
 * Unmodifiable view of a ProgrammerError
 */
public interface ReadOnlyProgrammerError {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
