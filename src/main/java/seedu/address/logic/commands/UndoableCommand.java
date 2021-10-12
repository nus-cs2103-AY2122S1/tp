package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;


/**
 * A command that can be undone or redone
 */
public abstract class UndoableCommand extends Command {

    protected abstract CommandResult executeUndoableCommand() throws CommandException;

    @Override
    public CommandResult execute() throws CommandException {
        return executeUndoableCommand();
    }

    protected abstract void undo();
    protected abstract void redo();
}
