package seedu.notor.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_GROUP;

import java.util.stream.Stream;

import seedu.notor.logic.commands.GroupCreateCommand;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.group.SuperGroup;

/**
 * Parses input arguments to create a group command.
 */
public class GroupCreateCommandParser extends Parser<GroupCreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code GroupCommand} and
     * returns a {@code GroupCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public GroupCreateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GroupCreateCommand.MESSAGE_USAGE));
        }

        SuperGroup superGroup = ParserUtil.parseSuperGroup(argMultimap.getValue(PREFIX_GROUP).get());
        return new GroupCreateCommand(superGroup);
    }
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
