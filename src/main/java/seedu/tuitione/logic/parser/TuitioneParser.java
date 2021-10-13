package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.tuitione.logic.commands.AddCommand;
import seedu.tuitione.logic.commands.AddLessonCommand;
import seedu.tuitione.logic.commands.ClearCommand;
import seedu.tuitione.logic.commands.Command;
import seedu.tuitione.logic.commands.DeleteCommand;
import seedu.tuitione.logic.commands.DeleteLessonCommand;
import seedu.tuitione.logic.commands.EditCommand;
import seedu.tuitione.logic.commands.EnrollCommand;
import seedu.tuitione.logic.commands.ExitCommand;
import seedu.tuitione.logic.commands.FindCommand;
import seedu.tuitione.logic.commands.HelpCommand;
import seedu.tuitione.logic.commands.ListCommand;
import seedu.tuitione.logic.commands.UnenrollCommand;
import seedu.tuitione.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TuitioneParser {

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

        case AddLessonCommand.COMMAND_WORD:
            return new AddLessonCommandParser().parse(arguments);

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
            return new HelpCommand();

        case EnrollCommand.COMMAND_WORD:
            return new EnrollCommandParser().parse(arguments);

        case UnenrollCommand.COMMAND_WORD:
            return new UnenrollCommandParser().parse(arguments);

        case DeleteLessonCommand.COMMAND_WORD:
            return new DeleteLessonCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
