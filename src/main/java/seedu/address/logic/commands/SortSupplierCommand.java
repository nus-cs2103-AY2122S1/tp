package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.util.CommandUtil;
import seedu.address.model.Model;
import seedu.address.model.person.supplier.Supplier;

/**
 * Sorts the supplier list to RHRH according to a comparator.
 */
public class SortSupplierCommand extends Command {

    public static final String COMMAND_WORD = "sorts";

    public static final String MESSAGE_USAGE = CommandUtil.formatCommandWord(COMMAND_WORD)
            + ": Sorts the supplier list by a sorting type and sorting order. "
            + "Parameters: "
            + PREFIX_SORT_BY + "name, address, email, phone, supply type or delivery details "
            + PREFIX_SORT_ORDER + "ascending or descending...\n"
            + "Example: " + CommandUtil.formatCommandWord(COMMAND_WORD) + " "
            + PREFIX_SORT_BY + "dd "
            + PREFIX_SORT_ORDER + "a ";

    public static final String MESSAGE_SUCCESS = "Supplier list sorted by %1$s in %2$s order";
    public static final String MESSAGE_EMPTY_FILTERED_LIST = "Supplier list is currently empty!";

    private final Comparator<Supplier> comparator;
    private final String sortBy;
    private final String sortingOrder;

    /**
     * Creates a SortSupplierCommand object.
     */
    public SortSupplierCommand(Comparator<Supplier> comparator, String sortBy, String sortingOrder) {
        requireNonNull(comparator);
        requireNonNull(sortBy);
        requireNonNull(sortingOrder);
        this.comparator = comparator;
        this.sortBy = sortBy;
        this.sortingOrder = sortingOrder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getFilteredSupplierList().size() == 0) {
            throw new CommandException(MESSAGE_EMPTY_FILTERED_LIST);
        }
        model.getSortableSupplierList().sort(comparator);
        model.setSupplierComparator(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, sortBy, sortingOrder),
                false, false, false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SortSupplierCommand)) {
            return false;
        }

        SortSupplierCommand otherCommand = (SortSupplierCommand) other;
        return otherCommand.sortingOrder.equals(sortingOrder)
                && otherCommand.sortBy.equals(sortBy);
    }

}
