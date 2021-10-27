package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * The command to display the meeting window.
 */
public class ShowMeetingCommand extends Command {
    public static final String COMMAND_WORD = "showMeeting";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the meeting window.\n"
            + "Example: " + COMMAND_WORD;
    public static final String SHOWING_HELP_MESSAGE = "Opened meeting window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false, false, true, false);
    }
}
