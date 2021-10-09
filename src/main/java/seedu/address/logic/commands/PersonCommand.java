package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Switches to a the Person view in the application.
 */
public class PersonCommand extends Command {

    public static final String COMMAND_WORD = "person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to person view.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_SWITCH_MESSAGE = "Switched to Person View.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_SWITCH_MESSAGE, false, false, true, false);
    }
}
