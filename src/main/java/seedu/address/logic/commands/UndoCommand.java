package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.summary.Summary;

/**
 * Undoes the last changes made to contact list.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes the last change made to contact list.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_UNDO_SUCCESS = "Previous contact list restored.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.isUndoable()) {
            model.undo();
            Summary summary = new Summary(model.getAddressBook());
            return new CommandResult(MESSAGE_UNDO_SUCCESS, summary);
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_UNDO_STATE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoCommand);
    }

}
