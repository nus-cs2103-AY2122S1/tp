package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.BackCommand;
import seedu.address.logic.commands.CalendarCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DayCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LessonAddCommand;
import seedu.address.logic.commands.LessonDeleteCommand;
import seedu.address.logic.commands.LessonEditCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MonthCommand;
import seedu.address.logic.commands.NextCommand;
import seedu.address.logic.commands.PaidCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.RemindCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.commands.TodayCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.WeekCommand;
import seedu.address.logic.commands.YearCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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

        switch (commandWord.toLowerCase()) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case LessonAddCommand.COMMAND_WORD:
            return new LessonAddCommandParser().parse(arguments);

        case LessonEditCommand.COMMAND_WORD:
            return new LessonEditCommandParser().parse(arguments);

        case LessonDeleteCommand.COMMAND_WORD:
            return new LessonDeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case PaidCommand.COMMAND_WORD:
            return new PaidCommandParser().parse(arguments);

        default:
            return parseSingleCommand(commandWord, arguments);
        }

    }

    /**
     * Parse commands that only have 1 command word and no arguments
     */
    private Command parseSingleCommand(String commandWord, String arguments) throws ParseException {
        checkEmptyArgs(commandWord, arguments);
        switch (commandWord.toLowerCase()) {

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case CalendarCommand.COMMAND_WORD:
            return new CalendarCommand();

        case DayCommand.COMMAND_WORD:
            return new DayCommand();

        case WeekCommand.COMMAND_WORD:
            return new WeekCommand();

        case MonthCommand.COMMAND_WORD:
            return new MonthCommand();

        case YearCommand.COMMAND_WORD:
            return new YearCommand();

        case TodayCommand.COMMAND_WORD:
            return new TodayCommand();

        case NextCommand.COMMAND_WORD:
            return new NextCommand();

        case BackCommand.COMMAND_WORD:
            return new BackCommand();

        case TagCommand.COMMAND_WORD:
            return new TagCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RemindCommand.COMMAND_WORD:
            return new RemindCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private void checkEmptyArgs(String commandWord, String args) throws ParseException {
        if (args.isEmpty()) {
            return;
        }
        switch (commandWord.toLowerCase()) {

        case ClearCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        case UndoCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));
        case RedoCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RedoCommand.MESSAGE_USAGE));
        case ListCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        case CalendarCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalendarCommand.MESSAGE_USAGE));
        case DayCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DayCommand.MESSAGE_USAGE));
        case WeekCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, WeekCommand.MESSAGE_USAGE));
        case MonthCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MonthCommand.MESSAGE_USAGE));
        case YearCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, YearCommand.MESSAGE_USAGE));
        case TodayCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TodayCommand.MESSAGE_USAGE));
        case NextCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextCommand.MESSAGE_USAGE));
        case BackCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BackCommand.MESSAGE_USAGE));
        case TagCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        case ExitCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExitCommand.MESSAGE_USAGE));
        case HelpCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        case RemindCommand.COMMAND_WORD:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
