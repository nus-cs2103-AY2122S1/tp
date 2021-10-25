package seedu.academydirectory.logic.commands;

import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.model.VersionedModel;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(VersionedModel model) throws CommandException {
        return null;
    }
}
