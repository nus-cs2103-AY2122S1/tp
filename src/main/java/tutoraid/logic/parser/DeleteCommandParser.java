package tutoraid.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tutoraid.commons.core.Messages;
import tutoraid.logic.commands.DeleteCommand;
import tutoraid.logic.commands.DeleteProgressCommand;
import tutoraid.logic.commands.DeleteStudentCommand;
import tutoraid.logic.commands.HelpCommand;
import tutoraid.logic.parser.exceptions.ParseException;

/**
 * Checks if a given delete command is to delete a student from TutorAid or to delete the progress note of a student.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    public static final String COMMAND_WORD = "del";

    /**
     * Used for initial separation of command flag ('-s' or '-p') and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandFlag>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into a specific delete command for execution.
     * @param userInput user input string after the 'del' keyword has been removed
     * @return the specific add command (delete student or delete progress) based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteCommand parse(String userInput) throws ParseException {
        final Matcher matcher;
        final String commandFlag;
        final String arguments;

        matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_DELETE_COMMAND, HelpCommand.MESSAGE_USAGE));
        }
        commandFlag = matcher.group("commandFlag");
        arguments = matcher.group("arguments");

        switch (commandFlag) {

        case DeleteStudentCommand.COMMAND_FLAG:
            return new DeleteStudentCommandParser().parse(arguments);

        case DeleteProgressCommand.COMMAND_FLAG:
            return new DeleteProgressCommandParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_INVALID_DELETE_COMMAND);
        }
    }
}
