package dash.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dash.commons.core.Messages;
import dash.logic.commands.Command;
import dash.logic.commands.ExitCommand;
import dash.logic.commands.HelpCommand;
import dash.logic.commands.SwitchTabContactsCommand;
import dash.logic.commands.SwitchTabHelpCommand;
import dash.logic.commands.SwitchTabTasksCommand;
import dash.logic.commands.taskcommand.AddTaskCommand;
import dash.logic.commands.taskcommand.AssignPeopleCommand;
import dash.logic.commands.taskcommand.ClearDoneTaskCommand;
import dash.logic.commands.taskcommand.ClearTaskCommand;
import dash.logic.commands.taskcommand.CompleteTaskCommand;
import dash.logic.commands.taskcommand.DeleteTaskCommand;
import dash.logic.commands.taskcommand.EditTaskCommand;
import dash.logic.commands.taskcommand.FindTaskCommand;
import dash.logic.commands.taskcommand.ListTaskCommand;
import dash.logic.commands.taskcommand.TagTaskCommand;
import dash.logic.commands.taskcommand.UpcomingTaskCommand;
import dash.logic.parser.exceptions.ParseException;
import dash.logic.parser.taskcommand.AddTaskCommandParser;
import dash.logic.parser.taskcommand.AssignPeopleCommandParser;
import dash.logic.parser.taskcommand.CompleteTaskCommandParser;
import dash.logic.parser.taskcommand.DeleteTaskCommandParser;
import dash.logic.parser.taskcommand.EditTaskCommandParser;
import dash.logic.parser.taskcommand.FindTaskCommandParser;
import dash.logic.parser.taskcommand.TagTaskCommandParser;
import dash.model.person.Person;
import javafx.collections.ObservableList;

/**
 * Parses user input.
 */
public class TaskTabParser {

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
    public Command parseCommand(String userInput, ObservableList<Person> filteredPersonList) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments, filteredPersonList);

        case EditTaskCommand.COMMAND_WORD:
            return new EditTaskCommandParser().parse(arguments, filteredPersonList);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case TagTaskCommand.COMMAND_WORD:
            return new TagTaskCommandParser().parse(arguments);

        case AssignPeopleCommand.COMMAND_WORD:
            return new AssignPeopleCommandParser().parse(arguments, filteredPersonList);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case FindTaskCommand.COMMAND_WORD:
            return new FindTaskCommandParser().parse(arguments, filteredPersonList);

        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();

        case ClearTaskCommand.COMMAND_WORD:
            return new ClearTaskCommand();

        case ClearDoneTaskCommand.COMMAND_WORD:
            return new ClearDoneTaskCommand();

        case CompleteTaskCommand.COMMAND_WORD:
            return new CompleteTaskCommandParser().parse(arguments);

        case UpcomingTaskCommand.COMMAND_WORD:
            return new UpcomingTaskCommand();

        case SwitchTabContactsCommand.COMMAND_WORD:

        case SwitchTabContactsCommand.COMMAND_WORD_ALTERNATE:
            return new SwitchTabContactsCommand(1);

        case SwitchTabTasksCommand.COMMAND_WORD:

        case SwitchTabTasksCommand.COMMAND_WORD_ALTERNATE:
            return new SwitchTabTasksCommand(1);

        case SwitchTabHelpCommand.COMMAND_WORD:

        case SwitchTabHelpCommand.COMMAND_WORD_ALTERNATE:
            return new SwitchTabHelpCommand(1);

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
