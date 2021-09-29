package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Shows a list of participants of an existing Event in the Managera Event list.
 */
public class ShowParticipantsCommand extends Command {

    public static final String COMMAND_WORD = "showParticipants";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays a list of participants of the Event matching the given name.\n"
            + "Parameters: EVENT_NAME \n"
            + "Example: " + COMMAND_WORD + " CS2103T Finals ";

    private final Predicate<Event> eventName;

    /**
     * Creates an ShowParticipantsCommand to find Event with the specified {@code eventName}
     */
    public ShowParticipantsCommand(Predicate<Event> eventName) {
        requireNonNull(eventName);
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        FilteredList<Event> filteredEventList = model.getEventList().filtered(eventName);

        if (filteredEventList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_EVENT_NOT_FOUND);
        }

        Event desiredEvent = filteredEventList.get(0);
        String displayedMessage = String.format("Event is %s, insert list here!", desiredEvent.getName());

        return new CommandResult(displayedMessage);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowParticipantsCommand // instanceof handles nulls
                && eventName.equals(((ShowParticipantsCommand) other).eventName)); // state check
    }
}

