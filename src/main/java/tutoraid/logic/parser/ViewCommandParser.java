package tutoraid.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tutoraid.commons.core.Messages;
import tutoraid.logic.commands.HelpCommand;
import tutoraid.logic.commands.ViewCommand;
import tutoraid.logic.commands.ViewLessonCommand;
import tutoraid.logic.commands.ViewStudentCommand;
import tutoraid.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {
    /**
     * Used for initial separation of command flag ('-s' or '-l') and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandFlag>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into a specific view command for execution.
     * @param userInput user input string after the 'view' keyword has been removed
     * @return the specific view command (view student or view lesson) based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ViewCommand parse(String userInput) throws ParseException {
        final Matcher matcher;
        final String commandFlag;
        final String arguments;

        matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_VIEW_COMMAND, HelpCommand.MESSAGE_USAGE));
        }
        commandFlag = matcher.group("commandFlag");
        arguments = matcher.group("arguments");

        switch (commandFlag) {

        case ViewStudentCommand.COMMAND_FLAG:
            return new ViewStudentCommandParser().parse(arguments);

        case ViewLessonCommand.COMMAND_FLAG:
            return new ViewLessonCommandParser().parse(arguments);

        default:
            throw new ParseException(Messages.MESSAGE_INVALID_VIEW_COMMAND);
        }
    }
}

