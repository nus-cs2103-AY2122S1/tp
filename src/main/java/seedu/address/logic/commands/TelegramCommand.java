package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Opens the current user's Telegram.
 */
public class TelegramCommand extends Command {

    public static final String COMMAND_WORD = "te";

    public static final String MESSAGE_NO_USER_SELECTED = "There is no user selected. " +
            "Select a user using 'show <INDEX>' and try again.";

    public static final String MESSAGE_SUCCESS = "Telegram shown";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.getSelectedIndex() == -1) {
            throw new CommandException(MESSAGE_NO_USER_SELECTED);
        }
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false);
    }
}
