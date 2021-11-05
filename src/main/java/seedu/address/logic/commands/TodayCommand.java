package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMS;
import static seedu.address.logic.commands.CommandResult.DisplayType.TODAY;

/**
 * Navigates forward in the calendar.
 */
public class TodayCommand extends Command {

    public static final String COMMAND_WORD = "today";

    public static final String COMMAND_ACTION = "Go To Today In Calendar";

    public static final String USER_TIP = "To go to the current date on the calendar, type: " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes to the current date.\n" + MESSAGE_NO_PARAMS;

    public static final String TODAY_MESSAGE = "Showing today."
            + " You can navigate forwards by typing \""
            + NextCommand.COMMAND_WORD
            + "\" or navigate backwards using \""
            + BackCommand.COMMAND_WORD
            + "\".";

    @Override
    public CommandResult execute() {
        return new CommandResult(TODAY_MESSAGE, TODAY);
    }
}
