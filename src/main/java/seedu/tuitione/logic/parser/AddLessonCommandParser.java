package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.tuitione.model.lesson.LessonTime.TIME_MESSAGE_CONSTRAINTS;
import static seedu.tuitione.model.lesson.LessonTime.isValidTime;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.tuitione.logic.commands.AddLessonCommand;
import seedu.tuitione.logic.parser.exceptions.ParseException;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonTime;
import seedu.tuitione.model.lesson.Price;
import seedu.tuitione.model.lesson.Subject;
import seedu.tuitione.model.student.Grade;

/**
 * Parses input arguments and creates a new AddLessonCommand object
 */
public class AddLessonCommandParser implements Parser<AddLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddLessonCommand
     * and returns an AddLessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLessonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SUBJECT, PREFIX_GRADE, PREFIX_DAY, PREFIX_TIME, PREFIX_COST);

        if (!arePrefixesPresent(argMultimap, PREFIX_SUBJECT, PREFIX_GRADE, PREFIX_DAY, PREFIX_TIME, PREFIX_COST)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLessonCommand.MESSAGE_USAGE));
        }

        Subject subject = ParserUtil.parseSubjectArgs(argMultimap.getValue(PREFIX_SUBJECT).get());
        Grade grade = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get());
        Price price = ParserUtil.parseCostArgs(argMultimap.getValue(PREFIX_COST).get());

        DayOfWeek dayOfWeek = ParserUtil.parseDayOfWeek(argMultimap.getValue(PREFIX_DAY).get());
        LocalTime startTime = ParserUtil.parseLocalTime(argMultimap.getValue(PREFIX_TIME).get());
        if (!isValidTime(startTime)) {
            throw new ParseException(TIME_MESSAGE_CONSTRAINTS);
        }
        LessonTime lessonTime = new LessonTime(dayOfWeek, startTime);

        Lesson lesson = new Lesson(subject, grade, lessonTime, price);
        return new AddLessonCommand(lesson);

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
