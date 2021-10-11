package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddFacilityCommand;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.ClearFacilitiesCommand;
import seedu.address.logic.commands.ClearMembersCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindFacilityCommand;
import seedu.address.logic.commands.FindMemberCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListFacilityCommand;
import seedu.address.logic.commands.ListMemberCommand;
import seedu.address.logic.commands.SplitCommand;
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
        case AddMemberCommand.COMMAND_WORD:
            return new AddMemberCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearMembersCommand.COMMAND_WORD:
            return new ClearMembersCommand();

        case ClearFacilitiesCommand.COMMAND_WORD:
            return new ClearFacilitiesCommand();

        case FindMemberCommand.COMMAND_WORD:
            return new FindMemberCommandParser().parse(arguments);

        case ListMemberCommand.COMMAND_WORD:
            return new ListMemberCommand();

        case ListFacilityCommand.COMMAND_WORD:
            return new ListFacilityCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case FindFacilityCommand.COMMAND_WORD:
            return new FindFacilityCommandParser().parse(arguments);

        case AddFacilityCommand.COMMAND_WORD:
            return new AddFacilityCommandParser().parse(arguments);

        case SplitCommand.COMMAND_WORD:
            return new SplitCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
