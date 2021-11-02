package seedu.edrecord.logic.parser;

import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.edrecord.logic.commands.AddCommand;
import seedu.edrecord.logic.commands.CdCommand;
import seedu.edrecord.logic.commands.ClearCommand;
import seedu.edrecord.logic.commands.Command;
import seedu.edrecord.logic.commands.DeleteAssignmentCommand;
import seedu.edrecord.logic.commands.DeleteCommand;
import seedu.edrecord.logic.commands.DeleteGradeCommand;
import seedu.edrecord.logic.commands.DeleteGroupCommand;
import seedu.edrecord.logic.commands.DeleteModuleCommand;
import seedu.edrecord.logic.commands.EditAssignmentCommand;
import seedu.edrecord.logic.commands.EditCommand;
import seedu.edrecord.logic.commands.ExitCommand;
import seedu.edrecord.logic.commands.FindCommand;
import seedu.edrecord.logic.commands.GradeCommand;
import seedu.edrecord.logic.commands.HelpCommand;
import seedu.edrecord.logic.commands.ListClassesCommand;
import seedu.edrecord.logic.commands.ListCommand;
import seedu.edrecord.logic.commands.ListModulesCommand;
import seedu.edrecord.logic.commands.MakeAssignmentCommand;
import seedu.edrecord.logic.commands.MakeGroupCommand;
import seedu.edrecord.logic.commands.MakeModuleCommand;
import seedu.edrecord.logic.commands.MoveCommand;
import seedu.edrecord.logic.commands.RemoveCommand;
import seedu.edrecord.logic.commands.ViewCommand;
import seedu.edrecord.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class EdRecordParser {

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

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case MakeModuleCommand.COMMAND_WORD:
            return new MakeModuleCommandParser().parse(arguments);

        case MakeGroupCommand.COMMAND_WORD:
            return new MakeGroupCommandParser().parse(arguments);

        case DeleteModuleCommand.COMMAND_WORD:
            return new DeleteModuleCommandParser().parse(arguments);

        case DeleteGroupCommand.COMMAND_WORD:
            return new DeleteGroupCommandParser().parse(arguments);

        case MoveCommand.COMMAND_WORD:
            return new MoveCommandParser().parse(arguments);

        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case CdCommand.COMMAND_WORD:
            return new CdCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case MakeAssignmentCommand.COMMAND_WORD:
            return new MakeAssignmentCommandParser().parse(arguments);

        case DeleteAssignmentCommand.COMMAND_WORD:
            return new DeleteAssignmentCommandParser().parse(arguments);

        case EditAssignmentCommand.COMMAND_WORD:
            return new EditAssignmentCommandParser().parse(arguments);

        case GradeCommand.COMMAND_WORD:
            return new GradeCommandParser().parse(arguments);

        case DeleteGradeCommand.COMMAND_WORD:
            return new DeleteGradeCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListModulesCommand.COMMAND_WORD:
            return new ListModulesCommand();

        case ListClassesCommand.COMMAND_WORD:
            return new ListClassesCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
