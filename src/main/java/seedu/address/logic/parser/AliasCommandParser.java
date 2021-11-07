package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND;

import java.util.stream.Stream;

import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AliasCommand object
 */
public class AliasCommandParser implements Parser<AliasCommand> {

    public static final String MESSAGE_ALIAS_CONSTRAINTS = "Alias cannot be empty or contain whitespace!";
    public static final String MESSAGE_COMMAND_CONSTRAINTS = "Command cannot be empty!";
    /**
     * Parses the given {@code String} of arguments in the context of the AliasCommand
     * and returns an AliasCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AliasCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ALIAS, PREFIX_COMMAND);

        if (!arePrefixesPresent(argMultimap, PREFIX_ALIAS, PREFIX_COMMAND)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        }

        String alias = argMultimap.getValue(PREFIX_ALIAS).get();
        String command = argMultimap.getValue(PREFIX_COMMAND).get();
        requireNonNull(alias);
        requireNonNull(command);
        alias = alias.trim();
        command = command.trim();

        if (alias.isEmpty() || alias.contains(" ")) {
            throw new ParseException(MESSAGE_ALIAS_CONSTRAINTS);
        }
        if (command.isEmpty()) {
            throw new ParseException(MESSAGE_COMMAND_CONSTRAINTS);
        }
        return new AliasCommand(alias, command);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
