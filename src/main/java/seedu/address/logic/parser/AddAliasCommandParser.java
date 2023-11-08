package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandWord;
import seedu.address.model.alias.Shortcut;

/**
 * Parses input arguments and creates a new AddAliasCommand object.
 */
public class AddAliasCommandParser implements Parser<AddAliasCommand> {

    @Override
    public AddAliasCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_SHORTCUT, PREFIX_COMMAND_WORD);

        if (!arePrefixesPresent(argMultiMap, PREFIX_SHORTCUT, PREFIX_COMMAND_WORD)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAliasCommand.MESSAGE_USAGE));
        }

        Shortcut shortcut = ParserUtil.parseShortcut(argMultiMap.getValue(PREFIX_SHORTCUT).get());
        CommandWord commandWord = ParserUtil.parseCommandWord(argMultiMap.getValue(PREFIX_COMMAND_WORD).get());

        return new AddAliasCommand(new Alias(shortcut, commandWord));
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
