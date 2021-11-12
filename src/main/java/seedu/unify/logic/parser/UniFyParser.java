package seedu.unify.logic.parser;

import static seedu.unify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unify.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.unify.logic.commands.AddCommand;
import seedu.unify.logic.commands.ClearCommand;
import seedu.unify.logic.commands.Command;
import seedu.unify.logic.commands.DeleteCommand;
import seedu.unify.logic.commands.DoneCommand;
import seedu.unify.logic.commands.EditCommand;
import seedu.unify.logic.commands.ExitCommand;
import seedu.unify.logic.commands.FindCommand;
import seedu.unify.logic.commands.HelpCommand;
import seedu.unify.logic.commands.ListCommand;
import seedu.unify.logic.commands.ShowCommand;
import seedu.unify.logic.commands.SortCommand;
import seedu.unify.logic.commands.TagCommand;
import seedu.unify.logic.commands.UndoneCommand;
import seedu.unify.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class UniFyParser {

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

        case TagCommand.COMMAND_WORD:
            return new TagCommandParser().parse(arguments);

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DoneCommand.COMMAND_WORD:
            return new DoneCommandParser().parse(arguments);

        case UndoneCommand.COMMAND_WORD:
            return new UndoneCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ShowCommand.COMMAND_WORD:
            return new ShowCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);


        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        }
    }
}





