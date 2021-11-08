package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.SortOrder;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_COUNT, PREFIX_TAG);

        if (!onePrefixPresent(argMultimap, PREFIX_NAME, PREFIX_COUNT)
                || anyPrefixFilled(argMultimap, PREFIX_NAME, PREFIX_COUNT)
                || !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            // Sort by name
            return new SortCommand(SortOrder.BY_NAME);
        } else {
            // Sort by count
            return new SortCommand(SortOrder.BY_COUNT);
        }
    }

    /**
     * Returns true if all but one prefix contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean onePrefixPresent(ArgumentMultimap argumentMultimap, Prefix ...prefixes) {
        return Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .count() == 1;
    }

    /**
     * Returns true if any prefixes contains non-empty {@code String} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixFilled(ArgumentMultimap argumentMultimap, Prefix ...prefixes) {
        return Stream.of(prefixes)
                .anyMatch(prefix -> isPrefixFilled(argumentMultimap, prefix));
    }

    /**
     * Returns true if prefix contains an empty {@code String} value in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isPrefixFilled(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return !argumentMultimap.getValue(prefix)
                .orElse("").equals("");
    }

}
