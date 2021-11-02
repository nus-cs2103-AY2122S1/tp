package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.modulink.logic.commands.AddFavCommand;
import seedu.modulink.logic.commands.AddModCommand;
import seedu.modulink.logic.commands.Command;
import seedu.modulink.logic.commands.CreateCommand;
import seedu.modulink.logic.commands.DeleteCommand;
import seedu.modulink.logic.commands.EditCommand;
import seedu.modulink.logic.commands.EditGroupStatusCommand;
import seedu.modulink.logic.commands.ExitCommand;
import seedu.modulink.logic.commands.FilterCommand;
import seedu.modulink.logic.commands.FindCommand;
import seedu.modulink.logic.commands.FindIdCommand;
import seedu.modulink.logic.commands.HelpCommand;
import seedu.modulink.logic.commands.ListCommand;
import seedu.modulink.logic.commands.ListFavCommand;
import seedu.modulink.logic.commands.RemFavCommand;
import seedu.modulink.logic.commands.RemoveModCommand;
import seedu.modulink.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

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

        case AddFavCommand.COMMAND_WORD:
            return new AddFavCommandParser().parse(arguments);

        case AddModCommand.COMMAND_WORD:
            return new AddModCommandParser().parse(arguments);

        case CreateCommand.COMMAND_WORD:
            return new CreateCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindIdCommand.COMMAND_WORD:
            return new FindIdCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListFavCommand.COMMAND_WORD:
            return new ListFavCommand();

        case RemFavCommand.COMMAND_WORD:
            return new RemFavCommandParser().parse(arguments);

        case RemoveModCommand.COMMAND_WORD:
            return new RemoveModCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case EditGroupStatusCommand.COMMAND_WORD:
            return new EditGroupStatusCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
