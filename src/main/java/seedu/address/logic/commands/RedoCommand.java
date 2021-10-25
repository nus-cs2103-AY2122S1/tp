package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.exceptions.OperationException;

/**
 * Re-executes the last command that was reversed
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    private static final String MESSAGE_REDO_SUCCESS = "Redo successful";
    private static final String MESSAGE_REDO_FAILURE = "No commands to redo";

    @Override
    public CommandResult execute(Model model) {
        try {
            model.redo();
        } catch (OperationException e) {
            return new CommandResult(MESSAGE_REDO_FAILURE);
        }

        return new CommandResult(MESSAGE_REDO_SUCCESS);
    }
}
