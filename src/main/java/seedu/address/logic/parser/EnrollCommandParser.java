package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.DayOfWeek;
import java.time.LocalTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EnrollCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Grade;

/**
 * Parses input arguments and creates a new {@code EnrollCommand} object.
 */
public class EnrollCommandParser implements Parser<EnrollCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EnrollCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        String subject = null;
        Grade grade = null;
        DayOfWeek day = null;
        LocalTime startTime = null;
        Index index;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                userInput,
                PREFIX_SUBJECT,
                PREFIX_GRADE,
                PREFIX_DAY,
                PREFIX_TIME);

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnrollCommand.MESSAGE_USAGE), pe);
        }

        // TODO change to parsing from lesson code, reading indiv prefix to be pushed to add lesson
        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            subject = argMultimap.getValue(PREFIX_SUBJECT).get();
        }

        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            grade = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get());
        }

        if (argMultimap.getValue(PREFIX_DAY).isPresent()) {
            day = ParserUtil.parseDayOfWeek(argMultimap.getValue(PREFIX_DAY).get());
        }

        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            startTime = ParserUtil.parseLocalTime(argMultimap.getValue(PREFIX_TIME).get());
        }

        return new EnrollCommand(index, subject + '-' + grade + '-' + day + '-' + startTime);
    }
}
