package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Filters the Event list for Events occurring on a specific date and optionally, time.
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

    public static final String MESSAGE_DONE_EVENT_SUCCESS = "Got it, I have added this event to the Event List!\n %1$s";

    private final Event event;

    /**
     * This is a constructor for AddEventCommand.
     *
     * @param event to be added.
     */
    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addEvent(event);
        return new CommandResult(String.format(MESSAGE_DONE_EVENT_SUCCESS, event));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddEventCommand
                && event.equals(((AddEventCommand) other).event));
    }
}
