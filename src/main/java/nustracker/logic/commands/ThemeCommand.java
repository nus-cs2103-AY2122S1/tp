package nustracker.logic.commands;

import nustracker.model.Model;
import nustracker.ui.MainWindow.CurrentlyShownList;

/**
 * Format full help instructions for every command for display.
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches between Light and Dark theme.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_THEME_MESSAGE = "Switched theme.";

    @Override
    public CommandResult execute(Model model, CurrentlyShownList currentlyShownList) {
        return new CommandResult(SHOWING_THEME_MESSAGE, false, false, false, true,
                false, false, false);
    }
}
