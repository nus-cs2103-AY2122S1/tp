package seedu.modulink.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.commons.core.Messages.MESSAGE_UNKNOWN_PREFIX_FORMAT;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_MOD;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import seedu.modulink.logic.commands.EditCommand;
import seedu.modulink.logic.commands.EditGroupStatusCommand;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.tag.Mod;

public class EditGroupStatusCommandParser implements Parser<EditGroupStatusCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditGroupStatusCommand
     * and returns an EditGroupStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditGroupStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MOD);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGroupStatusCommand.MESSAGE_USAGE));
        }

        if (!trimmedArgs.startsWith("mod/") && trimmedArgs.contains("/")) {
            throw new ParseException(
                    String.format(MESSAGE_UNKNOWN_PREFIX_FORMAT, EditGroupStatusCommand.MESSAGE_USAGE));
        }

        if (!trimmedArgs.startsWith("mod/") && !trimmedArgs.contains("/")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGroupStatusCommand.MESSAGE_USAGE));
        }

        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();

        try {
            parseModsToAdd(argMultimap.getAllValues(PREFIX_MOD)).ifPresent(editPersonDescriptor::setTags);
        } catch (ParseException e) {
            throw new ParseException(String.format(e.getMessage() + "%s",
                    e.getMessage().startsWith("Unknown prefix(es)") ? EditGroupStatusCommand.MESSAGE_USAGE : ""));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditGroupStatusCommand.MESSAGE_NO_MODULE_SPECIFIED);
        }

        return new EditGroupStatusCommand(editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Mod>> parseModsToAdd(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }

        if (tags.size() > 1) {
            throw new ParseException(EditGroupStatusCommand.MESSAGE_MULTIPLE_MODULES_SPECIFIED);
        }

        if (tags.contains("")) {
            throw new ParseException(EditGroupStatusCommand.MESSAGE_NO_MODULE_SPECIFIED);
        } else {
            return Optional.of(ParserUtil.parseTags(tags));
        }
    }
}
