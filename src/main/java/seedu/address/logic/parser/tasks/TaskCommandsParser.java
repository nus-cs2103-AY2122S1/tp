package seedu.address.logic.parser.tasks;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.tasks.AddTaskCommand;
import seedu.address.logic.commands.tasks.AssignTaskToGroupCommand;
import seedu.address.logic.commands.tasks.AssignTaskToPersonCommand;
import seedu.address.logic.commands.tasks.DeleteTaskCommand;
import seedu.address.logic.commands.tasks.EditTaskCommand;
import seedu.address.logic.commands.tasks.FindTaskCommand;
import seedu.address.logic.commands.tasks.ListTaskCommand;
import seedu.address.logic.commands.tasks.MarkTaskDoneStudentCommand;
import seedu.address.logic.commands.tasks.MarkTaskUndoneStudentCommand;
import seedu.address.logic.commands.tasks.UnassignTaskFromGroupCommand;
import seedu.address.logic.commands.tasks.UnassignTaskFromPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class TaskCommandsParser {

    public static final String COMMAND_WORD = "task";

    /**
     * Used for further separation of command action and args.
     */
    private static final Pattern COMMAND_FORMAT = Pattern.compile("(?<action>\\-\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param commandArgs user input string after COMMAND_WORD
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public static Command parseCommand(String commandArgs) throws ParseException {
        final Matcher matcher = COMMAND_FORMAT.matcher(commandArgs.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String action = matcher.group("action");
        final String arguments = matcher.group("arguments");

        switch (action) {

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case AssignTaskToPersonCommand.COMMAND_WORD:
            return new AssignTaskToPersonCommandParser().parse(arguments);

        case AssignTaskToGroupCommand.COMMAND_WORD:
            return new AssignTaskToGroupCommandParser().parse(arguments);

        case UnassignTaskFromPersonCommand.COMMAND_WORD:
            return new UnassignTaskFromPersonCommandParser().parse(arguments);

        case UnassignTaskFromGroupCommand.COMMAND_WORD:
            return new UnassignTaskFromGroupCommandParser().parse(arguments);

        case MarkTaskDoneStudentCommand.COMMAND_WORD:
            return new MarkTaskDoneStudentCommandParser().parse(arguments);

        case MarkTaskUndoneStudentCommand.COMMAND_WORD:
            return new MarkTaskUndoneStudentCommandParser().parse(arguments);

        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments);

        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
