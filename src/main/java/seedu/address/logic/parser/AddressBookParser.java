package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddShiftCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteShiftCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.RemoveMarkCommand;
import seedu.address.logic.commands.SchedulePeriodChangeCommand;
import seedu.address.logic.commands.SetRoleReqCommand;
import seedu.address.logic.commands.SetShiftTimeCommand;
import seedu.address.logic.commands.StaffIndividualStatisticsCommand;
import seedu.address.logic.commands.StaffStatisticsCommand;
import seedu.address.logic.commands.SwapShiftCommand;
import seedu.address.logic.commands.SwitchTabCommand;
import seedu.address.logic.commands.ViewShiftCommand;
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
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ViewShiftCommand.COMMAND_WORD:
            return new ViewShiftCommandParser().parse(arguments);

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case RemoveMarkCommand.COMMAND_WORD:
            return new RemoveMarkCommandParser().parse(arguments);

        case SwitchTabCommand.COMMAND_WORD:
            return new SwitchTabCommand();

        case DeleteShiftCommand.COMMAND_WORD:
            return new DeleteShiftCommandParser().parse(arguments);

        case StaffIndividualStatisticsCommand.COMMAND_WORD:
            return new StaffIndividualStatisticsCommandParser().parse(arguments);

        case SwapShiftCommand.COMMAND_WORD:
            return new SwapShiftCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case AddShiftCommand.COMMAND_WORD:
            return new AddShiftCommandParser().parse(arguments);

        case SetShiftTimeCommand.COMMAND_WORD:
            return new SetShiftTimeCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case SetRoleReqCommand.COMMAND_WORD:
            return new SetRoleReqCommandParser().parse(arguments);

        case SchedulePeriodChangeCommand.COMMAND_WORD:
            return new SchedulePeriodChangeCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case StaffStatisticsCommand.COMMAND_WORD:
            return new StaffStatisticsCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
