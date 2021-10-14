package seedu.address.logic.commands;

/**
 * Format full help instructions for every command for display.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String USER_TIP = "To view your weekly schedule, type: " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows your weekly schedule.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_SCHEDULE_MESSAGE = "Switched to schedule view."
            + " You can go back to list view by typing \"list\" or any other valid command.";

    @Override
    public CommandResult execute() {
        return new CommandResult(SHOWING_SCHEDULE_MESSAGE, false, true, false);
    }
}
