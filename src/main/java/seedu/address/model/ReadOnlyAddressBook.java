package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.employee.Employee;
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
     * Returns an unmodifiable view of the customers list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Customer> getCustomerList();
  
    /**
     * Returns an unmodifiable view of the employees list.
     * This list will not contain any duplicate employee.
     */
    ObservableList<Employee> getEmployeeList();

    /**
     * Returns an unmodifiable view of the suppliers list.
     * This list will not contain any duplicate suppliers.
     */
    ObservableList<Supplier> getSupplierList();
}
