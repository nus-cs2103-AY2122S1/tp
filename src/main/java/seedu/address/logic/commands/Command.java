package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.CommandUndoException;
import seedu.address.logic.commands.memento.Memento;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    public Memento memento = new Memento();

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**
     * Unexecutes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandUndoException If an error occurs during command execution.
     */
    public void unExecute(Model model) throws CommandUndoException {
        return ;
    };

    public Model getHistoryModel() {
        return memento.getRecord();
    }

    public Memento getMemento() {
        return memento;
    }
}
