package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OUTSTANDING_FEES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.ParserUtil.INDEX_ARGS_COUNT_STUDENT;
import static seedu.address.logic.parser.ParserUtil.STUDENT_INDEX_ZERO_BASED;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LessonAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Homework;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonRates;
import seedu.address.model.lesson.MakeUpLesson;
import seedu.address.model.lesson.OutstandingFees;
import seedu.address.model.lesson.RecurringLesson;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.TimeRange;

/**
 * Parses input arguments and creates a new LessonAddCommand object.
 */
public class LessonAddCommandParser implements Parser<LessonAddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LessonAddCommand
     * and returns a LessonAddCommand object for execution.
     *
     * @param args String argument to be parsed.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LessonAddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RECURRING, PREFIX_DATE, PREFIX_TIME,
                        PREFIX_SUBJECT, PREFIX_HOMEWORK, PREFIX_RATES, PREFIX_OUTSTANDING_FEES);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_TIME, PREFIX_SUBJECT, PREFIX_RATES)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonAddCommand.MESSAGE_USAGE));
        }

        String[] preamble = ParserUtil.parsePreamble(argMultimap.getPreamble());
        if (preamble.length != INDEX_ARGS_COUNT_STUDENT) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonAddCommand.MESSAGE_USAGE));
        }

        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get())
                .orElseThrow(() -> new ParseException(Date.MESSAGE_CONSTRAINTS));

        TimeRange timeRange = ParserUtil.parseTimeRange(argMultimap.getValue(PREFIX_TIME).get());
        Subject subject = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
        Set<Homework> homework = ParserUtil.parseHomeworkList(argMultimap.getAllValues(PREFIX_HOMEWORK));
        LessonRates lessonRates = ParserUtil.parseLessonRates(argMultimap.getValue(PREFIX_RATES).get());
        OutstandingFees outstandingFees = ParserUtil.parseOutstandingFees(argMultimap.getValue(PREFIX_OUTSTANDING_FEES)
                .orElse("0.00"));
        // initialise empty set of cancelledDates
        Set<Date> cancelledDates = new HashSet<>();

        // index should come after missing arguments error and field errors
        Index index = ParserUtil.parseStudentIndex(preamble[STUDENT_INDEX_ZERO_BASED]);

        Lesson lesson;
        // Is a recurring lesson
        if (argMultimap.getValue(PREFIX_RECURRING).isPresent()) {
            // If no date is specified, use max date
            Date endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_RECURRING).get())
                    .orElse(Date.MAX_DATE);

            lesson = new RecurringLesson(date, endDate,
                    timeRange, subject, homework, lessonRates, outstandingFees, cancelledDates);
            return new LessonAddCommand(index, lesson);
        }

        lesson = new MakeUpLesson(date, timeRange, subject, homework, lessonRates,
            outstandingFees, cancelledDates);

        return new LessonAddCommand(index, lesson);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
