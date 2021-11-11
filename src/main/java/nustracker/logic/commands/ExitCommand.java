package nustracker.logic.commands;

import nustracker.model.Model;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(Model model, CurrentlyShownList currentlyShownList) {

        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false, false, false, false, false);

    }

}
