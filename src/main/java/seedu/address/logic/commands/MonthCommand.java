package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMS;
import static seedu.address.logic.commands.CommandResult.DisplayType.MONTH;

/**
 * Displays the user's month schedule.
 */
public class MonthCommand extends Command {

    public static final String COMMAND_WORD = "month";

    public static final String COMMAND_ACTION = "View Monthly Calendar";

    public static final String USER_TIP = "To view your monthly calendar, type: " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows your monthly calendar.\n" + MESSAGE_NO_PARAMS;

    public static final String SHOWING_MONTH_MESSAGE = "Showing your monthly calendar."
            + " You can go back to list view by typing \""
            + ListCommand.COMMAND_WORD
            + "\" or navigate the calendar with \""
            + NextCommand.COMMAND_WORD
            + "\" and \""
            + BackCommand.COMMAND_WORD
            + "\".";

    @Override
    public CommandResult execute() {
        return new CommandResult(SHOWING_MONTH_MESSAGE, MONTH);
    }
}
