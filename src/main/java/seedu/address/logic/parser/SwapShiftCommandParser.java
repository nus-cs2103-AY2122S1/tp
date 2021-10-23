package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_SHIFT;

import seedu.address.logic.commands.SwapShiftCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SwapShiftCommand object.
 */
public class SwapShiftCommandParser implements Parser<SwapShiftCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SwapShiftCommand
     * and returns a SwapShiftCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public SwapShiftCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DAY_SHIFT, PREFIX_DASH_NAME);

        // Checks if there are exactly 2 "- n" fields and exactly 2 "d/" fields
        if (argMultimap.getAllValues(PREFIX_DASH_NAME).size() != 2
                || argMultimap.getAllValues(PREFIX_DAY_SHIFT).size() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwapShiftCommand.MESSAGE_USAGE));
        }
        return null;
    }
}
