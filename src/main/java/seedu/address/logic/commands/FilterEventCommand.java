package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.EventDateTimePredicate;

/**
 * Filters the Event list for Events occurring on a specific date and optionally, time.
 */
public class FilterEventCommand extends Command {

    public static final String COMMAND_WORD = "filterEvents";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the event list for events occurring "
            + "on the specified date (in YYYY-MM-DD format) and optionally include time (in 24hr format),"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: "
            + PREFIX_DATE + "DATE"
            + " [" + PREFIX_TIME + "TIME]\n"
            + "Example: " + COMMAND_WORD + " d/2021-09-20\n";

    private final EventDateTimePredicate predicate;

    /**
     * This is a constructor for FilterEventCommand.
     *
     * @param predicate that determines if an Event is to be displayed in the list.
     */
    public FilterEventCommand(EventDateTimePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FilterEventCommand
                && predicate.equals(((FilterEventCommand) other).predicate));
    }
}
