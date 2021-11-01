package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDERING;

import seedu.address.model.Model;
import seedu.address.model.sort.SortDescriptor;

/**
 * Sorts orders in the address book based in either ascending or descending order based on the specified field.
 */
public class SortOrdersCommand extends Command {

    public static final String COMMAND_WORD = "sortorders";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all orders by a chosen field "
            + "in either ascending or descending order and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_SORT_FIELD + "FIELD "
            + PREFIX_SORT_ORDERING + "[ORDERING] "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT_FIELD + "amount "
            + PREFIX_SORT_ORDERING + "desc";

    public static final String MESSAGE_SUCCESS = "Sorted all orders";

    private final SortDescriptor sortDescriptor;

    /**
     * Creates an SortOrdersCommand to sort the orders by a specified {@code SortDescriptor}.
     */
    public SortOrdersCommand(SortDescriptor sortDescriptor) {
        this.sortDescriptor = requireNonNull(sortDescriptor);
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortOrderList(sortDescriptor);
        String successMessage = sortDescriptor.generateSuccessMessage();
        return new CommandResult(successMessage, CommandResult.DisplayState.ORDER);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortOrdersCommand)) {
            return false;
        }

        // state check
        SortOrdersCommand e = (SortOrdersCommand) other;

        return sortDescriptor.equals(e.sortDescriptor);
    }
}

