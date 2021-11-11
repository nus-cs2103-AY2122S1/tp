package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_FAILED = "Can't undo further!";
    public static final String MESSAGE_SUCCESS = "Successfully undone the previous change";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoInternship()) {
            throw new CommandException(MESSAGE_FAILED);
        }

        model.undoInternship();

        return new CommandResult(MESSAGE_SUCCESS);
    }

}
