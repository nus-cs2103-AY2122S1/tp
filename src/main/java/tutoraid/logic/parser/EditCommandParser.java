package tutoraid.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tutoraid.commons.core.Messages;
import tutoraid.logic.commands.EditCommand;
import tutoraid.logic.commands.EditLessonCommand;
import tutoraid.logic.commands.EditStudentCommand;
import tutoraid.logic.parser.exceptions.ParseException;

/**
 * Checks if a given edit command is to edit a student or lesson.
 */
public class EditCommandParser implements Parser<EditCommand> {
    /**
     * Used for initial separation of command flag ('-s' or '-l') and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandFlag>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into a specific edit command for execution.
     *
     * @param userInput user input string after the 'edit' keyword has been removed
     * @return the specific edit command (edit student or edit lesson) based on the user input
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public EditCommand parse(String userInput) throws ParseException {
        final Matcher matcher;
        final String commandFlag;
        final String arguments;

        matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(Messages.MESSAGE_INVALID_EDIT_COMMAND);
        }
        commandFlag = matcher.group("commandFlag");
        arguments = matcher.group("arguments");

        switch (commandFlag) {

        case EditStudentCommand.COMMAND_FLAG:
            return new EditStudentCommandParser().parse(arguments);

        case EditLessonCommand.COMMAND_FLAG:
            return new EditLessonCommandParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_INVALID_EDIT_COMMAND);
        }
    }
}
