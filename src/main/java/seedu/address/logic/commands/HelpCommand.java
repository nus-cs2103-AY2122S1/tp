package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Formats full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    /**
     * Stands for help command.
     */
    public static final String COMMAND_WORD = "help";

    /**
     * Stands for the message of help command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Stands for the message of help command and show help window.
     */
    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    /**
     * Overrides and executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related to help command.
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false, false, false);
    }
}
