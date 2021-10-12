package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID_DEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ID;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TDelCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TDelCommand object
 */
public class TDelCommandParser implements Parser<TDelCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TDelCommand
     * and returns an TDelCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TDelCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_TASK_ID, PREFIX_MEMBER_ID_DEL);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_ID, PREFIX_MEMBER_ID_DEL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TDelCommand.MESSAGE_USAGE));
        }

        Index taskID = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK_ID).get());
        Index memberID = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEMBER_ID_DEL).get());

        return new TDelCommand(memberID, taskID);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
