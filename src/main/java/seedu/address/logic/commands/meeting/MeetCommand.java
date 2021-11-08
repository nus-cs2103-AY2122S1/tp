package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_ATTENDEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_VENUE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Meeting;

/**
 * Adds a meeting to NewAddressBook.
 */
public class MeetCommand extends Command {
    public static final String COMMAND_WORD = "meet";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to NewAddressBook. "
            + "Parameters: "
            + PREFIX_MEETING_TITLE + "TITLE "
            + PREFIX_MEETING_DATETIME + "DATETIME "
            + PREFIX_MEETING_VENUE + "VENUE "
            + PREFIX_MEETING_ATTENDEE + "ATTENDEE_TYPE \n "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MEETING_TITLE + "Math consult with class 4D "
            + PREFIX_MEETING_DATETIME + "2040-12-25 13:45 "
            + PREFIX_MEETING_VENUE + "Seminar room 3 "
            + PREFIX_MEETING_ATTENDEE + "S ";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_MEETING_CONFLICT = "There is a conflict with an existing meeting.";
    public static final String MESSAGE_PAST_DATETIME =
            "Meeting datetime must be in the future. The current datetime is: %s";

    private final Meeting toAdd;

    /**
     * Creates a MeetCommand to add the specified {@code Meeting}
     */
    public MeetCommand(Meeting meeting) {
        requireNonNull(meeting);
        toAdd = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMeetingConflict(toAdd)) {
            throw new CommandException(MESSAGE_MEETING_CONFLICT);
        } else if (toAdd.isExpiredMeeting()) {
            throw new CommandException(String.format(MESSAGE_PAST_DATETIME, DateTime.getCurrentDateTime()));
        }

        model.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof MeetCommand // instanceof handles nulls
            && toAdd.equals(((MeetCommand) other).toAdd));
    }
}
