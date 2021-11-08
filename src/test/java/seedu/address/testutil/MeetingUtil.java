package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_ATTENDEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_VENUE;

import seedu.address.logic.commands.meeting.MeetCommand;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class for Meeting.
 */
public class MeetingUtil {

    /**
     * Returns an add command string for adding the {@code Meeting}.
     */
    public static String getMeetCommand(Meeting meeting) {
        return MeetCommand.COMMAND_WORD + " " + getMeetingDetails(meeting);
    }

    /**
     * Returns the part of command string for the given {@code Meeting}'s details.
     */
    public static String getMeetingDetails(Meeting meeting) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_MEETING_TITLE + meeting.getTitle().value + " ");
        sb.append(PREFIX_MEETING_DATETIME + meeting.getDateTime().value + " ");
        sb.append(PREFIX_MEETING_VENUE + meeting.getVenue().value + " ");
        sb.append(PREFIX_MEETING_ATTENDEE + meeting.getAttendee().value);
        return sb.toString();
    }
}
