package seedu.notor.logic.parser.person;

import static java.util.Objects.requireNonNull;
import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_GROUPNAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_SUBGROUP;

import java.util.stream.Stream;

import seedu.notor.logic.commands.person.PersonAddGroupCommand;
import seedu.notor.logic.commands.person.PersonCommand;
import seedu.notor.logic.parser.ArgumentMultimap;
import seedu.notor.logic.parser.ArgumentTokenizer;
import seedu.notor.logic.parser.Prefix;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;

/**
 * Parses input arguments to create a person command.
 */
public class PersonAddGroupCommandParser extends PersonCommandParser {
    public PersonAddGroupCommandParser(String unparsedIndex, String arguments) throws ParseException {
        super(unparsedIndex, arguments);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PersonCommand parse() throws ParseException {
        requireNonNull(arguments);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arguments, PREFIX_GROUPNAME, PREFIX_SUBGROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUPNAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonAddGroupCommand.MESSAGE_USAGE));
        }
        assert argMultimap.getValue(PREFIX_GROUPNAME).isPresent();

        String groupName = argMultimap.getValue(PREFIX_GROUPNAME).get();

        String subGroupName = null;
        if (argMultimap.getValue(PREFIX_SUBGROUP).isPresent()) {
            subGroupName = argMultimap.getValue(PREFIX_SUBGROUP).get();
        }

        // TODO: Check here for validty after Yukun allow special characters.
        if (!SuperGroup.isValidGroupName(groupName)) {
            throw new ParseException("Please input a valid group.");
        }

        if (subGroupName != null && !SubGroup.isValidGroupName(subGroupName)) {
            throw new ParseException("Please input a valid subgroup.");
        }

        if (subGroupName != null) {
            return new PersonAddGroupCommand(index, groupName + "_" + subGroupName);
        }
        return new PersonAddGroupCommand(index, groupName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
