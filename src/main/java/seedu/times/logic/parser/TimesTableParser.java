package seedu.times.logic.parser;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.times.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.times.logic.commands.AddCommand;
import seedu.times.logic.commands.ClearCommand;
import seedu.times.logic.commands.Command;
import seedu.times.logic.commands.DeleteCommand;
import seedu.times.logic.commands.EditCommand;
import seedu.times.logic.commands.ExitCommand;
import seedu.times.logic.commands.FindNameCommand;
import seedu.times.logic.commands.FindTagCommand;
import seedu.times.logic.commands.HelpCommand;
import seedu.times.logic.commands.ListCommand;
import seedu.times.logic.commands.SortCommand;
import seedu.times.logic.commands.ViewCommand;
import seedu.times.logic.commands.classcommands.AddClassCommand;
import seedu.times.logic.commands.classcommands.AddToClassCommand;
import seedu.times.logic.commands.classcommands.DeleteClassCommand;
import seedu.times.logic.commands.classcommands.EditClassCommand;
import seedu.times.logic.commands.classcommands.FindClassCommand;
import seedu.times.logic.commands.classcommands.FindClassNameCommand;
import seedu.times.logic.commands.classcommands.ListClassCommand;
import seedu.times.logic.commands.classcommands.RemoveFromClassCommand;
import seedu.times.logic.commands.classcommands.SelectClassCommand;
import seedu.times.logic.parser.classcommandparsers.AddClassCommandParser;
import seedu.times.logic.parser.classcommandparsers.AddToClassCommandParser;
import seedu.times.logic.parser.classcommandparsers.DeleteClassCommandParser;
import seedu.times.logic.parser.classcommandparsers.EditClassCommandParser;
import seedu.times.logic.parser.classcommandparsers.FindClassCommandParser;
import seedu.times.logic.parser.classcommandparsers.FindClassNameCommandParser;
import seedu.times.logic.parser.classcommandparsers.RemoveFromClassCommandParser;
import seedu.times.logic.parser.classcommandparsers.SelectClassCommandParser;
import seedu.times.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TimesTableParser {

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

        //// student commands

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindNameCommand.COMMAND_WORD:
            return new FindNameCommandParser().parse(arguments);

        case FindTagCommand.COMMAND_WORD:
            return new FindTagCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        //// tuition class commands

        case ListClassCommand.COMMAND_WORD:
            return new ListClassCommand();

        case AddClassCommand.COMMAND_WORD:
            return new AddClassCommandParser().parse(arguments);

        case AddToClassCommand.COMMAND_WORD:
            return new AddToClassCommandParser().parse(arguments);

        case RemoveFromClassCommand.COMMAND_WORD:
            return new RemoveFromClassCommandParser().parse(arguments);

        case EditClassCommand.COMMAND_WORD:
            return new EditClassCommandParser().parse(arguments);

        case SelectClassCommand.COMMAND_WORD:
            return new SelectClassCommandParser().parse(arguments);

        case DeleteClassCommand.COMMAND_WORD:
            return new DeleteClassCommandParser().parse(arguments);

        case FindClassCommand.COMMAND_WORD:
            return new FindClassCommandParser().parse(arguments);

        case FindClassNameCommand.COMMAND_WORD:
            return new FindClassNameCommandParser().parse(arguments);

        //// general application command
        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
