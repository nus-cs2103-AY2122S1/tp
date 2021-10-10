package dash.logic.parser.taskcommand;

import dash.commons.core.Messages;
import dash.logic.commands.taskcommand.AddTaskCommand;
import dash.logic.parser.ArgumentMultimap;
import dash.logic.parser.ArgumentTokenizer;
import dash.logic.parser.CliSyntax;
import dash.logic.parser.Parser;
import dash.logic.parser.ParserUtil;
import dash.logic.parser.exceptions.ParseException;
import dash.model.task.Task;
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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_TASK_DESCRIPTION);
        boolean isPrefixPresent = argMultimap.getValue(CliSyntax.PREFIX_TASK_DESCRIPTION).isPresent();

        if (!isPrefixPresent || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTaskCommand.MESSAGE_USAGE));
        }

        TaskDescription description =
                ParserUtil.parseTaskDescription(argMultimap.getValue(CliSyntax.PREFIX_TASK_DESCRIPTION).get());

        Task task = new Task(description);

        return new AddTaskCommand(task);
    }
}
