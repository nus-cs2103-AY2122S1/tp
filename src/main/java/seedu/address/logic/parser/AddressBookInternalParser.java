package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INTERNAL_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_INTERNAL_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AccessCacheCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ViewTaskListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments.
 */
public class AddressBookInternalParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses internal command string into command for execution.
     *
     * @param internalCommandString full internal command string
     * @return the command based on the command string
     * @throws ParseException if the internal command string does not conform the expected format
     */
    public Command parseCommand(String internalCommandString) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(internalCommandString.trim());
        if (!matcher.matches()) {
            throw new ParseException(MESSAGE_INVALID_INTERNAL_COMMAND_FORMAT);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Coded as switch to ensure ease expansion
        switch (commandWord) {

        case AccessCacheCommand.COMMAND_WORD:
            return new AccessCacheCommandParser().parse(arguments);

        // Handle when user clicks UI instead of typing in
        case ViewTaskListCommand.COMMAND_WORD:
            return new ViewTaskListCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_INTERNAL_COMMAND);
        }
    }
}
