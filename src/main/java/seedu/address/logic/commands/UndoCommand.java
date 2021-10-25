package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.exceptions.OperationException;

/**
 * Reverses the last command that modified data
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    private static final String MESSAGE_UNDO_SUCCESS = "Undo successful";
    private static final String MESSAGE_UNDO_FAILURE = "No commands to undo";

    @Override
    public CommandResult execute(Model model) {
        try {
            model.undo();
        } catch (OperationException e) {
            return new CommandResult(MESSAGE_UNDO_FAILURE);
        }

        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }
}
