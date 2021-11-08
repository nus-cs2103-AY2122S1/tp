package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo the previous command.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_UNDO_SUCCESS = "Undo command successfully!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.undoAddressBook();
            return new CommandResult(MESSAGE_UNDO_SUCCESS);
        } catch (CommandException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_UNDO);
        }
    }

}
