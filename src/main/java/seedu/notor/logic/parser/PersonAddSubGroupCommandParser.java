package seedu.notor.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_SUBGROUP;

import java.util.stream.Stream;

import seedu.notor.logic.commands.PersonAddSubGroupCommand;
import seedu.notor.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments to create a person command.
 */
public class PersonAddSubGroupCommandParser extends Parser<PersonAddSubGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonAddSubGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GROUP, PREFIX_SUBGROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP, PREFIX_NAME, PREFIX_SUBGROUP)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PersonAddSubGroupCommand.MESSAGE_USAGE));
        }

        String personName = argMultimap.getValue(PREFIX_NAME).get();
        String groupName = argMultimap.getValue(PREFIX_GROUP).get();
        String subGroupName = argMultimap.getValue(PREFIX_SUBGROUP).get();

        return new PersonAddSubGroupCommand(personName, groupName, subGroupName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
