package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_VENUE;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_DESCRIPTION, PREFIX_TASK_DESCRIPTION, PREFIX_IMPORTANCE, PREFIX_TASK_INDEX,
                        PREFIX_TASK_DATE, PREFIX_TASK_TIME, PREFIX_TASK_VENUE);

        Index index;
        Index taskIndex;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editPersonDescriptor.setDescription(ParserUtil.parseDescription(
                    argMultimap.getValue(PREFIX_DESCRIPTION).get()
            ));
        }
        if (argMultimap.getValue(PREFIX_IMPORTANCE).isPresent()) {
            editPersonDescriptor.setImportance(ParserUtil.parseImportance(
                    argMultimap.getValue(PREFIX_IMPORTANCE).get()
            ));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited() && argMultimap.getValue(PREFIX_TASK_INDEX).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_TASK_INDEX).isPresent()) {
            try {
                taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK_INDEX).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
            }
        } else {
            return new EditCommand(index, editPersonDescriptor);
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TASK_DESCRIPTION).isPresent()) {
            editTaskDescriptor.setTaskName(
                    ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_DATE).isPresent()) {
            editTaskDescriptor.setTaskDate(ParserUtil.parseTaskDate(argMultimap.getValue(PREFIX_TASK_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_TIME).isPresent()) {
            editTaskDescriptor.setTaskTime(ParserUtil.parseTaskTime(argMultimap.getValue(PREFIX_TASK_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_VENUE).isPresent()) {
            editTaskDescriptor.setTaskVenue(ParserUtil.parseTaskVenue(argMultimap.getValue(PREFIX_TASK_VENUE).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited() && !editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor, taskIndex, editTaskDescriptor);
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

    /**
     * Parses {@code Collection<String> tasks} into a {@code List<Task>} if {@code tasks} is non-empty.
     * If {@code tasks} contain only one element which is an empty string, it will be parsed into a
     * {@code List<Task>} containing zero task.
     */
    private Optional<List<Task>> parseTasksForEdit(Collection<String> tasks) throws ParseException {
        assert tasks != null;

        if (tasks.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> taskList = tasks.size() == 1 && tasks.contains("") ? Collections.emptyList() : tasks;
        return Optional.of(ParserUtil.parseTasks(taskList));
    }
}
