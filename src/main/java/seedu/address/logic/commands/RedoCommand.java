package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Redoes the last Command that has been undone. \n" + MESSAGE_NO_PARAMS;

    public static final String MESSAGE_SUCCESS = "%1$s command for %2$s has been redone.";
    public static final String MESSAGE_FAILURE = "No commands to redo!";

    @Override
    public CommandResult execute() throws CommandException {
        requireNonNull(undoRedoStack);

        if (!undoRedoStack.canRedo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        UndoableCommand commandToRedo = undoRedoStack.popRedo();
        Person studentModified = commandToRedo.redo();

        if (studentModified != null && !model.hasPersonFilteredList(studentModified)) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        if (commandToRedo.isClearOrDelete()) {
            String successMessage = commandToRedo.commandType + " command has been redone.";
            return new CommandResult(successMessage);
        }

        String successMessage = String.format(MESSAGE_SUCCESS, commandToRedo.commandType, studentModified.getName());
        return new CommandResult(successMessage, studentModified);
    }

    @Override
    public void setDependencies(Model model, UndoRedoStack undoRedoStack) {
        this.model = model;
        this.undoRedoStack = undoRedoStack;
    }
}
