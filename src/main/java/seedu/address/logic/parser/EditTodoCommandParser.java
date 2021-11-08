package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTodoCommand;
import seedu.address.logic.commands.EditTodoCommand.EditTodoDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditTodoCommand object
 */
public class EditTodoCommandParser implements Parser<EditTodoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTodoCommand
     * and returns an EditTodoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTodoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(MESSAGE_INVALID_TODO_DISPLAYED_INDEX, e);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTodoCommand.MESSAGE_USAGE), pe);
        }

        EditTodoDescriptor editTodoDescriptor = new EditTodoDescriptor();
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editTodoDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editTodoDescriptor::setTags);

        if (!editTodoDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTodoCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTodoCommand(index, editTodoDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
