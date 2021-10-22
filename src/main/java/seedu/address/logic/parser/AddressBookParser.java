package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddDeadlineTaskCommand;
import seedu.address.logic.commands.AddEventTaskCommand;
import seedu.address.logic.commands.AddGithubGroupCommand;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.AddStudentGroupCommand;
import seedu.address.logic.commands.AddTodoTaskCommand;
import seedu.address.logic.commands.BackCommand;
import seedu.address.logic.commands.ClearAllCommand;
import seedu.address.logic.commands.ClearAllTasksCommand;
import seedu.address.logic.commands.ClearStudentsCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListGroupCommand;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.commands.ListTaskHistoryCommand;
import seedu.address.logic.commands.MarkStudentAttCommand;
import seedu.address.logic.commands.MarkStudentPartCommand;
import seedu.address.logic.commands.MarkTaskDoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case BackCommand.COMMAND_WORD:
            return new BackCommand();

        case AddStudentCommand.COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteStudentCommand.COMMAND_WORD:
            return new DeleteStudentCommandParser().parse(arguments);

        case MarkStudentAttCommand.COMMAND_WORD:
            return new MarkStudentAttCommandParser().parse(arguments);

        case ClearAllCommand.COMMAND_WORD:
            return new ClearAllCommand();

        case ClearAllTasksCommand.COMMAND_WORD:
            return new ClearAllTasksCommand();

        case MarkStudentPartCommand.COMMAND_WORD:
            return new MarkStudentPartCommandParser().parse(arguments);

        case ClearStudentsCommand.COMMAND_WORD:
            return new ClearStudentsCommand();

        case FindStudentCommand.COMMAND_WORD:
            return new FindStudentCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListStudentCommand.COMMAND_WORD:
            return new ListStudentCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddTodoTaskCommand.COMMAND_WORD:
            return new AddTodoTaskCommandParser().parse(arguments);

        case AddDeadlineTaskCommand.COMMAND_WORD:
            return new AddDeadlineTaskCommandParser().parse(arguments);

        case AddEventTaskCommand.COMMAND_WORD:
            return new AddEventTaskCommandParser().parse(arguments);

        case MarkTaskDoneCommand.COMMAND_WORD:
            return new MarkTaskDoneCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();

        case AddGroupCommand.COMMAND_WORD:
            return new AddGroupCommandParser().parse(arguments);

        case DeleteGroupCommand.COMMAND_WORD:
            return new DeleteGroupCommandParser().parse(arguments);

        case ListGroupCommand.COMMAND_WORD:
            return new ListGroupCommand();

        case AddStudentGroupCommand.COMMAND_WORD:
            return new AddStudentGroupCommandParser().parse(arguments);

        case AddGithubGroupCommand.COMMAND_WORD:
            return new AddGithubGroupCommandParser().parse(arguments);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments);

        case ListTaskHistoryCommand.COMMAND_WORD:
            return new ListTaskHistoryCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
