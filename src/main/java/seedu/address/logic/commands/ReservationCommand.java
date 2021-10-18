package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Switches to the reservation view in the application.
 */
public class ReservationCommand extends Command {

    public static final String COMMAND_WORD = "reservation";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to reservation view.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_SWITCH_MESSAGE = "Switched to Reservation View.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_SWITCH_MESSAGE, false, false, false, false, false, true);
    }
}

