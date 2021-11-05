package tutoraid.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tutoraid.commons.core.Messages;
import tutoraid.logic.commands.AddCommand;
import tutoraid.logic.commands.ClearCommand;
import tutoraid.logic.commands.Command;
import tutoraid.logic.commands.DeleteCommand;
import tutoraid.logic.commands.EditCommand;
import tutoraid.logic.commands.ExitCommand;
import tutoraid.logic.commands.FindCommand;
import tutoraid.logic.commands.HelpCommand;
import tutoraid.logic.commands.ListCommand;
import tutoraid.logic.commands.ViewCommand;
import tutoraid.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TutorAidParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher;
        final String commandWord;
        final String arguments;

        matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        commandWord = matcher.group("commandWord");
        arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
