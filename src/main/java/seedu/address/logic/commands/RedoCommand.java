package seedu.address.logic.commands;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    private static final String MESSAGE_REDO_SUCCESS = "Redo successful";
    private static final String MESSAGE_REDO_FAILURE = "No commands to redo";

    @Override
    public CommandResult execute(Model model) throws CommandException, DataConversionException {
        boolean isSuccessful = model.redo();

        return new CommandResult(isSuccessful ? MESSAGE_REDO_SUCCESS : MESSAGE_REDO_FAILURE);

    }
}
