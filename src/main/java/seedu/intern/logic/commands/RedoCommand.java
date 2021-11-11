package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;

/**
 * Restores the model to a state before a previous undo action.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Undone action has been redone:  \n%1$s";
    public static final String MESSAGE_NO_REDO = "No action to redo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Redo previous undone action. Only actions that change the applicant list can be redone.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isRedoAvailable()) {
            throw new CommandException(MESSAGE_NO_REDO);
        }

        String redoneCommand = model.redoInternWatcher();
        return new CommandResult(String.format(MESSAGE_SUCCESS, redoneCommand));
    }
}
