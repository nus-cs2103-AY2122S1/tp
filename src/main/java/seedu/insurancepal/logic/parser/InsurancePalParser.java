package seedu.insurancepal.logic.parser;

import static seedu.insurancepal.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.insurancepal.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.insurancepal.logic.commands.AddCommand;
import seedu.insurancepal.logic.commands.ClaimCommand;
import seedu.insurancepal.logic.commands.ClearCommand;
import seedu.insurancepal.logic.commands.Command;
import seedu.insurancepal.logic.commands.DeleteCommand;
import seedu.insurancepal.logic.commands.EditCommand;
import seedu.insurancepal.logic.commands.ExitCommand;
import seedu.insurancepal.logic.commands.FindCommand;
import seedu.insurancepal.logic.commands.HelpCommand;
import seedu.insurancepal.logic.commands.ListCommand;
import seedu.insurancepal.logic.commands.NoteCommand;
import seedu.insurancepal.logic.commands.RevenueCommand;
import seedu.insurancepal.logic.commands.ScheduleCommand;
import seedu.insurancepal.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class InsurancePalParser {

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

        case RevenueCommand.COMMAND_WORD:
            return new RevenueCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ScheduleCommand.COMMAND_WORD:
            return new ScheduleCommandParser().parse(arguments);

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

        case ClaimCommand.COMMAND_WORD:
            return new ClaimCommandParser().parse(arguments);

        case NoteCommand.COMMAND_WORD:
            return new NoteCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
