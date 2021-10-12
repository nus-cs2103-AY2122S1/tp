package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.ItemCountComparator;
import seedu.address.model.item.ItemNameComparator;

/**
 * Sorts the items in inventory in a given order.
 */
public class SortCommand extends Command {

    public enum SortOrder { BY_NAME, BY_COUNT };

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the currently displayed items\n"
            + "Flags\n"
            + "\t" + PREFIX_NAME + " : sort by name\n"
            + "\t" + PREFIX_COUNT + " : sort by count\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COUNT;

    public static final String MESSAGE_SUCCESS = "Listed items sorted by %s";

    private SortOrder order;

    /**
     * Creates a SortCommand which sorts by the specified {@code SortOrder}
     */
    public SortCommand(SortOrder order) {
        requireNonNull(order);
        this.order = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (order) {
        case BY_NAME:
            model.sortItems(new ItemNameComparator());
            return new CommandResult(String.format(MESSAGE_SUCCESS, "name"));
        case BY_COUNT:
            model.sortItems(new ItemCountComparator());
            return new CommandResult(String.format(MESSAGE_SUCCESS, "count"));
        default:
            assert(false); // Should be unreachable, switch case must be exhaustive
            return new CommandResult("");
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && order == ((SortCommand) other).order); // state check
    }
}
