package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Opens the current user's GitHub.
 */
public class GithubCommand extends Command {

    public static final String COMMAND_WORD = "g";

    public static final String MESSAGE_NO_USER_SELECTED = "There is no user selected. "
            + "Select a user using 'show <INDEX>' and try again.";

    public static final String MESSAGE_SUCCESS = "GitHub shown";


    /**
     * This method attempts to open the GitHub of the user.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which holds the outcome of this method.
     * @throws CommandException if there are any errors during execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.getSelectedIndex() == -1) {
            throw new CommandException(MESSAGE_NO_USER_SELECTED);
        }
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true);
    }
}
