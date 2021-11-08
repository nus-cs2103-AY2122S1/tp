package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.RemoveFromOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.ItemDescriptor;

public class RemoveFromOrderCommandParser implements Parser<RemoveFromOrderCommand> {

    /**
     * Parses {@code userInput} into a {@code RemoveFromOrderCommand} and returns it.
     */
    @Override
    public RemoveFromOrderCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_COUNT, PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_ID).isEmpty() && argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveFromOrderCommand.MESSAGE_USAGE)
            );
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

        return new RemoveFromOrderCommand(toRemoveDescriptor);
    }

}
