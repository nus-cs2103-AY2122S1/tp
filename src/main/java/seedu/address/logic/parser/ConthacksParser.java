package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.modulelesson.ClearModuleLessonCommand;
import seedu.address.logic.commands.modulelesson.ListModuleLessonCommand;
import seedu.address.logic.commands.person.ClearPersonCommand;
import seedu.address.logic.commands.person.ListPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.modulelesson.AddModuleLessonCommandParser;
import seedu.address.logic.parser.modulelesson.DeleteModuleLessonCommandParser;
import seedu.address.logic.parser.modulelesson.EditModuleLessonCommandParser;
import seedu.address.logic.parser.modulelesson.FindModuleLessonCommandParser;
import seedu.address.logic.parser.person.AddPersonCommandParser;
import seedu.address.logic.parser.person.DeletePersonCommandParser;
import seedu.address.logic.parser.person.EditPersonCommandParser;
import seedu.address.logic.parser.person.FindPersonCommandParser;

/**
 * Parses user input.
 */
public class ConthacksParser {

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
        switch (CommandWord.getCommandType(commandWord)) {

        case ADD_PERSON:
            return new AddPersonCommandParser().parse(arguments);

        case EDIT_PERSON:
            return new EditPersonCommandParser().parse(arguments);

        case DELETE_PERSON:
            return new DeletePersonCommandParser().parse(arguments);

        case CLEAR_PERSON:
            return new ClearPersonCommand();

        case FIND_PERSON:
            return new FindPersonCommandParser().parse(arguments);

        case LIST_PERSON:
            return new ListPersonCommand();

        case LIST_MODULE_LESSON:
            return new ListModuleLessonCommand();

        case EDIT_MODULE_LESSON:
            return new EditModuleLessonCommandParser().parse(arguments);

        case FIND_MODULE_LESSON:
            return new FindModuleLessonCommandParser().parse(arguments);

        case DELETE_MODULE_LESSON:
            return new DeleteModuleLessonCommandParser().parse(arguments);

        case CLEAR_MODULE_LESSON:
            return new ClearModuleLessonCommand();

        case EXIT:
            return new ExitCommand();

        case HELP:
            return new HelpCommand();

        case ADD_MODULE_LESSON:
            return new AddModuleLessonCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
