package dash.logic.commands;

import dash.logic.commands.exceptions.CommandException;
import dash.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class SwitchTabHelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to the Help tab.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Switched to Help tab.";
    public static final String MESSAGE_ALREADY_ON_TAB = "You are already on the help tab";

    private final int tabIndex;

    public SwitchTabHelpCommand(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (tabIndex == 2) {
            throw new CommandException(MESSAGE_ALREADY_ON_TAB);
        }
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false, false, false);
    }
}
