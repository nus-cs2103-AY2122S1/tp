package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Opens a summary of all the commands available.
 */
public class CmdCommand extends Command {
    public static final String COMMAND_WORD = "cmd";

    public static final String SHOWING_CMD_MESSAGE = "Opened Command Summary.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of all available commands.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CMD_MESSAGE, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || other instanceof CmdCommand; // instanceof handles nulls
    }
}
