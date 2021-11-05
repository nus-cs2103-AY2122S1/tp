package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleName;
import seedu.address.model.task.TaskId;

/**
 * Parses input arguments and creates a new EditTaskCommand object.
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @param args Args for editing a student's information.
     * @return EditTaskCommand object created from the user input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_NAME, PREFIX_TASK_ID, PREFIX_TASK_NAME,
                        PREFIX_TASK_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_NAME, PREFIX_TASK_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE));
        }

        ModuleName moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_MODULE_NAME).get());
        TaskId taskId = ParserUtil.parseTaskId(argMultimap.getValue(PREFIX_TASK_ID).get());

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        if (argMultimap.getValue(PREFIX_TASK_NAME).isPresent()) {
            editTaskDescriptor.setTaskName(ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_TASK_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_DEADLINE).isPresent()) {
            editTaskDescriptor
                    .setTaskDeadline(ParserUtil.parseTaskDeadline(argMultimap.getValue(PREFIX_TASK_DEADLINE).get()));
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTaskCommand.MESSAGE_NOT_EDITED);
        }
        editTaskDescriptor.setTaskId(taskId);
        return new EditTaskCommand(moduleName, editTaskDescriptor);
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
