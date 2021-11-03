package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteCommandIndex;
import seedu.address.logic.commands.DeleteCommandName;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Name;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        Index index;

        // n/ and preamble is empty
        if (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getPreamble().trim().equals("")) {
            try {
                Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
                return new DeleteCommandName(name);
            } catch (ParseException peName) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }
            // index only
        } else if (!argMultimap.getValue(PREFIX_NAME).isPresent() && !argMultimap.getPreamble().trim().equals("")) {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
                return new DeleteCommandIndex(index);
            } catch (ParseException e) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage()));
            }
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

}
