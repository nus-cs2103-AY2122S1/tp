package safeforhall.logic.commands.sort;

import static java.util.Objects.requireNonNull;
import static safeforhall.logic.parser.CliSyntax.PREFIX_ORDER;
import static safeforhall.logic.parser.CliSyntax.PREFIX_SORT;

import java.util.Comparator;

import safeforhall.logic.commands.Command;
import safeforhall.logic.commands.CommandResult;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.Model;
import safeforhall.model.event.Capacity;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventDate;
import safeforhall.model.event.EventName;
import safeforhall.model.event.Venue;

/**
 * Sorts list of events in the application.
 */
public class SortEventCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String PARAMETERS = "by/FIELD o/ORDER";
    public static final String MESSAGE_SUCCESS = "Events have been successfully sorted.";
    public static final String ALLOWED_FIELDS = "FIELD should be one of the following: "
            + EventName.FIELD + ", "
            + EventDate.FIELD + ", "
            + Capacity.FIELD + ", "
            + Venue.FIELD;

    public static final String ASCENDING = "a";
    public static final String DESCENDING = "d";
    public static final String ALLOWED_ORDER = "ORDER should be one of the following: " + ASCENDING + ", " + DESCENDING;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort events by field in "
            + "ascending or descending order. \n"
            + "Parameters: " + PREFIX_SORT + "FIELD " + PREFIX_ORDER + "ORDER \n"
            + ALLOWED_FIELDS + "\n"
            + ALLOWED_ORDER + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SORT + EventName.FIELD + " " + PREFIX_ORDER + DESCENDING;

    private final String field;
    private final String order;


    /**
     * Creates a {@code SortCommand} to sort the list of residents
     *
     * @param field The field to sort by.
     * @param order Ascending or descending order to sort.
     */
    public SortEventCommand(String field, String order) {
        requireNonNull(field);
        requireNonNull(order);
        this.field = field;
        this.order = order;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Comparator<Event> comparator = getComparator();
        model.updateSortedEventList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates a Comparator given the field and order for sort.
     * @return Comparator that sorts by the field and order specified.
     * @throws CommandException If field or order provided is invalid.
     */
    public Comparator<Event> getComparator() throws CommandException {
        Comparator<Event> comparator;
        switch (field) {
        case EventName.FIELD:
            comparator = Comparator.comparing(Event::getEventName);
            break;
        case EventDate.FIELD:
            comparator = Comparator.comparing(Event::getEventDate).thenComparing(Event::getEventTime);
            break;
        case Capacity.FIELD:
            comparator = Comparator.comparing(Event::getCapacity);
            break;
        case Venue.FIELD:
            comparator = Comparator.comparing(Event::getVenue);
            break;
        default:
            throw new CommandException(ALLOWED_FIELDS);
        }

        switch (order) {
        case ASCENDING:
            return comparator;
        case DESCENDING:
            return comparator.reversed();
        default:
            throw new CommandException(ALLOWED_ORDER);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortEventCommand // instanceof handles nulls
                && field.equals(((SortEventCommand) other).field)
                && order.equals(((SortEventCommand) other).order));
    }

}
