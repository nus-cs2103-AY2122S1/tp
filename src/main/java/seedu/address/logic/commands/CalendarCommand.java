package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_NO_PARAMS;
import static seedu.address.logic.commands.CommandResult.DisplayType.CALENDAR;

/**
 * Displays the user's calendar.
 */
public class CalendarCommand extends Command {

    public static final String COMMAND_WORD = "calendar";

    public static final String COMMAND_ACTION = "View Calendar";

    public static final String USER_TIP = "To switch to calendar view, type: " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the calendar.\n" + MESSAGE_NO_PARAMS;

    public static final String SHOWING_CALENDAR_MESSAGE = String.format(
            "Showing your calendar. You can go back to list view by typing \"%1$s\", or navigate the calendar with "
                    + "\"%2$s\", \"%3$s\", \"%4$s\", \"%5$s\", \"%6$s\", \"%7$s\", or \"%8$s\".",
            ListCommand.COMMAND_WORD,
            DayCommand.COMMAND_WORD, WeekCommand.COMMAND_WORD, MonthCommand.COMMAND_WORD, YearCommand.COMMAND_WORD,
            NextCommand.COMMAND_WORD, BackCommand.COMMAND_WORD, TodayCommand.COMMAND_WORD);

    @Override
    public CommandResult execute() {
        return new CommandResult(SHOWING_CALENDAR_MESSAGE, CALENDAR);
    }
}
