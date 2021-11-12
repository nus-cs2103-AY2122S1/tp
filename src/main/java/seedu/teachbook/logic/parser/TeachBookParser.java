package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachbook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.teachbook.logic.commands.AddClassCommand;
import seedu.teachbook.logic.commands.AddCommand;
import seedu.teachbook.logic.commands.ClearCommand;
import seedu.teachbook.logic.commands.Command;
import seedu.teachbook.logic.commands.DeleteClassCommand;
import seedu.teachbook.logic.commands.DeleteCommand;
import seedu.teachbook.logic.commands.EditClassCommand;
import seedu.teachbook.logic.commands.EditCommand;
import seedu.teachbook.logic.commands.ExitCommand;
import seedu.teachbook.logic.commands.FindCommand;
import seedu.teachbook.logic.commands.GradeCommand;
import seedu.teachbook.logic.commands.HelpCommand;
import seedu.teachbook.logic.commands.ListCommand;
import seedu.teachbook.logic.commands.MarkCommand;
import seedu.teachbook.logic.commands.PrintCommand;
import seedu.teachbook.logic.commands.RedoCommand;
import seedu.teachbook.logic.commands.RemarkCommand;
import seedu.teachbook.logic.commands.ResetGradeCommand;
import seedu.teachbook.logic.commands.SelectClassCommand;
import seedu.teachbook.logic.commands.SetGradeCommand;
import seedu.teachbook.logic.commands.SortCommand;
import seedu.teachbook.logic.commands.UndoCommand;
import seedu.teachbook.logic.commands.UnmarkCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TeachBookParser {

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
        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case EditClassCommand.COMMAND_WORD:
            return new EditClassCommandParser().parse(arguments);

        case GradeCommand.COMMAND_WORD:
            return new GradeCommandParser().parse(arguments);

        case ResetGradeCommand.COMMAND_WORD:
            return new ResetGradeCommand();

        case PrintCommand.COMMAND_WORD:
            return new PrintCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case SetGradeCommand.COMMAND_WORD:
            return new SetGradeCommandParser().parse(arguments);

        case SelectClassCommand.COMMAND_WORD:
            return new SelectClassCommandParser().parse(arguments);

        case AddClassCommand.COMMAND_WORD:
            return new AddClassCommandParser().parse(arguments);

        case DeleteClassCommand.COMMAND_WORD:
            return new DeleteClassCommandParser().parse(arguments);

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
