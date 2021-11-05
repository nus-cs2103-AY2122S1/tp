package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMS;
import static seedu.address.logic.commands.CommandResult.DisplayType.NEXT;

/**
 * Navigates forward in the calendar.
 */
public class NextCommand extends Command {

    public static final String COMMAND_WORD = "next";

    public static final String COMMAND_ACTION = "Go Forwards In Calendar";

    public static final String USER_TIP = "To navigate forward in the calendar, type: " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes forwards in the calendar.\n" + MESSAGE_NO_PARAMS;

    public static final String NEXT_MESSAGE = "Went forward in the calendar."
            + " You can jump back to today by typing \""
            + TodayCommand.COMMAND_WORD
            + "\" or navigate backwards using \""
            + BackCommand.COMMAND_WORD
            + "\".";

    @Override
    public CommandResult execute() {
        return new CommandResult(NEXT_MESSAGE, NEXT);
    }
}
