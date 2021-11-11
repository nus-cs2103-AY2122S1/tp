package tutoraid.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tutoraid.commons.core.Messages;
import tutoraid.logic.commands.AddCommand;
import tutoraid.logic.commands.AddLessonCommand;
import tutoraid.logic.commands.AddProgressCommand;
import tutoraid.logic.commands.AddStudentCommand;
import tutoraid.logic.commands.AddStudentToLessonCommand;
import tutoraid.logic.parser.exceptions.ParseException;

/**
 * Checks if a given add command is to add a student/lesson to TutorAid or to add a progress note to a student
 * or to add students to lessons.
 */
public class AddCommandParser implements Parser<AddCommand> {
    /**
     * Used for initial separation of command flag ('-s', '-l', '-p', or '-sl') and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandFlag>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into a specific add command for execution.
     *
     * @param userInput user input string after the 'add' keyword has been removed
     * @return the specific add command (add student or add progress) based on the user input
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public AddCommand parse(String userInput) throws ParseException {
        final Matcher matcher;
        final String commandFlag;
        final String arguments;

        matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(Messages.MESSAGE_INVALID_ADD_COMMAND);
        }
        commandFlag = matcher.group("commandFlag");
        arguments = matcher.group("arguments");

        switch (commandFlag) {

        case AddStudentCommand.COMMAND_FLAG:
            return new AddStudentCommandParser().parse(arguments);

        case AddProgressCommand.COMMAND_FLAG:
            return new AddProgressCommandParser().parse(arguments);

        case AddStudentToLessonCommand.COMMAND_FLAG:
            return new AddStudentToLessonCommandParser().parse(arguments);

        case AddLessonCommand.COMMAND_FLAG:
            return new AddLessonCommandParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_INVALID_ADD_COMMAND);
        }
    }
}
