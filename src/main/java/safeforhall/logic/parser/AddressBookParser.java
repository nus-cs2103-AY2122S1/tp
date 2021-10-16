package safeforhall.logic.parser;

import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static safeforhall.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import safeforhall.logic.commands.AddCommand;
import safeforhall.logic.commands.ClearCommand;
import safeforhall.logic.commands.Command;
import safeforhall.logic.commands.DeleteCommand;
import safeforhall.logic.commands.EaddCommand;
import safeforhall.logic.commands.ExitCommand;
import safeforhall.logic.commands.FindCommand;
import safeforhall.logic.commands.HelpCommand;
import safeforhall.logic.commands.ListCommand;
import safeforhall.logic.commands.ViewCommand;
import safeforhall.logic.commands.editcommands.EditEventCommand;
import safeforhall.logic.commands.editcommands.EditPersonCommand;
import safeforhall.logic.parser.editcommandparsers.EditEventCommandParser;
import safeforhall.logic.parser.editcommandparsers.EditPersonCommandParser;
import safeforhall.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final Pattern TYPED_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+\\st/\\S+)(?<arguments>.*)");


    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher basicMatcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        final Matcher typeMatcher = TYPED_COMMAND_FORMAT.matcher(userInput.trim());

        final String commandWord;
        final String arguments;

        if (typeMatcher.matches()) {
            commandWord = typeMatcher.group("commandWord");
            arguments = typeMatcher.group("arguments");
        } else if (basicMatcher.matches()) {
            commandWord = basicMatcher.group("commandWord");
            arguments = basicMatcher.group("arguments");
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case EditEventCommand.COMMAND_WORD:
            return new EditEventCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case EaddCommand.COMMAND_WORD:
            return new EaddCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
