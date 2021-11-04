package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_VISIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT;
import static seedu.address.model.util.SortUtil.SORT_BY_LAST_VISIT;
import static seedu.address.model.util.SortUtil.SORT_BY_NEXT_VISIT;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_VISIT, PREFIX_LAST_VISIT);

        if (argMultimap.isAllPresent(PREFIX_LAST_VISIT, PREFIX_VISIT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        } else if (ParserUtil.isPrefixPresentAndEmpty(argMultimap, PREFIX_LAST_VISIT)) {
            return new SortCommand(SORT_BY_LAST_VISIT, false);

        } else if (ParserUtil.isPrefixPresentAndEmpty(argMultimap, PREFIX_VISIT)) {
            return new SortCommand(SORT_BY_NEXT_VISIT, true);

        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        }

    }

}
