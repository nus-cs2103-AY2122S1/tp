package seedu.tracker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_SEMESTER;

import java.util.stream.Stream;

import seedu.tracker.commons.core.index.Index;
import seedu.tracker.logic.commands.TakeCommand;
import seedu.tracker.logic.parser.exceptions.ParseException;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;

/**
 * Parses input arguments and creates a new TakeCommand object
 */
public class TakeCommandParser implements Parser<TakeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TakeCommand
     * and returns an TakeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TakeCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ACADEMIC_YEAR, PREFIX_SEMESTER);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TakeCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_ACADEMIC_YEAR, PREFIX_SEMESTER)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TakeCommand.MESSAGE_USAGE));
        }

        AcademicYear academicYear = ParserUtil.parseAcademicYear(argMultimap.getValue(PREFIX_ACADEMIC_YEAR).get());
        Semester semester = ParserUtil.parseSemester(argMultimap.getValue(PREFIX_SEMESTER).get());

        AcademicCalendar academicCalendar = new AcademicCalendar(academicYear, semester);

        return new TakeCommand(index, academicCalendar);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
