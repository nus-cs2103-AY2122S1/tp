package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.smartnus.logic.commands.AddNoteCommand;
import seedu.smartnus.logic.commands.ClearCommand;
import seedu.smartnus.logic.commands.Command;
import seedu.smartnus.logic.commands.DeleteCommand;
import seedu.smartnus.logic.commands.EditCommand;
import seedu.smartnus.logic.commands.ExitCommand;
import seedu.smartnus.logic.commands.FindCommand;
import seedu.smartnus.logic.commands.HelpCommand;
import seedu.smartnus.logic.commands.ListCommand;
import seedu.smartnus.logic.commands.StatCommand;
import seedu.smartnus.logic.commands.ThemeCommand;
import seedu.smartnus.logic.commands.questions.AddMcqCommand;
import seedu.smartnus.logic.commands.questions.AddSaqCommand;
import seedu.smartnus.logic.commands.questions.AddTfqCommand;
import seedu.smartnus.logic.commands.quiz.QuizCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.logic.parser.quiz.QuizCommandParser;

/**
 * Parses user input.
 */
public class SmartNusParser {

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

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case StatCommand.COMMAND_WORD:
            return new StatCommandParser().parse(arguments);

        case AddMcqCommand.COMMAND_WORD:
            return new AddMcqCommandParser().parse(arguments);

        case AddTfqCommand.COMMAND_WORD:
            return new AddTfqCommandParser().parse(arguments);

        case AddSaqCommand.COMMAND_WORD:
            return new AddSaqCommandParser().parse(arguments);

        case QuizCommand.COMMAND_WORD:
            return new QuizCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);

        case ThemeCommand.COMMAND_WORD:
            return new ThemeCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
