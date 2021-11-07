package tutoraid.logic.parser;

import static tutoraid.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutoraid.commons.core.Messages.MESSAGE_INVALID_FIND_COMMAND;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tutoraid.commons.core.Messages;
import tutoraid.logic.commands.FindCommand;
import tutoraid.logic.commands.FindLessonCommand;
import tutoraid.logic.commands.FindStudentCommand;
import tutoraid.logic.commands.HelpCommand;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.lesson.LessonNameContainsSubstringsPredicate;
import tutoraid.model.student.NameContainsSubstringsPredicate;

/**
 * Checks if a given find command is to find a student or to find a lesson in TutorAid.
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Used for initial separation of command flag ('-s' or '-p') and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandFlag>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the FindStudentCommand
     * and returns a FindStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String userInput) throws ParseException {
        final Matcher matcher;
        final String commandFlag;
        final String arguments;

        matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(Messages.MESSAGE_INVALID_FIND_COMMAND);
        }
        commandFlag = matcher.group("commandFlag");
        arguments = matcher.group("arguments");

        String trimmedArgs = arguments.trim();
        if (trimmedArgs.isEmpty()) {
            if (commandFlag.equals(FindStudentCommand.COMMAND_FLAG)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStudentCommand.MESSAGE_USAGE));
            } else if (commandFlag.equals(FindLessonCommand.COMMAND_FLAG)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindLessonCommand.MESSAGE_USAGE));
            } else {
                throw new ParseException(MESSAGE_INVALID_FIND_COMMAND);
            }
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        switch (commandFlag) {

        case FindStudentCommand.COMMAND_FLAG:
            return new FindStudentCommand(new NameContainsSubstringsPredicate(Arrays.asList(nameKeywords)));

        case FindLessonCommand.COMMAND_FLAG:
            return new FindLessonCommand(new LessonNameContainsSubstringsPredicate(Arrays.asList(nameKeywords)));

        default:
            throw new ParseException(Messages.MESSAGE_INVALID_FIND_COMMAND);
        }
    }

}
