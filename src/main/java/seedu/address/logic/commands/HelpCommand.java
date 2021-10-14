package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private static String messageUsage = "";
    /**
     * Creates a HelpCommand with specific help messages
     */
    public HelpCommand(String message) {
        HelpCommand.messageUsage = message;
    }

    public static String getMessageUsage() {
        return messageUsage;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(messageUsage, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HelpCommand // instanceof handles nulls
                && this.messageUsage.equals(((HelpCommand) other).messageUsage));
    }
}
