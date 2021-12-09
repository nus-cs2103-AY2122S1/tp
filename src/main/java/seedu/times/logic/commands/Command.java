package seedu.times.logic.commands;

import java.util.ArrayList;

import seedu.times.logic.CommandObserver;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.ui.TabName;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    private static final ArrayList<CommandObserver> commandObservers = new ArrayList<>();

    public static void setCommandObserver(CommandObserver commandObserver) {
        commandObservers.add(commandObserver);
    }

    /**
     * Updates the CommandObservers by calling the updateView method for each of them.
     *
     * @param tabToView Tab to be viewed.
     */
    public void updateView(TabName tabToView) {
        for (CommandObserver commandObserver : commandObservers) {
            commandObserver.updateView(tabToView);
        }
    }

    /**
     * Updates the CommandObservers by calling the updateClass command.
     *
     * @param indexOfClassToSelect index of class to be selected.
     */
    public void updateClass(Integer indexOfClassToSelect) {
        for (CommandObserver commandObserver : commandObservers) {
            commandObserver.updateClass(indexOfClassToSelect);
        }
    }

    /**
     * Updates the CommandObservers by calling the hideTuitionClassStudentList command.
     */
    public void hideTuitionClassStudentList() {
        for (CommandObserver commandObserver : commandObservers) {
            commandObserver.hideTuitionClassStudentList();
        }
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

}
