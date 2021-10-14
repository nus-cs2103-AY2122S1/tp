package seedu.tracker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tracker.commons.core.Messages.MESSAGE_PARAMETER_DUPLICATE;
import static seedu.tracker.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.tracker.logic.commands.SetCommand;
import seedu.tracker.logic.parser.exceptions.ParseException;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;

public class SetCommandParser implements Parser<SetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SetCommand
     * and returns an SetCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ACADEMIC_YEAR, PREFIX_SEMESTER, PREFIX_MC);


        if (argMultimap.getValue(PREFIX_MC).isPresent()) {
            if(argMultimap.getValue(PREFIX_ACADEMIC_YEAR).isPresent() ||
                    argMultimap.getValue(PREFIX_SEMESTER).isPresent()) {
                throw new ParseException(String.format(MESSAGE_PARAMETER_DUPLICATE, SetCommand.MESSAGE_USAGE));
            }

            Mc mc = ParserUtil.parseMc(argMultimap.getValue(PREFIX_MC).get());

            return new SetCommand(mc);
        } else if (arePrefixesPresent(argMultimap, PREFIX_ACADEMIC_YEAR, PREFIX_SEMESTER)) {
            AcademicYear academicYear = ParserUtil.parseAcademicYear(argMultimap.getValue(PREFIX_ACADEMIC_YEAR).get());
            Semester semester = ParserUtil.parseSemester(argMultimap.getValue(PREFIX_SEMESTER).get());

            AcademicCalendar academicCalendar = new AcademicCalendar(academicYear, semester);

            return new SetCommand(academicCalendar);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetCommand.MESSAGE_USAGE));
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
