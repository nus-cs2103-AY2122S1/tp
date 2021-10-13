package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;

import java.util.HashSet;

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

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_COUNT);

        // Check that either name (preamble) or id is specified, not both
        if (!(argMultimap.getPreamble().isEmpty() ^ argMultimap.getValue(PREFIX_ID).isEmpty())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        int count = -1;
        if (argMultimap.getValue(PREFIX_COUNT).isPresent()) {
            count = ParserUtil.parseCount(argMultimap.getValue(PREFIX_COUNT).get());
        }

        if (!argMultimap.getPreamble().isEmpty()) {
            // name specified
            Name name = new Name(argMultimap.getPreamble());
            return new DeleteCommand(name, count);
        } else {
            // id specified
            String id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
            return new DeleteCommand(id, count);
        }
    }

}
