package seedu.notor.logic.parser;

import static seedu.notor.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.notor.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.notor.logic.commands.ClearCommand;
import seedu.notor.logic.commands.Command;
import seedu.notor.logic.commands.ExitCommand;
import seedu.notor.logic.commands.FindCommand;
import seedu.notor.logic.commands.GroupCreateCommand;
import seedu.notor.logic.commands.HelpCommand;
import seedu.notor.logic.commands.ListCommand;
import seedu.notor.logic.commands.PersonAddGroupCommand;
import seedu.notor.logic.commands.PersonAddSubGroupCommand;
import seedu.notor.logic.commands.PersonRemoveGroupCommand;
import seedu.notor.logic.commands.PersonRemoveSubGroupCommand;
import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.commands.person.PersonDeleteCommand;
import seedu.notor.logic.commands.person.PersonEditCommand;
import seedu.notor.logic.commands.person.PersonNoteCommand;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.logic.parser.person.PersonCreateCommandParser;
import seedu.notor.logic.parser.person.PersonDeleteCommandParser;
import seedu.notor.logic.parser.person.PersonEditCommandParser;
import seedu.notor.logic.parser.person.PersonNoteCommandParser;

/**
 * Parses user input.
 */
public class NotorParser {
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

        case PersonCreateCommand.COMMAND_WORD:
            return new PersonCreateCommandParser().parse(arguments);

        case PersonEditCommand.COMMAND_WORD:
            return new PersonEditCommandParser().parse(arguments);

        case PersonDeleteCommand.COMMAND_WORD:
            return new PersonDeleteCommandParser().parse(arguments);

        case PersonNoteCommand.COMMAND_WORD:
            return new PersonNoteCommandParser().parse(arguments);

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

        case GroupCreateCommand.COMMAND_WORD:
            return new GroupCreateCommandParser().parse(arguments);

        case PersonAddGroupCommand.COMMAND_WORD:
            return new PersonAddGroupCommandParser().parse(arguments);

        case PersonAddSubGroupCommand.COMMAND_WORD:
            return new PersonAddSubGroupCommandParser().parse(arguments);

        case PersonRemoveGroupCommand.COMMAND_WORD:
            return new PersonRemoveGroupCommandParser().parse(arguments);

        case PersonRemoveSubGroupCommand.COMMAND_WORD:
            return new PersonRemoveSubGroupCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
