package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undoes the last Command that modified TAB data. \n";

    public static final String MESSAGE_SUCCESS = "Undo success!";
    public static final String MESSAGE_FAILURE = "No commands to undo!";

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(undoRedoStack);

        if (!undoRedoStack.canUndo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        Person studentModified = undoRedoStack.popUndo().undo();
        return new CommandResult(MESSAGE_SUCCESS, studentModified);
    }

    @Override
    public void setDependencies(Model model, UndoRedoStack undoRedoStack) {
        this.model = model;
        this.undoRedoStack = undoRedoStack;
    }
}
