package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddTodoTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TodoTask;

public class AddTodoTaskCommandParser implements Parser<AddTodoTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTodoTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG, PREFIX_DESCRIPTION, PREFIX_PRIORITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoTaskCommand.MESSAGE_USAGE));
        }

        if (args.contains(PREFIX_DEADLINE.toString()) || args.contains(PREFIX_EVENT.toString())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    "Todo Tasks should not contain a date."));
        }

        Description description;

        if (arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        } else {
            description = Description.NO_DESCRIPTION;
        }

        TaskName name = ParserUtil.parseTaskName(argMultimap.getValue(PREFIX_NAME).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Task.Priority priority = ParserUtil.parsePriority(argMultimap.getPriorityUsingPrefix(PREFIX_PRIORITY).get());

        TodoTask task = new TodoTask(name, tagList, description, priority);
        return new AddTodoTaskCommand(task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
