package dash.logic.parser.taskcommand;

import static dash.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dash.logic.parser.CliSyntax.PREFIX_TAG;
import static dash.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import dash.commons.core.index.Index;
import dash.logic.commands.taskcommand.EditTaskCommand;
import dash.logic.commands.taskcommand.EditTaskCommand.EditTaskDescriptor;
import dash.logic.parser.ArgumentMultimap;
import dash.logic.parser.ArgumentTokenizer;
import dash.logic.parser.Parser;
import dash.logic.parser.ParserUtil;
import dash.logic.parser.exceptions.ParseException;
import dash.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESCRIPTION, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTaskCommand.MESSAGE_USAGE),
                    pe);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TASK_DESCRIPTION).isPresent()) {
            editTaskDescriptor.setTaskDescription(ParserUtil
                    .parseTaskDescription(argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editTaskDescriptor::setTags);

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTaskCommand(index, editTaskDescriptor);
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
