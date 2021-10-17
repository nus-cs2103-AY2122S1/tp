package dash.logic.parser.taskcommand;

import static dash.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dash.logic.parser.CliSyntax.PREFIX_TAG;
import static dash.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static dash.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.Set;

import dash.logic.commands.taskcommand.AddTaskCommand;
import dash.logic.parser.ArgumentMultimap;
import dash.logic.parser.ArgumentTokenizer;
import dash.logic.parser.Parser;
import dash.logic.parser.ParserUtil;
import dash.logic.parser.exceptions.ParseException;
import dash.model.tag.Tag;
import dash.model.task.CompletionStatus;
import dash.model.task.Task;
import dash.model.task.TaskDate;
import dash.model.task.TaskDescription;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESCRIPTION, PREFIX_TASK_DATE, PREFIX_TAG);
        boolean isDescriptionPrefixPresent = argMultimap.getValue(PREFIX_TASK_DESCRIPTION).isPresent();
        boolean isTaskDatePrefixPresent = argMultimap.getValue(PREFIX_TASK_DATE).isPresent();
        TaskDate taskDate;

        if (!isDescriptionPrefixPresent || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTaskCommand.MESSAGE_USAGE));
        }

        if (isTaskDatePrefixPresent) {
            taskDate = ParserUtil.parseTaskDate(argMultimap.getValue(PREFIX_TASK_DATE).get());
        } else {
            taskDate = new TaskDate();
        }

        TaskDescription description =
                ParserUtil.parseTaskDescription(argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get());
        CompletionStatus completionStatus = new CompletionStatus(false);
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Task task = new Task(description, completionStatus, taskDate, tagList);

        return new AddTaskCommand(task);
    }

}
