package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.exceptions.OperationException;

/**
 * Reverses the last command that modified data
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    private static final String MESSAGE_UNDO_SUCCESS = "Undo successful\nRemaining undo count: %d";
    private static final String MESSAGE_UNDO_FAILURE = "No commands to undo";

    @Override
    public CommandResult execute(Model model) {
        try {
            int remaining = model.undo();
            return new CommandResult(String.format(MESSAGE_UNDO_SUCCESS, remaining));
        } catch (OperationException e) {
            return new CommandResult(MESSAGE_UNDO_FAILURE);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UndoCommand;
    }
}
