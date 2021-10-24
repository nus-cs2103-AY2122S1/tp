package tutoraid.logic.parser;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.AddStudentCommand;
import tutoraid.logic.commands.AddStudentsToLessonsCommand;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.student.ParentName;
import tutoraid.model.student.PaymentStatus;
import tutoraid.model.student.Phone;
import tutoraid.model.student.Progress;
import tutoraid.model.student.ProgressList;
import tutoraid.model.student.Student;
import tutoraid.model.student.StudentName;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON;


/**
 * Parses input arguments and creates a new PaidCommand object
 */
public class AddStudentsToLessonsCommandParser implements Parser<AddStudentsToLessonsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of AddProgressCommand
     * and returns an AddProgressCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentsToLessonsCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT, PREFIX_LESSON);

        // Student name is a required fields (student phone, parent name and parent phone are optional)
        if (argMultimap.getValue(PREFIX_STUDENT).isEmpty()
                || argMultimap.getValue(PREFIX_LESSON).isEmpty()
                || !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddStudentsToLessonsCommand.MESSAGE_USAGE));
        }

        ArrayList<Index> studentIndexes = ParserUtil.parseMultipleIndexes(
                argMultimap.getValue(PREFIX_STUDENT).get());
        ArrayList<Index> lessonIndexes = ParserUtil.parseMultipleIndexes(
                argMultimap.getValue(PREFIX_LESSON).get());

        return new AddStudentsToLessonsCommand(studentIndexes, lessonIndexes);
    }
}
