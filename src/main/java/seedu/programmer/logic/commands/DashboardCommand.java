package seedu.programmer.logic.commands;

import seedu.programmer.model.Model;

/**
 * Dashboard command for user to see statistics.
 */
public class DashboardCommand extends Command {

    public static final String COMMAND_WORD = "dashboard";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays dashboard of data.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_DASHBOARD_MESSAGE = "Opened dashboard window.";

    @Override
    public DashboardCommandResult execute(Model model) {
        return new DashboardCommandResult(SHOWING_DASHBOARD_MESSAGE);
    }
}
