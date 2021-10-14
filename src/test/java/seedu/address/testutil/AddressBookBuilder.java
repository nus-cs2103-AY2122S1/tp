package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.reservation.Reservation;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }
    /**
     * Adds a new {@code Customer} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withCustomer(Customer customer) {
        addressBook.addCustomer(customer);
        return this;
    }
    /**
     * Adds a new {@code Employee} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withEmployee(Employee employee) {
        addressBook.addEmployee(employee);
        return this;
    }

    /**
     * Adds a new {@code Reservation} to the {@code AddressBook} that we are building
     */
    public AddressBookBuilder withReservation(Reservation reservation) {
        addressBook.addReservation(reservation);
        return this;
    }

    /**
     * Adds a new {@code Supplier} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withSupplier(Supplier supplier) {
        addressBook.addSupplier(supplier);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
