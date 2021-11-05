package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMS;
import static seedu.address.logic.commands.CommandResult.DisplayType.BACK;

/**
 * Navigates backwards in the calendar.
 */
public class BackCommand extends Command {

    public static final String COMMAND_WORD = "back";

    public static final String COMMAND_ACTION = "Go Back In Calendar";

    public static final String USER_TIP = "To navigate backward in the calendar, type: " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Goes backwards in the calendar.\n" + MESSAGE_NO_PARAMS;

    public static final String BACK_MESSAGE = "Went backwards in the calendar."
            + " You can jump back to today by typing \""
            + TodayCommand.COMMAND_WORD
            + "\" or navigate forwards using \""
            + NextCommand.COMMAND_WORD
            + "\".";

    @Override
    public CommandResult execute() {
        return new CommandResult(BACK_MESSAGE, BACK);
    }
}
