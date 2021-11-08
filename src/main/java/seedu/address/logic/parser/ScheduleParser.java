package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddBlockedSlotCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandSummaryCommand;
import seedu.address.logic.commands.DeleteBlockedSlotCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterByTagCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListBlockedSlotsCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListFreeSlotsCommand;
import seedu.address.logic.commands.NextEventCommand;
import seedu.address.logic.commands.UpcomingEventsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Date;

/**
 * Parses user input.
 */
public class ScheduleParser {

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

        case UpcomingEventsCommand.COMMAND_WORD:
            return new UpcomingEventsCommand();

        case NextEventCommand.COMMAND_WORD:
            return new NextEventCommand();

        case ListFreeSlotsCommand.COMMAND_WORD:
            return new ListFreeSlotsCommand(Date.TODAY, LocalTime.now());

        case FilterByTagCommand.COMMAND_WORD:
            return new FilterByTagCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddBlockedSlotCommand.COMMAND_WORD:
            return new AddBlockedSlotCommandParser().parse(arguments);

        case ListBlockedSlotsCommand.COMMAND_WORD:
            return new ListBlockedSlotsCommand();

        case CommandSummaryCommand.COMMAND_WORD:
            return new CommandSummaryCommand();

        case DeleteBlockedSlotCommand.COMMAND_WORD:
            return new DeleteBlockedSlotCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
