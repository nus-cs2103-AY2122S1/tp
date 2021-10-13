package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private static String MESSAGE_USAGE;
    /**
     * Creates a HelpCommand with specific help messages
     */
    public HelpCommand(String message) {
        HelpCommand.MESSAGE_USAGE = message;
    }

    public static String getMessageUsage() {
        return MESSAGE_USAGE;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_USAGE, false);
    }
}
