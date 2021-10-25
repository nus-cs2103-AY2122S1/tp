package seedu.programmer.logic.commands;

import seedu.programmer.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class DashboardCommand extends Command {

    public static final String COMMAND_WORD = "dashboard";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays dashboard of data.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened dashboard window.";

    @Override
    public DashboardCommandResult execute(Model model) {
        return new DashboardCommandResult(SHOWING_HELP_MESSAGE);
    }
}
