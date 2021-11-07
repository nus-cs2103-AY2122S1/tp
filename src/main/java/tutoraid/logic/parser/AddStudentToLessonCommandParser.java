package tutoraid.logic.parser;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT;

import java.util.ArrayList;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.AddStudentToLessonCommand;
import tutoraid.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddStudentToLessonCommand object
 */
public class AddStudentToLessonCommandParser implements Parser<AddStudentToLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of AddStudentToLessonCommand
     * and returns an AddStudentToLessonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentToLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT, PREFIX_LESSON);

        // Must specify student indexes and lesson indexes so these students can be added to these lessons
        if (argMultimap.getValue(PREFIX_STUDENT).isEmpty()
                || argMultimap.getValue(PREFIX_LESSON).isEmpty()
                || !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddStudentToLessonCommand.MESSAGE_USAGE));
        }

        ArrayList<Index> studentIndexes = ParserUtil.parseMultipleIndexes(
                argMultimap.getValue(PREFIX_STUDENT).get());
        ArrayList<Index> lessonIndexes = ParserUtil.parseMultipleIndexes(
                argMultimap.getValue(PREFIX_LESSON).get());

        return new AddStudentToLessonCommand(studentIndexes, lessonIndexes);
    }
}
