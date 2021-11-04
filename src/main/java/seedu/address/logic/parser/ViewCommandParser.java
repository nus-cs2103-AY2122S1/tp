package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.ViewCommandIndex;
import seedu.address.logic.commands.ViewCommandName;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Name;

/**
 * Parses input arguments and creates a new ViewCommand object.
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        Index index;

        // n/ and preamble is empty
        if (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getPreamble().trim().equals("")) {
            try {
                Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
                return new ViewCommandName(name);
            } catch (ParseException peName) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            }

            // index only
        } else if (!argMultimap.getValue(PREFIX_NAME).isPresent() && !argMultimap.getPreamble().trim().equals("")) {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
                return new ViewCommandIndex(index);
            } catch (ParseException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage()));
            }
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

}
