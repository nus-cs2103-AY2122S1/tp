package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Undoes the last changes made to contact list.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes the last change made to contact list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_UNDO_SUCCESS = "Previous contact list restored.";

    @Override
    public CommandResult execute(Model model) {
        if (model.isUndoable()) {
            model.undo();
            return new CommandResult(MESSAGE_UNDO_SUCCESS);
        } else {
            return new CommandResult(Messages.MESSAGE_INVALID_UNDO_STATE);
        }
    }

}
