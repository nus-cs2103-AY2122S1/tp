package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

import java.util.Comparator;

import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.model.Model;
import seedu.address.model.person.customer.Customer;

/**
 * Sorts the customer list to the address book according to a comparator.
 */
public class SortCustomerCommand extends Command {

    public static final String COMMAND_WORD = "sortc";

    public static final String MESSAGE_USAGE = CommandUtil.formatCommandWord(COMMAND_WORD)
            + ": Sorts the customer list by a sorting type and sorting order. "
            + "Parameters: "
            + PREFIX_SORT_BY + "name, address, email, phone, or loyalty points "
            + PREFIX_SORT_ORDER + "ascending or descending...\n"
            + "Example: " + CommandUtil.formatCommandWord(COMMAND_WORD) + " "
            + PREFIX_SORT_BY + "lp "
            + PREFIX_SORT_ORDER + "a ";

    public static final String MESSAGE_SUCCESS = "Customer list sorted by %1$s in %2$s order";

    private final Comparator<Customer> comparator;
    private final String sortBy;
    private final String sortingOrder;

    /**
     * Creates a SortCustomerCommand object.
     */
    public SortCustomerCommand(Comparator<Customer> comparator, String sortBy, String sortingOrder) {
        this.comparator = comparator;
        this.sortBy = sortBy;
        this.sortingOrder = sortingOrder;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.getSortableCustomerList().sort(comparator);
        model.setCustomerComparator(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, sortBy, sortingOrder),
                false, false, true, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SortSupplierCommand)) {
            return false;
        }

        SortCustomerCommand otherCommand = (SortCustomerCommand) other;
        return otherCommand.sortingOrder.equals(sortingOrder)
                && otherCommand.sortBy.equals(sortBy)
                && otherCommand.comparator.equals(comparator);
    }
}
