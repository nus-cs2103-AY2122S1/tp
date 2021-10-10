package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Shows the details of an existing Event in the Managera Event list.
 */
public class ShowEventDetailsCommand extends Command {

    public static final String COMMAND_WORD = "showDetails";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the details of the Event matching the given name.\n"
            + "Parameters: EVENT_NAME \n"
            + "Example: " + COMMAND_WORD + " CS2103T Finals ";

    private final Predicate<Event> eventName;

    /**
     * Creates a ShowEventDetailsCommand for the Event with the specified {@code eventName}
     */
    public ShowEventDetailsCommand(Predicate<Event> eventName) {
        requireNonNull(eventName);
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        FilteredList<Event> filteredEventList = model.getFilteredEventList().filtered(eventName);

        if (filteredEventList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_EVENT_NOT_FOUND);
        }

        Event desiredEvent = filteredEventList.get(0);
        String displayedMessage = String.format("Event Name: %s\nEvent Date: %s\nEvent Time: %s\nCompletion Status: %s",
                desiredEvent.getName(),
                desiredEvent.getDate(),
                desiredEvent.getTime().toString().equals("") ? "N/A" : desiredEvent.getTime(),
                desiredEvent.getIsDone() ? "Completed" : "Uncompleted");

        return new CommandResult(displayedMessage);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowEventDetailsCommand // instanceof handles nulls
                && eventName.equals(((ShowEventDetailsCommand) other).eventName)); // state check
    }
}
