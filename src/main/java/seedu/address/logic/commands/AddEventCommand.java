package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Adds an Event to the Event list
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "addEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an event with the specified name "
            + "on the specified date (in YYYY-MM-DD format) and optionally includes time (in 24hr format)\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_TIME + "TIME]\n"
            + "Example: " + COMMAND_WORD + " n/CS2106 Finals d/2021-09-20 t/0900\n";

    public static final String MESSAGE_ADD_EVENT_SUCCESS = "Added Event:\n%1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists!";

    private final Event event;

    /**
     * This is a constructor for AddEventCommand.
     *
     * @param event to be added.
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        this.event = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasEvent(event)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        model.addEvent(event);
        return new CommandResult(String.format(MESSAGE_ADD_EVENT_SUCCESS, event));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddEventCommand
                && event.equals(((AddEventCommand) other).event));
    }
}
