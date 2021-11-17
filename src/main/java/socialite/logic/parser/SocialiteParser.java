package socialite.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import socialite.commons.core.Messages;
import socialite.logic.commands.AddCommand;
import socialite.logic.commands.ClearCommand;
import socialite.logic.commands.Command;
import socialite.logic.commands.DeleteCommand;
import socialite.logic.commands.EditCommand;
import socialite.logic.commands.ExitCommand;
import socialite.logic.commands.FindCommand;
import socialite.logic.commands.HelpCommand;
import socialite.logic.commands.ListCommand;
import socialite.logic.commands.PictureCommand;
import socialite.logic.commands.PinCommand;
import socialite.logic.commands.RemarkCommand;
import socialite.logic.commands.ShareCommand;
import socialite.logic.commands.UnpinCommand;
import socialite.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class SocialiteParser {

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

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case ShareCommand.COMMAND_WORD:
            return new ShareCommandParser().parse(arguments);

        case PictureCommand.COMMAND_WORD:
            return new PictureCommandParser().parse(arguments);

        case PinCommand.COMMAND_WORD:
            return new PinCommandParser().parse(arguments);

        case UnpinCommand.COMMAND_WORD:
            return new UnpinCommandParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
