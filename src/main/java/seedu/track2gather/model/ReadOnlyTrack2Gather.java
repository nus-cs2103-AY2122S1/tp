package seedu.track2gather.model;

import javafx.collections.ObservableList;
import seedu.track2gather.model.person.Person;

/**
 * Unmodifiable view of Track2Gather
 */
public interface ReadOnlyTrack2Gather {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
