package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;

/**
 * Reverts the model to a previous state.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Action has been undone: \n%1$s";
    public static final String MESSAGE_NO_UNDO = "No action to undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undo previous action. Only actions that change the applicant list can be undone.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isUndoAvailable()) {
            throw new CommandException(MESSAGE_NO_UNDO);
        }

        String undoneCommand = model.undoInternWatcher();
        return new CommandResult(String.format(MESSAGE_SUCCESS, undoneCommand));
    }
}
