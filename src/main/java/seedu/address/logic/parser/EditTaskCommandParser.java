package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_VENUE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditTaskCommand object
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns an EditTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_INDEX, PREFIX_TASK_DESCRIPTION, PREFIX_TASK_DATE,
                        PREFIX_TASK_TIME, PREFIX_TASK_VENUE);

        Index index;
        Index taskIndex;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_TASK_INDEX).isPresent()) {
            taskIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK_INDEX).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE));
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

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_TASK_NOT_EDITED);
        }

        return new EditTaskCommand(index, taskIndex, editTaskDescriptor);
    }
}
