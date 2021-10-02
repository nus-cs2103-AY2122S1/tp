package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class SwitchTabContactsCommand extends Command {

    public static final String COMMAND_WORD = "contacts";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to the Contacts tab.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Switched to Contacts tab.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false, true, false);
    }
}
