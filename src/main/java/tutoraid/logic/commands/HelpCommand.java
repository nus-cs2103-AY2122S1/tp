package tutoraid.logic.commands;

import tutoraid.commons.core.Messages;
import tutoraid.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "\nExample: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(Messages.MESSAGES_SHOWING_HELP_MESSAGE, true, false);
    }
}
