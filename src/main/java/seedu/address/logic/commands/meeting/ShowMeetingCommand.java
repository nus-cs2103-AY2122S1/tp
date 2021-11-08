package seedu.address.logic.commands.meeting;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Opens up the meeting window.
 */
public class ShowMeetingCommand extends Command {
    public static final String COMMAND_WORD = "showMeeting";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the meeting window.\n"
            + "Example: " + COMMAND_WORD;
    public static final String SHOWING_MEETING_MESSAGE = "Opened meeting window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_MEETING_MESSAGE, false, false, false, true, false);
    }
}
