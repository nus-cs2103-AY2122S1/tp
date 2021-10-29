package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.AddLastMarkCommand;
import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.commands.AddStudentToGroupCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAllMarkCommand;
import seedu.address.logic.commands.DeleteClassCommand;
import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.commands.DeleteLastMarkCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.DeleteStudentFromGroupCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindClassCommand;
import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListClassCommand;
import seedu.address.logic.commands.ListStudentCommand;
import seedu.address.logic.commands.ViewClassCommand;
import seedu.address.logic.commands.ViewGroupCommand;
import seedu.address.logic.commands.ViewStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ClassmateParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>(.|\n)*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string.
     * @return the command based on the user input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        //No breaks provided since all cases are return/throw statements

        case AddStudentCommand.COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case AddClassCommand.COMMAND_WORD:
            return new AddClassCommandParser().parse(arguments);

        case AddGroupCommand.COMMAND_WORD:
            return new AddGroupCommandParser().parse(arguments);

        case AddStudentToGroupCommand.COMMAND_WORD:
            return new AddStudentToGroupCommandParser().parse(arguments);

        case AddLastMarkCommand.COMMAND_WORD:
            return new AddLastMarkCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteStudentCommand.COMMAND_WORD:
            return new DeleteStudentCommandParser().parse(arguments);

        case DeleteGroupCommand.COMMAND_WORD:
            return new DeleteGroupCommandParser().parse(arguments);

        case DeleteClassCommand.COMMAND_WORD:
            return new DeleteClassCommandParser().parse(arguments);

        case DeleteStudentFromGroupCommand.COMMAND_WORD:
            return new DeleteStudentFromGroupCommandParser().parse(arguments);

        case DeleteLastMarkCommand.COMMAND_WORD:
            return new DeleteLastMarkCommandParser().parse(arguments);

        case DeleteAllMarkCommand.COMMAND_WORD:
            return new DeleteAllMarkCommandParser().parse(arguments);

        case ViewClassCommand.COMMAND_WORD:
            return new ViewClassCommandParser().parse(arguments);

        case ViewGroupCommand.COMMAND_WORD:
            return new ViewGroupCommandParser().parse(arguments);

        case ViewStudentCommand.COMMAND_WORD:
            return new ViewStudentCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindStudentCommand.COMMAND_WORD:
            return new FindStudentCommandParser().parse(arguments);

        case FindClassCommand.COMMAND_WORD:
            return new FindClassCommandParser().parse(arguments);

        case ListStudentCommand.COMMAND_WORD:
            return new ListStudentCommand();

        case ListClassCommand.COMMAND_WORD:
            return new ListClassCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}

