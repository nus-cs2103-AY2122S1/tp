package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.Model;

/**
 * Sorts the tuition class list.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String SHORTCUT = "s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the tuition class list.\n"
            + "Parameters: [" + PREFIX_SORT_ORDER + "ORDER] \n"
            + "ORDER includes 'o/time', 'o/asc' (for ascending alphabetical order), "
            + "and 'o/desc' (for descending alphabetical order)\n"
            + "Example1: " + COMMAND_WORD + " " + PREFIX_SORT_ORDER + "asc\n"
            + "Example2: " + COMMAND_WORD;
    public static final String MESSAGE_SORTED = "The tuition class list is now sorted by %1$s order";
    private static final Logger logger = LogsCenter.getLogger(SortCommand.class);
    private SortCommandParser.Order order;

    /**
     * Constructs a SortCommand with a sort order.
     * @param order the order to sort.
     */
    public SortCommand(SortCommandParser.Order order) {
        logger.info("Start sorting by " + getSuccessMessage(order.toString()) + " order");
        this.order = order;
    }

    /**
     * Sorts the tuition class list by time or alphabetical order.
     * @param model {@code Model} which the command should operate on.
     * @return a CommandResult of the sort command.
     * @throws CommandException default path throws a CommandException if
     * the sorting order is not defined by the software
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        SortCommandParser.Order resultOrder;
        assert this.order != null : "Order to sort should not be null";
        switch (this.order) {
        case ASCENDING:
            resultOrder = SortCommandParser.Order.ASCENDING;
            sort(SortCommandParser.Order.ASCENDING, model);
            break;
        case DESCENDING:
            resultOrder = SortCommandParser.Order.DESCENDING;
            sort(SortCommandParser.Order.DESCENDING, model);
            break;
        case TIME:
            resultOrder = SortCommandParser.Order.TIME;
            sort(SortCommandParser.Order.TIME, model);
            break;
        default:
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        return new CommandResult(String.format(MESSAGE_SORTED, getSuccessMessage(resultOrder.toString())));
    }

    private void sort(SortCommandParser.Order order, Model model) {
        model.sort(order);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
    }

    private String getSuccessMessage(String order) {
        switch (order) {
        case "time":
            return "time";
        case "asc":
            return "ascending";
        case "desc":
            return "descending";
        default:
            //This line should not be reached
            return order;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SortCommand that = (SortCommand) o;

        return order == that.order;
    }
}
