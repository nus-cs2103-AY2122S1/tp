package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * Unmodifiable view of a HR Manager
 */
public interface ReadOnlyHrManager {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the position list.
     * This list will not contain any duplicate positions.
     */
    ObservableList<Position> getPositionList();

    /**
     * Returns an unmodifiable view of the interview list.
     * This list will not contain any duplicate interviews.
     */
    ObservableList<Interview> getInterviewList();

}
