package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD1 = "help";
    public static final String COMMAND_WORD2 = "man";

    public static final String MESSAGE_USAGE = COMMAND_WORD1 + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD1;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
