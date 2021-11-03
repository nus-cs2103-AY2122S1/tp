package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.AddToOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.ItemDescriptor;

/**
 * Parses input arguments and creates a new AddToOrderCommand object
 */
public class AddToOrderCommandParser implements Parser<AddToOrderCommand> {

    /**
     * Parses {@code userInput} into a {@code AddToOrderCommand} and returns it.
     */
    @Override
    public AddToOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID, PREFIX_COUNT, PREFIX_TAG,
                        PREFIX_COSTPRICE, PREFIX_SALESPRICE);

        if (argMultimap.getValue(PREFIX_ID).isEmpty() && argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToOrderCommand.MESSAGE_USAGE));
        }

        ItemDescriptor toAddDescriptor = new ItemDescriptor();
        // if both name tag and prefix are present
        if (!argMultimap.getPreamble().isEmpty() && argMultimap.getValue(PREFIX_NAME).isPresent()) {
            throw new ParseException("Name field is specified 2 times." + "\n" + AddToOrderCommand.MESSAGE_USAGE);
        }

        // Parse preamble
        if (!argMultimap.getPreamble().isEmpty()) {
            toAddDescriptor.setName(ParserUtil.parseName(argMultimap.getPreamble()));
        }
        // Parse Id
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            toAddDescriptor.setId(ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get()));
        }
        // Parse count
        if (argMultimap.getValue(PREFIX_COUNT).isPresent()) {
            toAddDescriptor.setCount(ParserUtil.parseCount(argMultimap.getValue(PREFIX_COUNT).get()));
        } else {
            toAddDescriptor.setCount(1);
        }
        // Parse costprice
        if (argMultimap.getValue(PREFIX_COSTPRICE).isPresent()) {
            toAddDescriptor.setCostPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_COSTPRICE).get()));
        }
        // Parse salesprice
        if (argMultimap.getValue(PREFIX_SALESPRICE).isPresent()) {
            toAddDescriptor.setSalesPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_SALESPRICE).get()));
        }
        // Parse tag
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            toAddDescriptor.setTags(ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_SALESPRICE)));
        }
        return new AddToOrderCommand(toAddDescriptor);
    }
}
