package seedu.tracker.logic.parser;

import static seedu.tracker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tracker.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.tracker.logic.commands.AddCommand;
import seedu.tracker.logic.commands.ClearCommand;
import seedu.tracker.logic.commands.Command;
import seedu.tracker.logic.commands.DeleteCommand;
import seedu.tracker.logic.commands.EditCommand;
import seedu.tracker.logic.commands.ExitCommand;
import seedu.tracker.logic.commands.FindCommand;
import seedu.tracker.logic.commands.HelpCommand;
import seedu.tracker.logic.commands.ListCommand;
import seedu.tracker.logic.commands.SetCommand;
import seedu.tracker.logic.commands.TakeCommand;
import seedu.tracker.logic.commands.UntakeCommand;
import seedu.tracker.logic.commands.ViewCommand;
import seedu.tracker.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ModuleTrackerParser {

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

        case UntakeCommand.COMMAND_WORD:
            return new UntakeCommandParser().parse(arguments);

        case TakeCommand.COMMAND_WORD:
            return new TakeCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case SetCommand.COMMAND_WORD:
            return new SetCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
