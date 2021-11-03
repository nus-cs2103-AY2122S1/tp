package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PAID_AMOUNT;
import static seedu.address.logic.parser.ParserUtil.INDEX_ARGS_COUNT_STUDENT_LESSON;
import static seedu.address.logic.parser.ParserUtil.LESSON_INDEX_ZERO_BASED;
import static seedu.address.logic.parser.ParserUtil.STUDENT_INDEX_ZERO_BASED;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PaidCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Money;

/**
 * Parses input arguments and creates a new PaidCommand object.
 */
public class PaidCommandParser implements Parser<PaidCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PaidCommand
     * and returns a PaidCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public PaidCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PAID_AMOUNT);

        String[] preamble = ParserUtil.parsePreamble(argMultimap.getPreamble());
        boolean isInvalidPreamble = preamble.length != INDEX_ARGS_COUNT_STUDENT_LESSON;
        boolean isMissingPrefix = !argMultimap.getValue(PREFIX_PAID_AMOUNT).isPresent();
        if (isInvalidPreamble || isMissingPrefix) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PaidCommand.MESSAGE_USAGE));
        }
        Money payment = ParserUtil.parseMoney(argMultimap.getValue(PREFIX_PAID_AMOUNT).get());

        // index errors should come after field errors and not edited error
        Index studentIndex = ParserUtil.parseStudentIndex(preamble[STUDENT_INDEX_ZERO_BASED]);
        Index lessonIndex = ParserUtil.parseLessonIndex(preamble[LESSON_INDEX_ZERO_BASED]);

        return new PaidCommand(studentIndex, lessonIndex, payment);
    }
}
