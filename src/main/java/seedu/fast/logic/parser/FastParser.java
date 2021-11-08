package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.fast.logic.commands.AddCommand;
import seedu.fast.logic.commands.AppointmentCommand;
import seedu.fast.logic.commands.ClearCommand;
import seedu.fast.logic.commands.Command;
import seedu.fast.logic.commands.DeleteAppointmentCommand;
import seedu.fast.logic.commands.DeleteCommand;
import seedu.fast.logic.commands.EditAppointmentCommand;
import seedu.fast.logic.commands.EditCommand;
import seedu.fast.logic.commands.ExitCommand;
import seedu.fast.logic.commands.FindCommand;
import seedu.fast.logic.commands.HelpCommand;
import seedu.fast.logic.commands.ListCommand;
import seedu.fast.logic.commands.MarkAppointmentCommand;
import seedu.fast.logic.commands.RemarkCommand;
import seedu.fast.logic.commands.SortCommand;
import seedu.fast.logic.commands.TagCommand;
import seedu.fast.logic.commands.UnmarkAppointmentCommand;
import seedu.fast.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class FastParser {

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

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case AppointmentCommand.COMMAND_WORD:
            return new AppointmentCommandParser().parse(arguments);

        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parse(arguments);

        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case MarkAppointmentCommand.COMMAND_WORD:
            return new MarkAppointmentCommandParser().parse(arguments);

        case UnmarkAppointmentCommand.COMMAND_WORD:
            return new UnmarkAppointmentCommandParser().parse(arguments);

        case TagCommand.COMMAND_WORD:
            return new TagCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
