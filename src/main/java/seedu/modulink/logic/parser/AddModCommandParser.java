package seedu.modulink.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.commons.core.Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_MOD;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import seedu.modulink.logic.commands.AddModCommand;
import seedu.modulink.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.tag.Mod;

public class AddModCommandParser implements Parser<AddModCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddModCommand
     * and returns an AddModCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddModCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MOD);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()
            || parseModsToAdd(argMultimap.getAllValues(PREFIX_MOD)).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        parseModsToAdd(argMultimap.getAllValues(PREFIX_MOD)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(AddModCommand.MESSAGE_NO_CHANGE);
        }

        return new AddModCommand(editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Mod>> parseModsToAdd(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            throw new ParseException(AddModCommand.MESSAGE_NO_CHANGE);
        }

        Collection<String> tagSet;
        if (tags.size() == 1 && tags.contains("")) {
            throw new ParseException(AddModCommand.MESSAGE_NO_CHANGE);
        } else {
            try {
                return Optional.of(ParserUtil.parseTags(tags));
            } catch (ParseException e) {
                throw new ParseException(String.format(MESSAGE_UNKNOWN_PREFIX_FORMAT, AddModCommand.MESSAGE_USAGE));
            }
        }
    }
}
