package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkStudentAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkStudentAttendanceCommand object
 */
public class MarkStudentAttendanceCommandParser implements Parser<MarkStudentAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkStudentAttendanceCommand
     * and returns a MarkStudentAttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkStudentAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_WEEK);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkStudentAttendanceCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_WEEK).isPresent()) {
            int week = ParserUtil.parseWeek(argMultimap.getValue(PREFIX_WEEK).get());
            return new MarkStudentAttendanceCommand(index, week);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkStudentAttendanceCommand.MESSAGE_USAGE));
    }
}
