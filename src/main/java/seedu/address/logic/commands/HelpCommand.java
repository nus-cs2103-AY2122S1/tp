package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMS;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_ACTION = "Help";

    public static final String COMMAND_WORD = "help";

    public static final String USER_TIP = "To find more commands, type: " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + MESSAGE_NO_PARAMS;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute() {
        return new CommandResult(SHOWING_HELP_MESSAGE, CommandResult.DisplayType.HELP);
    }
}
