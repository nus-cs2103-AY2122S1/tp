package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.historyStates.exceptions.NoHistoryStatesException;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Undo success";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            model.undo();
        } catch (NoHistoryStatesException e) {
            throw new CommandException("Cannot undo: No history states");
        }
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
