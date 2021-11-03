package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COSTPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALESPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.ItemDescriptor;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    public static final String EXTRA_FLAGS_PRESENT = "Invalid Command Format! Delete Command does not require"
            + " costprice, salesprice, count or tag fields.";
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_COUNT, PREFIX_COSTPRICE, PREFIX_SALESPRICE,
                        PREFIX_TAG);

        // Check that either name or id specified
        if (argMultimap.getPreamble().isEmpty() && argMultimap.getValue(PREFIX_ID).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
        ItemDescriptor toDeleteDescriptor = new ItemDescriptor();
        // If any other flags present
        if (!argMultimap.getValue(PREFIX_COUNT).isEmpty() || !argMultimap.getValue(PREFIX_COSTPRICE).isEmpty()
                || !argMultimap.getValue(PREFIX_SALESPRICE).isEmpty()
                || !argMultimap.getValue(PREFIX_TAG).isEmpty()) {
            throw new ParseException(EXTRA_FLAGS_PRESENT);
        }

        // Parse name
        if (!argMultimap.getPreamble().isEmpty()) {
            toDeleteDescriptor.setName(ParserUtil.parseName(argMultimap.getPreamble()));
        }

        // Parse id
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            toDeleteDescriptor.setId(ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get()));
        }

        return new DeleteCommand(toDeleteDescriptor);
    }

}
