package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COSTPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALESPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.ItemDescriptor;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class RemoveCommandParser implements Parser<RemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns an RemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_COUNT, PREFIX_TAG, PREFIX_COSTPRICE,
                        PREFIX_SALESPRICE);

        if (argMultimap.getValue(PREFIX_ID).isEmpty() && argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
        }

        ItemDescriptor toRemoveDescriptor = new ItemDescriptor();

        // Parse name
        if (!argMultimap.getPreamble().isEmpty()) {
            toRemoveDescriptor.setName(ParserUtil.parseName(argMultimap.getPreamble()));
        }
        // Parse Id
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            toRemoveDescriptor.setId(ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get()));
        }
        // Parse count
        if (argMultimap.getValue(PREFIX_COUNT).isPresent()) {
            toRemoveDescriptor.setCount(ParserUtil.parseCount(argMultimap.getValue(PREFIX_COUNT).get()));
        } else {
            toRemoveDescriptor.setCount(1);
        }
        if (argMultimap.getValue(PREFIX_COSTPRICE).isPresent()) {
            toRemoveDescriptor.setCostPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_COSTPRICE).get()));
        }
        if (argMultimap.getValue(PREFIX_SALESPRICE).isPresent()) {
            toRemoveDescriptor.setSalesPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_SALESPRICE).get()));
        }

        return new RemoveCommand(toRemoveDescriptor);
    }

}
