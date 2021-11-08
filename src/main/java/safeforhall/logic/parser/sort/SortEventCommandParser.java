package safeforhall.logic.parser.sort;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.parser.CliSyntax.PREFIX_ORDER;
import static safeforhall.logic.parser.CliSyntax.PREFIX_SORT;

import java.util.stream.Stream;

import safeforhall.logic.commands.sort.SortEventCommand;
import safeforhall.logic.parser.ArgumentMultimap;
import safeforhall.logic.parser.ArgumentTokenizer;
import safeforhall.logic.parser.Parser;
import safeforhall.logic.parser.ParserUtil;
import safeforhall.logic.parser.Prefix;
import safeforhall.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindEventCommand object
 */
public class SortEventCommandParser implements Parser<SortEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortPersonCommand
     * and returns a SortPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortEventCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SORT,
                PREFIX_ORDER);

        if (!arePrefixesPresent(argMultimap, PREFIX_SORT, PREFIX_ORDER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortEventCommand.MESSAGE_USAGE));
        }

        // Required fields
        String field = ParserUtil.parseEventField(argMultimap.getValue(PREFIX_SORT).get());
        String order = ParserUtil.parseOrder(argMultimap.getValue(PREFIX_ORDER).get());

        return new SortEventCommand(field, order);
    }



    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
