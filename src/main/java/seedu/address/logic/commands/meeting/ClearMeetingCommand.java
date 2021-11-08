package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;

public class ClearMeetingCommand extends Command {

    public static final String COMMAND_WORD = "clearMeeting";

    public static final String MESSAGE_SUCCESS = "Cleared all meetings in NewAddressBook!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Clears all meetings.\n"
        + "Example: " + COMMAND_WORD;

    public static final String NOTHING_TO_CLEAR = "There are no meetings to clear!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> meetingsList = model.getMeetingList();

        if (meetingsList.size() == 0) {
            throw new CommandException(NOTHING_TO_CLEAR);
        }

        model.clearMeetings();

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
