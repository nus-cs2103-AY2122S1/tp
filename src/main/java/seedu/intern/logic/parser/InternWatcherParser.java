package seedu.intern.logic.parser;

import static seedu.intern.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.intern.logic.commands.AddCommand;
import seedu.intern.logic.commands.ClearCommand;
import seedu.intern.logic.commands.Command;
import seedu.intern.logic.commands.DeleteCommand;
import seedu.intern.logic.commands.EditCommand;
import seedu.intern.logic.commands.ExitCommand;
import seedu.intern.logic.commands.FilterCommand;
import seedu.intern.logic.commands.FindCommand;
import seedu.intern.logic.commands.HelpCommand;
import seedu.intern.logic.commands.ListCommand;
import seedu.intern.logic.commands.RedoCommand;
import seedu.intern.logic.commands.UndoCommand;
import seedu.intern.logic.commands.ViewCommand;
import seedu.intern.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class InternWatcherParser {

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
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();
        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);
        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
