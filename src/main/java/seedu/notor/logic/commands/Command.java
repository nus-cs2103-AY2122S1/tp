package seedu.notor.logic.commands;

import seedu.notor.logic.commands.exceptions.CommandException;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public interface Command {
    /**
     * Executes the command and returns the result message.
     *
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException, ExecuteException;
}
