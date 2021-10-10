package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT_PRICE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditProductCommand;
import seedu.address.logic.commands.EditProductCommand.EditProductDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditProductCommand object
 */
public class EditProductCommandParser implements Parser<EditProductCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditProductCommand
     * and returns an EditProductCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditProductCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_UNIT_PRICE,
                PREFIX_QUANTITY);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditProductCommand.MESSAGE_USAGE), pe);
        }

        EditProductDescriptor editProductDescriptor = new EditProductDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editProductDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_UNIT_PRICE).isPresent()) {
            editProductDescriptor.setUnitPrice(
                    ParserUtil.parseUnitPrice(argMultimap.getValue(PREFIX_UNIT_PRICE).get()));
        }

        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            editProductDescriptor.setQuantity(ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get()));
        }

        if (!editProductDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditProductCommand.MESSAGE_NOT_EDITED);
        }

        return new EditProductCommand(index, editProductDescriptor);
    }
}
