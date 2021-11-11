package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_FAILED = "Can't redo further!";
    public static final String MESSAGE_SUCCESS = "Successfully redone the previous change";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoInternship()) {
            throw new CommandException(MESSAGE_FAILED);
        }

        model.redoInternship();

        return new CommandResult(MESSAGE_SUCCESS);
    }

}
