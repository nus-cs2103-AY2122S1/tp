package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_ALICE;
import static seedu.address.testutil.TypicalCustomers.getTypicalAddressBookCustomers;
import static seedu.address.testutil.TypicalEmployees.ALICE_EMPLOYEE;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBookEmployees;
import static seedu.address.testutil.TypicalReservation.ALICE_RESERVATION;
import static seedu.address.testutil.TypicalSuppliers.AMY;
import static seedu.address.testutil.TypicalSuppliers.getTypicalAddressBookSuppliers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.exceptions.DuplicateCustomerException;
import seedu.address.model.person.exceptions.DuplicateEmployeeException;
import seedu.address.model.person.exceptions.DuplicateSupplierException;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.table.Table;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.EmployeeBuilder;
import seedu.address.testutil.SupplierBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getEmployeeList());
        assertEquals(Collections.emptyList(), addressBook.getCustomerList());
        assertEquals(Collections.emptyList(), addressBook.getSupplierList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBookCustomers();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesDataEmployees() {
        AddressBook newData = getTypicalAddressBookEmployees();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesDataSuppliers() {
        AddressBook newData = getTypicalAddressBookSuppliers();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateCustomers_throwsDuplicateCustomerException() {
        // Two customers with the same identity fields
        Customer editedAlice =
                new CustomerBuilder(CUSTOMER_ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Customer> newCustomers = Arrays.asList(CUSTOMER_ALICE, editedAlice);
        List<Employee> emptyList = new ArrayList<>();
        List<Supplier> emptySuppliersList = new ArrayList<>();
        AddressBookStub newData = new AddressBookStub(newCustomers, emptyList, emptySuppliersList);

        assertThrows(DuplicateCustomerException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateEmployees_throwsDuplicateEmployeeException() {
        // Two persons with the same identity fields
        Employee editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        List<Employee> newEmployees = Arrays.asList(ALICE_EMPLOYEE, editedAlice);
        List<Customer> emptyCustList = new ArrayList<>();
        List<Supplier> emptySuppliersList = new ArrayList<>();
        AddressBookStub newData = new AddressBookStub(emptyCustList, newEmployees, emptySuppliersList);

        assertThrows(DuplicateEmployeeException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateSuppliers_throwsDuplicateEmployeeException() {
        // Two persons with the same identity fields
        Supplier editedAmy = new SupplierBuilder(AMY).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        List<Supplier> newSuppliers = Arrays.asList(AMY, editedAmy);
        List<Customer> emptyCustList = new ArrayList<>();
        List<Employee> emptyEmployeeList = new ArrayList<>();
        AddressBookStub newData = new AddressBookStub(emptyCustList, emptyEmployeeList, newSuppliers);

        assertThrows(DuplicateSupplierException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasCustomer_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasCustomer(null));
    }

    @Test
    public void hasEmployee_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasEmployee(null));
    }

    @Test
    public void hasReservation_nullReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasReservation(null));
    }

    @Test
    public void hasSupplier_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasSupplier(null));
    }

    @Test
    public void hasCustomer_customerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasCustomer(CUSTOMER_ALICE));
    }

    @Test
    public void hasEmployee_employeeNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasEmployee(ALICE_EMPLOYEE));
    }

    @Test
    public void hasSupplier_supplierNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasSupplier(AMY));
    }

    @Test
    public void hasReservation_reservationNOtInAddressBook_returnFalse() {
        assertFalse(addressBook.hasReservation(ALICE_RESERVATION));
    }

    @Test
    public void hasCustomer_customerInAddressBook_returnsTrue() {
        addressBook.addCustomer(CUSTOMER_ALICE);
        assertTrue(addressBook.hasCustomer(CUSTOMER_ALICE));
    }

    @Test
    public void hasEmployee_inAddressBook_returnsTrue() {
        addressBook.addEmployee(ALICE_EMPLOYEE);
        assertTrue(addressBook.hasEmployee(ALICE_EMPLOYEE));
    }

    @Test
    public void hasSupplier_inAddressBook_returnsTrue() {
        addressBook.addSupplier(AMY);
        assertTrue(addressBook.hasSupplier(AMY));
    }

    @Test
    public void hasReservation_reservationInAddressBook_returnsTrue() {
        addressBook.addReservation(ALICE_RESERVATION);
        assertTrue(addressBook.hasReservation(ALICE_RESERVATION));
    }

    @Test
    public void hasCustomer_customerWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addCustomer(CUSTOMER_ALICE);
        Customer editedAlice =
                new CustomerBuilder(CUSTOMER_ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasCustomer(editedAlice));
    }

    @Test
    public void hasEmployee_employeeWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addEmployee(ALICE_EMPLOYEE);
        Employee editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasEmployee(editedAlice));
    }

    @Test
    public void hasSupplier_supplierWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addSupplier(AMY);
        Supplier editedAmy = new SupplierBuilder(AMY)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasSupplier(editedAmy));
    }

    @Test
    public void getCustomerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getCustomerList().remove(0));
    }

    @Test
    public void getEmployeeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getEmployeeList().remove(0));
    }

    @Test
    public void getSupplierList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getSupplierList().remove(0));
    }

    @Test
    public void getReservationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getReservationList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {

        private final ObservableList<Customer> customers = FXCollections.observableArrayList();
        private final ObservableList<Employee> employees = FXCollections.observableArrayList();
        private final ObservableList<Supplier> suppliers = FXCollections.observableArrayList();

        AddressBookStub(Collection<Customer> customers, Collection<Employee> employees,
                        Collection<Supplier> suppliers) {
            this.employees.setAll(employees);
            this.customers.setAll(customers);
            this.suppliers.setAll(suppliers);
        }

        @Override
        public ObservableList<Customer> getCustomerList() {
            return customers;
        }

        @Override
        public ObservableList<Employee> getEmployeeList() {
            return employees;
        }

        @Override
        public ObservableList<Supplier> getSupplierList() {
            return suppliers;
        }

        @Override
        public ObservableList<Table> getTableList() {
            return null;
        }

        @Override
        public ObservableList<Reservation> getReservationList() {
            return null;
        }
    }

}
