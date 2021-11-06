package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undoes the last Command that modified TAB data. \n" + MESSAGE_NO_PARAMS;
    public static final String MESSAGE_SUCCESS = "%1$s command for %2$s has been undone.";
    public static final String MESSAGE_FAILURE = "No commands to undo!";

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(undoRedoStack);

        if (!undoRedoStack.canUndo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        UndoableCommand commandToUndo = undoRedoStack.popUndo();

        Person studentModified = commandToUndo.undo();

        if (studentModified != null && !model.hasPersonFilteredList(studentModified)) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        if (commandToUndo.isClearOrAdd()) {
            String successMessage = commandToUndo.commandType + " command has been undone.";
            return new CommandResult(successMessage);
        }

        String successMessage = String.format(MESSAGE_SUCCESS, commandToUndo.commandType, studentModified.getName());
        return new CommandResult(successMessage, studentModified);
    }

    @Override
    public void setDependencies(Model model, UndoRedoStack undoRedoStack) {
        this.model = model;
        this.undoRedoStack = undoRedoStack;
    }
}
