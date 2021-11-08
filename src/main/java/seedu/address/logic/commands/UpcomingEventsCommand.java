package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.event.EventContainsCurrentTimePredicate;

/**
 * Lists all upcoming events in Schedule of the current date.
 */
public class UpcomingEventsCommand extends Command {

    public static final String COMMAND_WORD = "upcoming_events";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all upcoming events for the day\n"
            + "Example: " + COMMAND_WORD;

    private final EventContainsCurrentTimePredicate timePredicate;

    public UpcomingEventsCommand() {
        this.timePredicate = new EventContainsCurrentTimePredicate();
    }

    public UpcomingEventsCommand(EventContainsCurrentTimePredicate timePredicate) {
        this.timePredicate = timePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(timePredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_UPCOMING_EVENT_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpcomingEventsCommand // instanceof handles nulls
                && timePredicate.equals(((UpcomingEventsCommand) other).timePredicate)); // state check
    }
}
