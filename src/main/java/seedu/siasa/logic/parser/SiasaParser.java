package seedu.siasa.logic.parser;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.siasa.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.siasa.logic.commands.ClearCommand;
import seedu.siasa.logic.commands.Command;
import seedu.siasa.logic.commands.DownloadCommand;
import seedu.siasa.logic.commands.ExitCommand;
import seedu.siasa.logic.commands.HelpCommand;
import seedu.siasa.logic.commands.contact.AddContactCommand;
import seedu.siasa.logic.commands.contact.ClearContactPolicyCommand;
import seedu.siasa.logic.commands.contact.DeleteContactCommand;
import seedu.siasa.logic.commands.contact.EditContactCommand;
import seedu.siasa.logic.commands.contact.FindContactCommand;
import seedu.siasa.logic.commands.contact.ListContactCommand;
import seedu.siasa.logic.commands.contact.ListContactPolicyCommand;
import seedu.siasa.logic.commands.contact.SortContactCommand;
import seedu.siasa.logic.commands.policy.AddPolicyCommand;
import seedu.siasa.logic.commands.policy.DeletePolicyCommand;
import seedu.siasa.logic.commands.policy.EditPolicyCommand;
import seedu.siasa.logic.commands.policy.ListPolicyCommand;
import seedu.siasa.logic.commands.policy.ShowExpiringPolicyCommand;
import seedu.siasa.logic.commands.policy.ShowExpiringPolicySummaryCommand;
import seedu.siasa.logic.commands.policy.SortPolicyCommand;
import seedu.siasa.logic.parser.contact.AddContactCommandParser;
import seedu.siasa.logic.parser.contact.ClearContactPolicyCommandParser;
import seedu.siasa.logic.parser.contact.DeleteContactCommandParser;
import seedu.siasa.logic.parser.contact.EditContactCommandParser;
import seedu.siasa.logic.parser.contact.FindCommandParser;
import seedu.siasa.logic.parser.contact.ListContactPolicyCommandParser;
import seedu.siasa.logic.parser.contact.SortContactCommandParser;
import seedu.siasa.logic.parser.exceptions.ParseException;
import seedu.siasa.logic.parser.policy.AddPolicyCommandParser;
import seedu.siasa.logic.parser.policy.DeletePolicyCommandParser;
import seedu.siasa.logic.parser.policy.EditPolicyCommandParser;
import seedu.siasa.logic.parser.policy.SortPolicyCommandParser;

/**
 * Parses user input.
 */
public class SiasaParser {

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

        case AddContactCommand.COMMAND_WORD:
            return new AddContactCommandParser().parse(arguments);

        case AddPolicyCommand.COMMAND_WORD:
            return new AddPolicyCommandParser().parse(arguments);

        case EditContactCommand.COMMAND_WORD:
            return new EditContactCommandParser().parse(arguments);

        case EditPolicyCommand.COMMAND_WORD:
            return new EditPolicyCommandParser().parse(arguments);

        case DeleteContactCommand.COMMAND_WORD:
            return new DeleteContactCommandParser().parse(arguments);

        case DeletePolicyCommand.COMMAND_WORD:
            return new DeletePolicyCommandParser().parse(arguments);

        case ClearContactPolicyCommand.COMMAND_WORD:
            return new ClearContactPolicyCommandParser().parse(arguments);

        case SortContactCommand.COMMAND_WORD:
            return new SortContactCommandParser().parse(arguments);

        case SortPolicyCommand.COMMAND_WORD:
            return new SortPolicyCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindContactCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListContactPolicyCommand.COMMAND_WORD:
            return new ListContactPolicyCommandParser().parse(arguments);

        case ListContactCommand.COMMAND_WORD:
            return new ListContactCommand();

        case ListPolicyCommand.COMMAND_WORD:
            return new ListPolicyCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case DownloadCommand.COMMAND_WORD:
            return new DownloadCommand();

        case ShowExpiringPolicySummaryCommand.COMMAND_WORD:
            return new ShowExpiringPolicySummaryCommand();

        case ShowExpiringPolicyCommand.COMMAND_WORD:
            return new ShowExpiringPolicyCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
