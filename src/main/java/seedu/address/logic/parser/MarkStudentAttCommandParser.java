package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkStudentAttCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkStudentAttCommand object
 */
public class MarkStudentAttCommandParser implements Parser<MarkStudentAttCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkStudentAttCommand
     * and returns a MarkStudentAttCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkStudentAttCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_WEEK);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkStudentAttCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_WEEK).isPresent()) {
            int week = ParserUtil.parseWeek(argMultimap.getValue(PREFIX_WEEK).get());
            return new MarkStudentAttCommand(index, week);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkStudentAttCommand.MESSAGE_USAGE));
    }
}
