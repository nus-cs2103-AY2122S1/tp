package seedu.anilist.logic.commands;

import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /** Indicates if the command is an intermediate command which requires
     * a follow-up user confirmation. */
    private final boolean isIntermediate = false;

    /**
     * Indicates if the command is an intermediate command.
     *
     * @return whether the command requires a follow-up user confirmation
     */
    public boolean requiresConfirmation() {
        return this.isIntermediate;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

}
