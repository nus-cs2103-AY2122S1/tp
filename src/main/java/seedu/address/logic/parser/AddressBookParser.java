package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.member.MdelCommand;
import seedu.address.logic.commands.event.EaddCommand;
import seedu.address.logic.commands.event.EdelCommand;
import seedu.address.logic.commands.member.MeditCommand;
import seedu.address.logic.commands.event.ElistCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.member.MfindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.member.MlistCommand;
import seedu.address.logic.commands.member.MaddCommand;
import seedu.address.logic.commands.task.TaddCommand;
import seedu.address.logic.commands.task.TdelCommand;
import seedu.address.logic.commands.task.TlistCommand;
import seedu.address.logic.parser.event.EaddCommandParser;
import seedu.address.logic.parser.event.EdelCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.member.*;
import seedu.address.logic.parser.task.TaddCommandParser;
import seedu.address.logic.parser.task.TdelCommandParser;
import seedu.address.logic.parser.task.TlistCommandParser;

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

        case MaddCommand.COMMAND_WORD:
            return new MaddCommandParser().parse(arguments);

        case MeditCommand.COMMAND_WORD:
            return new MeditCommandParser().parse(arguments);

        case MdelCommand.COMMAND_WORD:
            return new MdelCommandParser().parse(arguments);

        case MfindCommand.COMMAND_WORD:
            return new MfindCommandParser().parse(arguments);

        case MlistCommand.COMMAND_WORD:
            return new MlistCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case EaddCommand.COMMAND_WORD:
            return new EaddCommandParser().parse(arguments);

        case EdelCommand.COMMAND_WORD:
            return new EdelCommandParser().parse(arguments);

        case ElistCommand.COMMAND_WORD:
            return new ElistCommand();

        case TaddCommand.COMMAND_WORD:
            return new TaddCommandParser().parse(arguments);

        case TdelCommand.COMMAND_WORD:
            return new TdelCommandParser().parse(arguments);

        case TlistCommand.COMMAND_WORD:
            return new TlistCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
