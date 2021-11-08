package seedu.address.testutil;

import seedu.address.model.Rhrh;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.reservation.Reservation;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Rhrh ab = new RhrhBuilder().withPerson("John", "Doe").build();}
 */
public class RhrhBuilder {

    private Rhrh rhrh;

    public RhrhBuilder() {
        rhrh = new Rhrh();
    }

    public RhrhBuilder(Rhrh rhrh) {
        this.rhrh = rhrh;
    }

    /**
     * Adds a new {@code Customer} to the {@code Rhrh} that we are building.
     */
    public RhrhBuilder withCustomer(Customer customer) {
        rhrh.addCustomer(customer);
        return this;
    }
    /**
     * Adds a new {@code Employee} to the {@code Rhrh} that we are building.
     */
    public RhrhBuilder withEmployee(Employee employee) {
        rhrh.addEmployee(employee);
        return this;
    }

    /**
     * Adds a new {@code Reservation} to the {@code Rhrh} that we are building
     */
    public RhrhBuilder withReservation(Reservation reservation) {
        rhrh.addReservation(reservation);
        return this;
    }

    /**
     * Adds a new {@code Supplier} to the {@code Rhrh} that we are building.
     */
    public RhrhBuilder withSupplier(Supplier supplier) {
        rhrh.addSupplier(supplier);
        return this;
    }

    public Rhrh build() {
        return rhrh;
    }
}
