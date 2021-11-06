package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyRhrh;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.reservation.Reservation;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the Rhrh.
     *
     * @see seedu.address.model.Model#getRhrh()
     */
    ReadOnlyRhrh getRhrh();

    /** Returns an unmodifiable view of the filtered list of customers */
    ObservableList<Customer> getFilteredCustomerList();
    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Employee> getFilteredEmployeeList();

    /** Returns an unmodifiable view of the filtered list of suppliers */
    ObservableList<Supplier> getFilteredSupplierList();

    /** Returns an unmodifiable view of the filtered list of reservations */
    ObservableList<Reservation> getFilteredReservations();

    /**
     * Returns the user prefs' RHRH file path.
     */
    Path getRhrhFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
