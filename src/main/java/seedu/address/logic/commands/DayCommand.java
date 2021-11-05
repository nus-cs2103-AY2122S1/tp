package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMS;
import static seedu.address.logic.commands.CommandResult.DisplayType.DAY;

/**
 * Displays the user's daily calendar.
 */
public class DayCommand extends Command {

    public static final String COMMAND_WORD = "day";

    public static final String COMMAND_ACTION = "View Daily Calendar";

    public static final String USER_TIP = "To view your daily calendar, type: " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows your daily calendar.\n" + MESSAGE_NO_PARAMS;

    public static final String SHOWING_DAY_MESSAGE = "Showing your daily calendar."
            + " You can go back to list view by typing \""
            + ListCommand.COMMAND_WORD
            + "\" or navigate the calendar with \""
            + NextCommand.COMMAND_WORD
            + "\" and \""
            + BackCommand.COMMAND_WORD
            + "\".";

    @Override
    public CommandResult execute() {
        return new CommandResult(SHOWING_DAY_MESSAGE, DAY);
    }
}
