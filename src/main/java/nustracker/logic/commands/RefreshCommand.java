package nustracker.logic.commands;

import nustracker.model.Model;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Format full help instructions for every command for display.
 */
public class RefreshCommand extends Command {

    public static final String COMMAND_WORD = "refresh";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Refreshes the main window.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_REFRESH_MESSAGE = "Refreshed main window.";

    @Override
    public CommandResult execute(Model model, CurrentlyShownList currentlyShownList) {
        return new CommandResult(SHOWING_REFRESH_MESSAGE, false, false, false, false,
                true, false, false);
    }
}
