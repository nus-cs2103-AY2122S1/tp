package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class SwitchTabCommand extends Command {

    public static final String COMMAND_WORD = "tab";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches tab on the right window.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SWITCHED_TAB_MESSAGE = "Switched tab.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SWITCHED_TAB_MESSAGE, false, false, true);
    }
}
