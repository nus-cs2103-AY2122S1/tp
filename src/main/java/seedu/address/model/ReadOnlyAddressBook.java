package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.residency.ReadOnlyResidencyBook;
import seedu.address.model.residency.Residency;
import seedu.address.model.room.Room;

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
     * Returns an unmodifiable view of the rooms list.
     * This list will not contain any duplicate rooms.
     */
    ObservableList<Room> getRoomList();

    /**
     * Returns an unmodifiable view of the residencies list.
     * This list will not contain any duplicate residencies.
     */
    ObservableList<Residency> getResidencyList();

    /**
     * Returns an unmodifiable view of the residency book.
     * This will not contain any duplicate residencies.
     */
    ReadOnlyResidencyBook getResidencyBook();

    /**
     * Returns an unmodifiable view of the residencies records list.
     * This list will not contain any duplicate residencies.
     */
    ObservableList<Residency> getRecordsList();

    /**
     * Returns an unmodifiable view of the residency records book.
     * This will not contain any duplicate residencies.
     */
    ReadOnlyResidencyBook getRecordsBook();
}
