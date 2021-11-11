package seedu.address.model.residency;

import java.util.Map;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.room.Room;

/**
 * Unmodifiable view of a residency book
 */
public interface ReadOnlyResidencyBook {

    /**
     * Returns an unmodifiable view of the person to residency map.
     * This map will not contain any duplicate registrations.
     */
    Map<Person, Residency> getGuestMap();

    /**
     * Returns an unmodifiable view of the room to residency map.
     * This map will not contain any duplicate registrations.
     */
    Map<Room, Residency> getRoomMap();

    ObservableList<Residency> asUnmodifiableObservableList();
}
