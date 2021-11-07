package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddPersonToEventCommand;
import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.ChainCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DetailsCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EventCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemovePersonFromEventCommand;
import seedu.address.logic.commands.RenameEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern ADVANCED_COMMAND_FORMAT = Pattern.compile("(?<leftCommandString>.*)"
            + " +&& +(?<rightCommandString>.*)");
    private final HashMap<String, String> aliasMap = new HashMap<>();
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher basicMatcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        final Matcher advancedMatcher = ADVANCED_COMMAND_FORMAT.matcher(userInput.trim());

        if (advancedMatcher.matches()) {
            final String leftCommandString = advancedMatcher.group("leftCommandString");
            final String rightCommandString = advancedMatcher.group("rightCommandString");
            Command leftCommand = this.parseCommand(leftCommandString);
            Command rightCommand = this.parseCommand(rightCommandString);
            return new ChainCommand(leftCommand, rightCommand);
        }

        if (!basicMatcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = basicMatcher.group("commandWord");
        final String arguments = basicMatcher.group("arguments");

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AddPersonToEventCommand.COMMAND_WORD:
            return new AddPersonToEventCommandParser().parse(arguments);

        case RemovePersonFromEventCommand.COMMAND_WORD:
            return new RemovePersonFromEventCommandParser().parse(arguments);

        case EventCommand.COMMAND_WORD:
            return new EventCommandParser().parse(arguments);

        case RenameEventCommand.COMMAND_WORD:
            return new RenameEventCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DetailsCommand.COMMAND_WORD:
            return new DetailsCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AliasCommand.COMMAND_WORD:
            return new AliasCommandParser().parse(arguments).setMap(aliasMap);

        default:
            if (aliasMap.containsKey(commandWord)) {
                return parseCommand(aliasMap.get(commandWord) + " " + arguments);
            }
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
