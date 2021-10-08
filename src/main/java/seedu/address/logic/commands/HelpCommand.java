package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_ACTION = "Help";

    public static final String COMMAND_WORD = "help";

    public static final String USER_TIP = "To find more commands, type: " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    /**
     * Returns the description of what the command does.
     *
     * @return Description of what the command does.
     */
    public String getAction() {
        return COMMAND_ACTION;
    }

    /**
     * Returns the format of the valid command with command word and parameters.
     *
     * @return The format of the valid command.
     */
    public String getFormat() {
        return COMMAND_WORD;
    }

    /**
     * Returns an example usage of the command.
     *
     * @return Example usage of the command.
     */
    public String getExample() {
        return COMMAND_WORD;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
