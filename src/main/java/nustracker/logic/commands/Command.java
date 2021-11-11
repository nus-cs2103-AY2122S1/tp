package nustracker.logic.commands;

import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @param currentlyShownList the list which is currently being shown in the Main Window.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model, CurrentlyShownList currentlyShownList)
            throws CommandException;

}
