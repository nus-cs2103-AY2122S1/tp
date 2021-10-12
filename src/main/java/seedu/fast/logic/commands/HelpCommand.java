package seedu.fast.logic.commands;

import seedu.fast.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n\n"
        + "Parameters: \n"
        + "help [COMMAND]\n\n"
        + "Example: \n"
        + COMMAND_WORD + "\n"
        + COMMAND_WORD + " add" + "\n"
        + COMMAND_WORD + " Add";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private String command;

    public HelpCommand(String command) {
        this.command = command;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HelpCommand)) {
            return false;
        }

        // state check
        HelpCommand e = (HelpCommand) other;
        return e.command.equals(((HelpCommand) other).command);
    }
}
