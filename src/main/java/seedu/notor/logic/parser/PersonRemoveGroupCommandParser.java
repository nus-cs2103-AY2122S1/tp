package seedu.notor.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.notor.logic.commands.PersonAddGroupCommand;
import seedu.notor.logic.commands.PersonRemoveGroupCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments to create a person command.
 */
public class PersonRemoveGroupCommandParser extends Parser<PersonRemoveGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonRemoveGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GROUP, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP, PREFIX_NAME)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PersonAddGroupCommand.MESSAGE_USAGE));
        }
        String groupName = argMultimap.getValue(PREFIX_GROUP).get();
        String personName = argMultimap.getValue(PREFIX_NAME).get();
        return new PersonRemoveGroupCommand(personName, groupName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
