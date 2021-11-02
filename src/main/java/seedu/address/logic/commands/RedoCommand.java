package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.exceptions.OperationException;

/**
 * Re-executes the last command that was reversed
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    private static final String MESSAGE_REDO_SUCCESS = "Redo successful\nRemaining redo count: %d";
    private static final String MESSAGE_REDO_FAILURE = "No commands to redo";

    @Override
    public CommandResult execute(Model model) {
        try {
            int remaining = model.redo();
            return new CommandResult(String.format(MESSAGE_REDO_SUCCESS, remaining));
        } catch (OperationException e) {
            return new CommandResult(MESSAGE_REDO_FAILURE);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RedoCommand;
    }
}
