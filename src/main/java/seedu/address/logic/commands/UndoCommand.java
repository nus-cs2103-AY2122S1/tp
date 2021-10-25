package seedu.address.logic.commands;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    private static final String MESSAGE_UNDO_SUCCESS = "Last command undid";
    private static final String MESSAGE_UNDO_FAILURE = "No commands to undo";

    @Override
    public CommandResult execute(Model model) throws CommandException, DataConversionException {
        boolean isSuccessful = model.undo();

        return new CommandResult(isSuccessful ? MESSAGE_UNDO_SUCCESS : MESSAGE_UNDO_FAILURE);
    }
}
