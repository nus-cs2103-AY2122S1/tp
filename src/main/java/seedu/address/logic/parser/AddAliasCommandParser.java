package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND_WORD;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddAliasCommand object.
 */
public class AddAliasCommandParser implements Parser<AddAliasCommand> {

    @Override
    public AddAliasCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_ALIAS, PREFIX_COMMAND_WORD);

        if (!arePrefixesPresent(argMultiMap, PREFIX_ALIAS, PREFIX_COMMAND_WORD)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAliasCommand.MESSAGE_USAGE));
        }

        String alias = argMultiMap.getValue(PREFIX_ALIAS).get();
        String commandWord = argMultiMap.getValue(PREFIX_COMMAND_WORD).get();

        return new AddAliasCommand(alias, commandWord);
    }

    /**
     * Returns true if all the prefixes are non-empty.
     *
     * @param argMultiMap ArgumentMultimap containing the prefixes.
     * @param prefixes Prefixes that need to present.
     * @return boolean value of whether all prefixes are non-empty.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argMultiMap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argMultiMap.getValue(prefix).isPresent());
    }
}
