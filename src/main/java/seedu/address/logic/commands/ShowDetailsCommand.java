package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Changes the remark of an existing person in the address book.
 */
public class ShowDetailsCommand extends Command {

    public static final String COMMAND_WORD = "showDetails";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the details of the Event matching the given name.\n"
            + "Parameters: EVENT_NAME \n"
            + "Example: " + COMMAND_WORD + " CS2103T Finals ";
    
    private static final String MESSAGE_SUCCESS = "Event found!";

    private final Predicate<Event> eventName;

    public ShowDetailsCommand(Predicate<Event> eventName) {
        this.eventName = eventName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Event desiredEvent = model.getEventList().filtered(eventName).get(0);
        String displayedMessage = String.format("Event Name: %s\nEvent Date: %s\nEvent Time: %s", 
                desiredEvent.getName(), 
                desiredEvent.getDate(), 
                desiredEvent.getTime().toString().equals("") ? "N/A" : desiredEvent.getTime());
        return new CommandResult(displayedMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowDetailsCommand // instanceof handles nulls
                && eventName.equals(((ShowDetailsCommand) other).eventName)); // state check
    }
}