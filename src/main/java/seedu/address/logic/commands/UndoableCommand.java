package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;


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
