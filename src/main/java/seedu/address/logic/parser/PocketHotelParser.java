package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.guest.ChargeGuestCommand;
import seedu.address.logic.commands.guest.CheckInNewGuestCommand;
import seedu.address.logic.commands.guest.CheckInReturningGuestCommand;
import seedu.address.logic.commands.guest.CheckOutCommand;
import seedu.address.logic.commands.guest.ClearGuestCommand;
import seedu.address.logic.commands.guest.DeleteGuestCommand;
import seedu.address.logic.commands.guest.EditGuestCommand;
import seedu.address.logic.commands.guest.FilterGuestCommand;
import seedu.address.logic.commands.guest.ListGuestCommand;
import seedu.address.logic.commands.vendor.AddVendorCommand;
import seedu.address.logic.commands.vendor.ClearVendorCommand;
import seedu.address.logic.commands.vendor.DeleteVendorCommand;
import seedu.address.logic.commands.vendor.EditVendorCommand;
import seedu.address.logic.commands.vendor.FilterVendorCommand;
import seedu.address.logic.commands.vendor.ListVendorCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.guest.ChargeGuestCommandParser;
import seedu.address.logic.parser.guest.CheckInNewGuestCommandParser;
import seedu.address.logic.parser.guest.CheckInReturningGuestCommandParser;
import seedu.address.logic.parser.guest.CheckOutCommandParser;
import seedu.address.logic.parser.guest.DeleteGuestCommandParser;
import seedu.address.logic.parser.guest.EditGuestCommandParser;
import seedu.address.logic.parser.guest.FilterGuestCommandParser;
import seedu.address.logic.parser.vendor.AddVendorCommandParser;
import seedu.address.logic.parser.vendor.DeleteVendorCommandParser;
import seedu.address.logic.parser.vendor.EditVendorCommandParser;
import seedu.address.logic.parser.vendor.FilterVendorCommandParser;

/**
 * Parses user input.
 */
public class PocketHotelParser {

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

        case CheckInNewGuestCommand.COMMAND_WORD:
            return new CheckInNewGuestCommandParser().parse(arguments);

        case CheckInReturningGuestCommand.COMMAND_WORD:
            return new CheckInReturningGuestCommandParser().parse(arguments);

        case CheckOutCommand.COMMAND_WORD:
            return new CheckOutCommandParser().parse(arguments);

        case ClearGuestCommand.COMMAND_WORD:
            return new ClearGuestCommand();

        case EditGuestCommand.COMMAND_WORD:
            return new EditGuestCommandParser().parse(arguments);

        case ListGuestCommand.COMMAND_WORD:
            return new ListGuestCommand();

        case DeleteGuestCommand.COMMAND_WORD:
            return new DeleteGuestCommandParser().parse(arguments);

        case FilterGuestCommand.COMMAND_WORD:
            return new FilterGuestCommandParser().parse(arguments);

        case ChargeGuestCommand.COMMAND_WORD:
            return new ChargeGuestCommandParser().parse(arguments);

        case AddVendorCommand.COMMAND_WORD:
            return new AddVendorCommandParser().parse(arguments);

        case DeleteVendorCommand.COMMAND_WORD:
            return new DeleteVendorCommandParser().parse(arguments);

        case ClearVendorCommand.COMMAND_WORD:
            return new ClearVendorCommand();

        case EditVendorCommand.COMMAND_WORD:
            return new EditVendorCommandParser().parse(arguments);

        case ListVendorCommand.COMMAND_WORD:
            return new ListVendorCommand();

        case FilterVendorCommand.COMMAND_WORD:
            return new FilterVendorCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
