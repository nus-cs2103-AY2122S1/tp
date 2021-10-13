package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNIT_PRICE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddProductCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Name;
import seedu.address.model.product.UnitPrice;

public class AddProductCommandParser implements Parser<AddProductCommand> {
    @Override
    public AddProductCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_UNIT_PRICE, PREFIX_QUANTITY);

        Name name;
        try {
            name = ParserUtil.parseName(argMultimap.getPreamble());
        } catch (ParseException parseException) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddProductCommand.MESSAGE_USAGE), parseException);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_UNIT_PRICE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddProductCommand.MESSAGE_USAGE));
        }

        UnitPrice unitPrice = ParserUtil.parseUnitPrice(argMultimap.getValue(PREFIX_UNIT_PRICE).get());

        AddProductCommand.AddProductDescriptor descriptor =
                new AddProductCommand.AddProductDescriptor(name, unitPrice);

        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            descriptor.setQuantity(ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get()));
        }

        return new AddProductCommand(descriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
