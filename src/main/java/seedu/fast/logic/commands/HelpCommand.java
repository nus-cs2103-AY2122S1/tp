package seedu.fast.logic.commands;

import seedu.fast.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    String command = "";

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n\n"
            + "Example: \n" + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public HelpCommand(String command) {
        this.command = command;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
