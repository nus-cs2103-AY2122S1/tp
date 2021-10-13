package seedu.address.logic.commands;

import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {
    protected Model model;
    protected UndoRedoStack undoRedoStack;

    /**
     * Executes the command and returns the result message.
     *
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute() throws CommandException;

    /**
     * Sets the required components that are required for commands to be executed.
     * This method may be overridden to set the different dependencies required.
     *
     * @param model Model for commands to be executed on.
     * @param undoRedoStack UndoRedoStack that stores the accessible undoable commands.
     */
    public void setDependencies(Model model, UndoRedoStack undoRedoStack) {
        this.model = model;
    }
}
