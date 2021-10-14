package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TaddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new TaddCommand object
 */
public class TaddCommandParser implements Parser<TaddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaddCommand
     * and returns an TaddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TaddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_MEMBER_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MEMBER_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaddCommand.MESSAGE_USAGE));
        }

        Task task = ParserUtil.parseTask(argMultimap.getValue(PREFIX_NAME).get());
        Index memberID = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MEMBER_ID).get());

        return new TaddCommand(memberID, task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
