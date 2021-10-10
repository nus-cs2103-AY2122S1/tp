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
import dash.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class HelpTabParser {

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
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case SwitchTabContactsCommand.COMMAND_WORD:
            return new SwitchTabContactsCommand(2);

        case SwitchTabTasksCommand.COMMAND_WORD:
            return new SwitchTabTasksCommand(2);

        case SwitchTabHelpCommand.COMMAND_WORD:
            return new SwitchTabHelpCommand(2);

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
