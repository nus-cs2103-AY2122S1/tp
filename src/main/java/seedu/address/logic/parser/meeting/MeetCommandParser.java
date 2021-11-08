package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_ATTENDEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_VENUE;

import java.util.stream.Stream;

import seedu.address.logic.commands.meeting.MeetCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;

/**
 * Parses input arguments and creates a new {@code MeetCommand} object
 */

public class MeetCommandParser implements Parser<MeetCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the MeetCommand
     * and returns a MeetCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MeetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEETING_TITLE, PREFIX_MEETING_DATETIME, PREFIX_MEETING_VENUE,
                        PREFIX_MEETING_ATTENDEE);

        // put all the non-optional arguments here
        if (!arePrefixesPresent(argMultimap, PREFIX_MEETING_TITLE, PREFIX_MEETING_DATETIME, PREFIX_MEETING_VENUE,
                PREFIX_MEETING_ATTENDEE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));
        }

        Description title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MEETING_TITLE).get());
        DateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_MEETING_DATETIME).get());
        Description venue = ParserUtil.parseVenue(argMultimap.getValue(PREFIX_MEETING_VENUE).get());
        Attendee attendee = ParserUtil.parseAttendee(argMultimap.getValue(PREFIX_MEETING_ATTENDEE).get());

        Meeting meeting = new Meeting(dateTime, attendee, title, venue);
        return new MeetCommand(meeting);
    }
}
