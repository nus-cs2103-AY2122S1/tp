package seedu.notor.logic.parser.group;

import static java.util.Objects.requireNonNull;
import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.notor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.notor.logic.commands.group.GroupCommand;
import seedu.notor.logic.commands.group.SubGroupCreateCommand;
import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.parser.ArgumentMultimap;
import seedu.notor.logic.parser.ArgumentTokenizer;
import seedu.notor.logic.parser.ParserUtil;
import seedu.notor.logic.parser.Prefix;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.common.Name;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.tag.Tag;

public class SubGroupCreateCommandParser extends GroupCommandParser {
    public SubGroupCreateCommandParser(String uncheckedIndex, String arguments)
            throws ParseException {
        super(uncheckedIndex, arguments);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the {@code GroupCommand} and
     * returns a {@code GroupCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public GroupCommand parse() throws ParseException {
        requireNonNull(arguments);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arguments, PREFIX_NAME, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SubGroupCreateCommand.MESSAGE_USAGE));
        }
        assert argMultimap.getValue(PREFIX_NAME).isPresent();

        String uncheckedName = argMultimap.getValue(PREFIX_NAME).get();
        if (!Name.isValidName(uncheckedName)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonCreateCommand.MESSAGE_USAGE));
        }

        Name name = new Name(uncheckedName);
        Set<Tag> tagList = new HashSet<>();
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tagList = ParserUtil.parseTags(argMultimap.getValue(PREFIX_TAG).get());
        }

        SubGroup subGroup = new SubGroup(name, tagList);

        return new SubGroupCreateCommand(index, subGroup);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
