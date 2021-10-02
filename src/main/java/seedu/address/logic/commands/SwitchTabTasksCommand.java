package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class SwitchTabTasksCommand extends Command {

    public static final String COMMAND_WORD = "tasks";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to the Tasks tab.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Switched to Tasks tab.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false, false, true);
    }
}
