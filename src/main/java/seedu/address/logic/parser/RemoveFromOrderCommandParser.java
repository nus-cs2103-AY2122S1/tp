package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.RemoveFromOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;
import seedu.address.model.tag.Tag;

public class RemoveFromOrderCommandParser implements Parser<RemoveFromOrderCommand> {
    private static final int DUMMY_COUNT = -1;

    /**
     * Parses {@code userInput} into a {@code AddToOrderCommand} and returns it.
     */
    @Override
    public RemoveFromOrderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID);

        if (!(isNameOrIdPresent(argMultimap))
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, RemoveFromOrderCommand.MESSAGE_USAGE));
        }

        Name name;
        String id;
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            // Use name as long as name is given.
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            id = UUID.randomUUID().toString();
        } else {
            // Use ID if only ID is given.
            name = new Name(StringUtil.generateRandomString());
            id = argMultimap.getValue(PREFIX_ID).get();
        }

        Integer count = DUMMY_COUNT;
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Item item = new Item(name, id, count, tagList);

        return new RemoveFromOrderCommand(item);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean isNameOrIdPresent(ArgumentMultimap argumentMultimap) {
        return arePrefixesPresent(argumentMultimap, PREFIX_NAME) || arePrefixesPresent(argumentMultimap, PREFIX_ID);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
