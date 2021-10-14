package tutoraid.logic.parser;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;

import tutoraid.commons.core.Messages;
import tutoraid.logic.commands.AddStudentCommand;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.student.ParentName;
import tutoraid.model.student.PaymentStatus;
import tutoraid.model.student.Phone;
import tutoraid.model.student.Progress;
import tutoraid.model.student.Student;
import tutoraid.model.student.StudentName;


/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_NAME, PREFIX_STUDENT_PHONE,
                        PREFIX_PARENT_NAME, PREFIX_PARENT_PHONE);

        // Student name is a required fields (student phone, parent name and parent phone are optional)
        if (argMultimap.getValue(PREFIX_STUDENT_NAME).isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        StudentName studentName = ParserUtil.parseStudentName(
                argMultimap.getValue(PREFIX_STUDENT_NAME).get());
        Phone studentPhone = ParserUtil.parsePhone(
                argMultimap.getValue(PREFIX_STUDENT_PHONE).orElse(""));
        ParentName parentName = ParserUtil.parseParentName(
                argMultimap.getValue(PREFIX_PARENT_NAME).orElse(""));
        Phone parentPhone = ParserUtil.parsePhone(
                argMultimap.getValue(PREFIX_PARENT_PHONE).orElse(""));
        Progress progress = new Progress("No Progress");
        PaymentStatus paymentStatus = new PaymentStatus(false);

        Student student = new Student(studentName, studentPhone, parentName, parentPhone, progress, paymentStatus);

        return new AddStudentCommand(student);
    }
}
