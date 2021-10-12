package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.reservation.Reservation;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    ObservableList<Reservation> getReservationList();

    /**
     * Returns an unmodifiable view of the suppliers list.
     * This list will not contain any duplicate suppliers.
     */
    ObservableList<Supplier> getSupplierList();
}
