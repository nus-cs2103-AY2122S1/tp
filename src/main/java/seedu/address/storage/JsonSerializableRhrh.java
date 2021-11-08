package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyRhrh;
import seedu.address.model.Rhrh;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.table.Table;

/**
 * An Immutable Rhrh that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableRhrh {

    public static final String MESSAGE_DUPLICATE_CUSTOMER = "Customers list contains duplicate customer(s).";
    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "Employees list contains duplicate employee(s).";
    public static final String MESSAGE_DUPLICATE_SUPPLIER = "Suppliers list contains duplicate supplier(s).";
    public static final String MESSAGE_RESERVATION_PHONE_DOES_NOT_EXIST =
            "Phone number of reservation does not exist in Customer database";

    private final List<JsonAdaptedCustomer> customers = new ArrayList<>();
    private final List<JsonAdaptedEmployee> employees = new ArrayList<>();
    private final List<JsonAdaptedSupplier> suppliers = new ArrayList<>();
    private final List<JsonAdaptedReservation> reservations = new ArrayList<>();
    private final List<JsonAdaptedTable> tables = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRhrh} with the given persons and employees.
     */
    @JsonCreator
    public JsonSerializableRhrh(@JsonProperty("customers") List<JsonAdaptedCustomer> customers,
        @JsonProperty("employees") List<JsonAdaptedEmployee> employees,
        @JsonProperty("suppliers") List<JsonAdaptedSupplier> suppliers,
        @JsonProperty("reservations") List<JsonAdaptedReservation> reservations,
        @JsonProperty("tables") List<JsonAdaptedTable> tables) {
        this.customers.addAll(customers);
        this.employees.addAll(employees);
        this.suppliers.addAll(suppliers);
        this.reservations.addAll(reservations);
        this.tables.addAll(tables);
    }

    /**
     * Converts a given {@code ReadOnlyRhrh} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRhrh}.
     */
    public JsonSerializableRhrh(ReadOnlyRhrh source) {
        customers.addAll(source.getCustomerList().stream().map(JsonAdaptedCustomer::new).collect(Collectors.toList()));
        employees.addAll(source.getEmployeeList().stream().map(JsonAdaptedEmployee::new).collect(Collectors.toList()));
        suppliers.addAll(source.getSupplierList().stream().map(JsonAdaptedSupplier::new).collect(Collectors.toList()));
        reservations.addAll(
                source.getReservationList().stream().map(JsonAdaptedReservation::new).collect(Collectors.toList()));
        tables.addAll(source.getTableList().stream().map(JsonAdaptedTable::new).collect(Collectors.toList()));
    }

    /**
     * Converts this RHRH into the model's {@code Rhrh} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Rhrh toModelType() throws IllegalValueException {
        Rhrh rhrh = new Rhrh();

        for (JsonAdaptedCustomer jsonAdaptedCustomer : customers) {
            Customer customer = jsonAdaptedCustomer.toModelType();
            if (rhrh.hasCustomer(customer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CUSTOMER);
            }
            rhrh.addCustomer(customer);
        }
        for (JsonAdaptedEmployee jsonAdaptedEmployee : employees) {
            Employee employee = jsonAdaptedEmployee.toModelType();
            if (rhrh.hasEmployee(employee)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EMPLOYEE);
            }
            rhrh.addEmployee(employee);
        }
        for (JsonAdaptedSupplier jsonAdaptedSupplier : suppliers) {
            Supplier supplier = jsonAdaptedSupplier.toModelType();
            if (rhrh.hasSupplier(supplier)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SUPPLIER);
            }
            rhrh.addSupplier(supplier);
        }
        for (JsonAdaptedReservation jsonAdaptedReservation : reservations) {
            Reservation reservation = jsonAdaptedReservation.toModelType();
            if (!rhrh.hasCustomerWithPhone(reservation.getPhone())) {
                throw new IllegalValueException(MESSAGE_RESERVATION_PHONE_DOES_NOT_EXIST);
            }
            rhrh.addReservation(reservation);
        }
        for (JsonAdaptedTable jsonAdaptedTable : tables) {
            Table table = jsonAdaptedTable.toModelType();
            rhrh.addTable(table);
        }
        return rhrh;
    }
}
