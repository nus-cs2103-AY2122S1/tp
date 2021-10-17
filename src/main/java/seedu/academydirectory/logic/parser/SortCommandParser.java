package seedu.academydirectory.logic.parser;

import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_ATTRIBUTE;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_ORDER;

import java.util.stream.Stream;

import seedu.academydirectory.logic.commands.SortCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ATTRIBUTE, PREFIX_ORDER);

        if (!arePrefixesPresent(argMultimap, PREFIX_ATTRIBUTE, PREFIX_ORDER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String attribute = ParserUtil.parseAttribute(argMultimap.getValue(PREFIX_ATTRIBUTE).get());
        boolean isAscending = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_ORDER).get());
        return new SortCommand(attribute, isAscending);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
