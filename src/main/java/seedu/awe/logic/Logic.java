package seedu.awe.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.awe.commons.core.GuiSettings;
import seedu.awe.logic.commands.CommandResult;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.logic.parser.exceptions.ParseException;
import seedu.awe.model.ReadOnlyAwe;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.group.Group;
import seedu.awe.model.payment.Payment;
import seedu.awe.model.person.Person;
import seedu.awe.model.transactionsummary.TransactionSummary;

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
     * Returns the Awe.
     *
     * @see seedu.awe.model.Model#getAwe()
     */
    ReadOnlyAwe getAwe();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of groups */
    ObservableList<Group> getFilteredGroupList();

    /** Returns the list of expenses. */
    ObservableList<Expense> getExpenses();

    /** Returns the transaction summary as a list. */
    ObservableList<TransactionSummary> getTransactionSummary();

    /** Returns the payments to make as a list. */
    ObservableList<Payment> getPayments();

    /**
     * Returns the user prefs' awe book file path.
     */
    Path getAweFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
