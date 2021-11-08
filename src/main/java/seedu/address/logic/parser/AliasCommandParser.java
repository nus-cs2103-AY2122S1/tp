package seedu.address.logic.parser;

import static seedu.address.logic.commands.AliasCommand.MESSAGE_INVALID_COMMAND_FORMAT_ALIAS_ABSENT;
import static seedu.address.logic.commands.AliasCommand.MESSAGE_INVALID_COMMAND_FORMAT_COMMAND_ABSENT;
import static seedu.address.logic.commands.AliasCommand.MESSAGE_INVALID_COMMAND_FORMAT_PREAMBLE_PRESENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND;

import java.util.stream.Stream;

import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AliasCommand object
 */
public class AliasCommandParser implements Parser<AliasCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AliasCommand
     * and returns an AliasCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AliasCommand parse(String args) throws ParseException {
        AddressBookParser parser = new AddressBookParser();
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ALIAS, PREFIX_COMMAND);

        if (!arePrefixesPresent(argMultimap, PREFIX_ALIAS)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT_ALIAS_ABSENT, AliasCommand.MESSAGE_USAGE));
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_COMMAND)) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT_COMMAND_ABSENT, AliasCommand.MESSAGE_USAGE));
        }
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT_PREAMBLE_PRESENT, AliasCommand.MESSAGE_USAGE));
        }

        String alias = ParserUtil.parseAlias(argMultimap.getValue(PREFIX_ALIAS).get());
        String rawCommand = argMultimap.getValue(PREFIX_COMMAND).get();
        Command command = parser.parseCommand(rawCommand);

        if (AddressBookParser.COMMAND_WORDS.contains(alias)) {
            throw new ParseException(AliasCommand.MESSAGE_INVALID_ALIAS);
        }

        return new AliasCommand(alias, command, rawCommand);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
