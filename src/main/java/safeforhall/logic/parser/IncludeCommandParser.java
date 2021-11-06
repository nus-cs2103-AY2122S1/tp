package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.logic.parser.CliSyntax.PREFIX_RESIDENTS;

import java.util.stream.Stream;

import safeforhall.commons.core.index.Index;
import safeforhall.logic.commands.IncludeCommand;
import safeforhall.logic.parser.exceptions.ParseException;
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
                ArgumentTokenizer.tokenize(args, PREFIX_RESIDENTS);

        if (!arePrefixesPresent(argMultimap, PREFIX_RESIDENTS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncludeCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            String preamble = argMultimap.getPreamble();
            index = ParserUtil.parseIndex(preamble);
        } catch (ParseException pe) {
            String message = pe.getMessage() + "\n" + IncludeCommand.MESSAGE_USAGE;
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, message), pe);
        }

        // Required fields
        ResidentList list = ParserUtil.parseResidents(argMultimap.getValue(PREFIX_RESIDENTS).get());

        return new IncludeCommand(index, list);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
