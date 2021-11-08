package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.CUSTOMER_ALICE;
import static seedu.address.testutil.TypicalCustomers.getTypicalRhrhCustomers;
import static seedu.address.testutil.TypicalEmployees.ALICE_EMPLOYEE;
import static seedu.address.testutil.TypicalEmployees.getTypicalRhrhEmployees;
import static seedu.address.testutil.TypicalReservation.ALICE_RESERVATION;
import static seedu.address.testutil.TypicalSuppliers.AMY;
import static seedu.address.testutil.TypicalSuppliers.getTypicalRhrhSuppliers;

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

public class RhrhTest {

    private final Rhrh rhrh = new Rhrh();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), rhrh.getEmployeeList());
        assertEquals(Collections.emptyList(), rhrh.getCustomerList());
        assertEquals(Collections.emptyList(), rhrh.getSupplierList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> rhrh.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyRhrh_replacesData() {
        Rhrh newData = getTypicalRhrhCustomers();
        rhrh.resetData(newData);
        assertEquals(newData, rhrh);
    }

    @Test
    public void resetData_withValidReadOnlyRhrh_replacesDataEmployees() {
        Rhrh newData = getTypicalRhrhEmployees();
        rhrh.resetData(newData);
        assertEquals(newData, rhrh);
    }

    @Test
    public void resetData_withValidReadOnlyRhrh_replacesDataSuppliers() {
        Rhrh newData = getTypicalRhrhSuppliers();
        rhrh.resetData(newData);
        assertEquals(newData, rhrh);
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
        RhrhStub newData = new RhrhStub(newCustomers, emptyList, emptySuppliersList);

        assertThrows(DuplicateCustomerException.class, () -> rhrh.resetData(newData));
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
        RhrhStub newData = new RhrhStub(emptyCustList, newEmployees, emptySuppliersList);

        assertThrows(DuplicateEmployeeException.class, () -> rhrh.resetData(newData));
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
        RhrhStub newData = new RhrhStub(emptyCustList, emptyEmployeeList, newSuppliers);

        assertThrows(DuplicateSupplierException.class, () -> rhrh.resetData(newData));
    }

    @Test
    public void hasCustomer_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> rhrh.hasCustomer(null));
    }

    @Test
    public void hasEmployee_nullEmployee_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> rhrh.hasEmployee(null));
    }

    @Test
    public void hasReservation_nullReservation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> rhrh.hasReservation(null));
    }

    @Test
    public void hasSupplier_nullSupplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> rhrh.hasSupplier(null));
    }

    @Test
    public void hasCustomer_customerNotInRhrh_returnsFalse() {
        assertFalse(rhrh.hasCustomer(CUSTOMER_ALICE));
    }

    @Test
    public void hasEmployee_employeeNotInRhrh_returnsFalse() {
        assertFalse(rhrh.hasEmployee(ALICE_EMPLOYEE));
    }

    @Test
    public void hasSupplier_supplierNotInRhrh_returnsFalse() {
        assertFalse(rhrh.hasSupplier(AMY));
    }

    @Test
    public void hasReservation_reservationNOtInRhrh_returnFalse() {
        assertFalse(rhrh.hasReservation(ALICE_RESERVATION));
    }

    @Test
    public void hasCustomer_customerInRhrh_returnsTrue() {
        rhrh.addCustomer(CUSTOMER_ALICE);
        assertTrue(rhrh.hasCustomer(CUSTOMER_ALICE));
    }

    @Test
    public void hasEmployee_inRhrh_returnsTrue() {
        rhrh.addEmployee(ALICE_EMPLOYEE);
        assertTrue(rhrh.hasEmployee(ALICE_EMPLOYEE));
    }

    @Test
    public void hasSupplier_inRhrh_returnsTrue() {
        rhrh.addSupplier(AMY);
        assertTrue(rhrh.hasSupplier(AMY));
    }

    @Test
    public void hasReservation_reservationInRhrh_returnsTrue() {
        rhrh.addReservation(ALICE_RESERVATION);
        assertTrue(rhrh.hasReservation(ALICE_RESERVATION));
    }

    @Test
    public void hasCustomer_customerWithSameIdentityFieldsInRhrh_returnsTrue() {
        rhrh.addCustomer(CUSTOMER_ALICE);
        Customer editedAlice =
                new CustomerBuilder(CUSTOMER_ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(rhrh.hasCustomer(editedAlice));
    }

    @Test
    public void hasEmployee_employeeWithSameIdentityFieldsInRhrh_returnsTrue() {
        rhrh.addEmployee(ALICE_EMPLOYEE);
        Employee editedAlice = new EmployeeBuilder(ALICE_EMPLOYEE)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(rhrh.hasEmployee(editedAlice));
    }

    @Test
    public void hasSupplier_supplierWithSameIdentityFieldsInRhrh_returnsTrue() {
        rhrh.addSupplier(AMY);
        Supplier editedAmy = new SupplierBuilder(AMY)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(rhrh.hasSupplier(editedAmy));
    }

    @Test
    public void getCustomerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> rhrh.getCustomerList().remove(0));
    }

    @Test
    public void getEmployeeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> rhrh.getEmployeeList().remove(0));
    }

    @Test
    public void getSupplierList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> rhrh.getSupplierList().remove(0));
    }

    @Test
    public void getReservationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> rhrh.getReservationList().remove(0));
    }

    /**
     * A stub ReadOnlyRhrh whose persons list can violate interface constraints.
     */
    private static class RhrhStub implements ReadOnlyRhrh {

        private final ObservableList<Customer> customers = FXCollections.observableArrayList();
        private final ObservableList<Employee> employees = FXCollections.observableArrayList();
        private final ObservableList<Supplier> suppliers = FXCollections.observableArrayList();

        RhrhStub(Collection<Customer> customers, Collection<Employee> employees,
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
