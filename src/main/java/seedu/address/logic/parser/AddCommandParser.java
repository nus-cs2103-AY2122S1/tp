package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COSTPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALESPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.ItemDescriptor;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static Logger logger = Logger.getLogger("AddCommandParser");

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "going to start parsing");
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_COUNT, PREFIX_TAG,
                        PREFIX_COSTPRICE, PREFIX_SALESPRICE);

        if (argMultimap.getValue(PREFIX_ID).isEmpty() && argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        ItemDescriptor toAddDescriptor = new ItemDescriptor();

        // Parse name
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
        // Parse salesPrice
        if (argMultimap.getValue(PREFIX_SALESPRICE).isPresent()) {
            toAddDescriptor.setSalesPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_SALESPRICE).get()));
        }
        // Parse costPrice
        if (argMultimap.getValue(PREFIX_COSTPRICE).isPresent()) {
            toAddDescriptor.setCostPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_COSTPRICE).get()));
        }
        // Parse tags
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            toAddDescriptor.setTags(ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG)));
        }

        return new AddCommand(toAddDescriptor);
    }

}
