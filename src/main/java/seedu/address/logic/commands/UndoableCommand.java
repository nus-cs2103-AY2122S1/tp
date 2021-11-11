package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;

// Solution adapted from
// https://github.com/nus-cs2103-AY1718S2/
// addressbook-level4/blob/master/src/main/java/seedu/
// address/logic/commands/UndoableCommand.java

/**
 * Represents a command that modifies existing data in {@code model#addressBook}.
 */
public abstract class UndoableCommand extends Command {
    public static final String MESSAGE_FAILURE = "The command has been successfully executed previously; "
            + "it should not fail now.";
    public final String commandType;

    protected UndoableCommand(String commandType) {
        this.commandType = commandType;
    }

    protected abstract CommandResult executeUndoableCommand() throws CommandException;

    @Override
    public CommandResult execute() throws CommandException {
        return executeUndoableCommand();
    }

    protected abstract Person undo() throws AssertionError;
    protected abstract Person redo() throws AssertionError;

    /**
     * Gets the Index of the person to undo in the UniquePersonsList.
     *
     * @param personToUndo Person the undo command should execute on.
     * @return The index of the person on the UniquePersonsList.
     */
    protected Index setToDefinitiveIndex(Person personToUndo) {
        ObservableList<Person> mainList = model.getUnfilteredPersonList();
        Index definitiveIndex = Index.fromZeroBased(mainList.indexOf(personToUndo));
        return definitiveIndex;
    }

    protected void checkValidity(Person person) throws AssertionError {
        if (!model.hasPerson(person)) {
            throw new AssertionError(MESSAGE_FAILURE);
        }
    }

    public boolean isClearOrDelete() {
        return commandType.equals(ClearCommand.COMMAND_ACTION)
                || commandType.equals(DeleteCommand.COMMAND_ACTION);
    }

    public boolean isClearOrAdd() {
        return commandType.equals(ClearCommand.COMMAND_ACTION)
                || commandType.equals(AddCommand.COMMAND_ACTION);
    }
}
