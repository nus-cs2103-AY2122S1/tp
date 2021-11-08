package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redo the previous command.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_REDO_SUCCESS = "Redo command successfully!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.redoAddressBook();
            return new CommandResult(MESSAGE_REDO_SUCCESS);
        } catch (CommandException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_REDO);
        }
    }
}
