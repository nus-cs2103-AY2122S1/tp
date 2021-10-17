package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.parser.CliSyntax.PREFIX_EVENT;
import static safeforhall.logic.parser.CliSyntax.PREFIX_INFORMATION;

import java.util.stream.Stream;

import safeforhall.logic.commands.IncludeCommand;
import safeforhall.logic.parser.exceptions.ParseException;
import safeforhall.model.event.EventName;
import safeforhall.model.event.ResidentList;

/**
 * Parses input arguments and creates a new IncludeCommand object
 */
public class IncludeCommandParser implements Parser<IncludeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the IncludeCommand
     * and returns an IncludeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public IncludeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT, PREFIX_INFORMATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_EVENT, PREFIX_INFORMATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncludeCommand.MESSAGE_USAGE));
        }

        // Required fields
        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_EVENT).get());
        ResidentList list = ParserUtil.parseResidents(argMultimap.getValue(PREFIX_INFORMATION).get());

        return new IncludeCommand(eventName, list);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
