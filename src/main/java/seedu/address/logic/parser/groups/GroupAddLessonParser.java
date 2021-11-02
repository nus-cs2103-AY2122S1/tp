package seedu.address.logic.parser.groups;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX_GIVEN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.time.DayOfWeek;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.groups.GroupAddLessonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Subject;
import seedu.address.model.lesson.Timeslot;

/**
 * Parses input arguments and creates a new EditPersonCommand object
 */
public class GroupAddLessonParser implements Parser<GroupAddLessonCommand> {

    public static final String COMMAND_WORD = "-al";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lesson to the group identified "
            + "by the index number used in the displayed person list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_SUBJECT + "SUBJECT] "
            + "[" + PREFIX_START_TIME + "HH:MM START TIME] "
            + "[" + PREFIX_END_TIME + "HH:MM END TIME] "
            + "[" + PREFIX_DAY + "DAY] ";

    @Override
    public GroupAddLessonCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_SUBJECT, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_DAY);

        if (!argMultimap.arePrefixesPresent(PREFIX_SUBJECT, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_DAY)
                || argMultimap.getPreamble().isEmpty() || !argMultimap.preambleHasExpectedSegments(1)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_INVALID_INDEX_GIVEN), pe);
        }

        Timeslot timeslot = ParserUtil.parseTimeslot(argMultimap.getValue(PREFIX_START_TIME).get(),
                argMultimap.getValue(PREFIX_END_TIME).get());
        Subject subject = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
        DayOfWeek dayOfWeek = ParserUtil.parseDayOfWeek(argMultimap.getValue(PREFIX_DAY).get());
        Lesson lesson = new Lesson(timeslot, subject, dayOfWeek);

        return new GroupAddLessonCommand(index, lesson);
    }
}
