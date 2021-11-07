package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.commands.AddFacilityCommand;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.AllocateMemberCommand;
import seedu.address.logic.commands.ClearAttendanceCommand;
import seedu.address.logic.commands.ClearFacilitiesCommand;
import seedu.address.logic.commands.ClearMembersCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeallocateMemberCommand;
import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.logic.commands.DeleteFacilityCommand;
import seedu.address.logic.commands.DeleteMemberCommand;
import seedu.address.logic.commands.EditFacilityCommand;
import seedu.address.logic.commands.EditMemberCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.FindFacilityCommand;
import seedu.address.logic.commands.FindMemberCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListFacilityCommand;
import seedu.address.logic.commands.ListMemberCommand;
import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.commands.SetMemberAvailabilityCommand;
import seedu.address.logic.commands.ShowAliasesCommand;
import seedu.address.logic.commands.SortMemberCommand;
import seedu.address.logic.commands.SplitCommand;
import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.alias.AliasMap;

/**
 * Parses user input.
 */
public class SportsPaParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @param aliases AliasMap for user commands
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, AliasMap aliases) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = aliases.convertAliasIfPresent(matcher.group("commandWord"));
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case AddMemberCommand.COMMAND_WORD:
            return new AddMemberCommandParser().parse(arguments);

        case EditMemberCommand.COMMAND_WORD:
            return new EditMemberCommandParser().parse(arguments);

        case DeleteMemberCommand.COMMAND_WORD:
            return new DeleteMemberCommandParser().parse(arguments);

        case DeleteFacilityCommand.COMMAND_WORD:
            return new DeleteFacilityCommandParser().parse(arguments);

        case EditFacilityCommand.COMMAND_WORD:
            return new EditFacilityCommandParser().parse(arguments);

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

        case SetMemberAvailabilityCommand.COMMAND_WORD:
            return new SetMemberAvailabilityCommandParser().parse(arguments);

        case SplitCommand.COMMAND_WORD:
            return new SplitCommandParser().parse(arguments);

        case MarkAttendanceCommand.COMMAND_WORD:
            return new MarkAttendanceCommandParser().parse(arguments);

        case UnmarkAttendanceCommand.COMMAND_WORD:
            return new UnmarkAttendanceCommandParser().parse(arguments);

        case ClearAttendanceCommand.COMMAND_WORD:
            return new ClearAttendanceCommand();

        case AddAliasCommand.COMMAND_WORD:
            return new AddAliasCommandParser().parse(arguments);

        case DeleteAliasCommand.COMMAND_WORD:
            return new DeleteAliasCommandParser().parse(arguments);

        case ShowAliasesCommand.COMMAND_WORD:
            return new ShowAliasesCommand();

        case AllocateMemberCommand.COMMAND_WORD:
            return new AllocateMemberCommandParser().parse(arguments);

        case DeallocateMemberCommand.COMMAND_WORD:
            return new DeallocateMemberCommandParser().parse(arguments);

        case ImportCommand.COMMAND_WORD:
            return new ImportCommandParser().parse(arguments);

        case ExportCommand.COMMAND_WORD:
            return new ExportCommand();

        case SortMemberCommand.COMMAND_WORD:
            return new SortMemberCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
