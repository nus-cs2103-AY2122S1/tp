package tutoraid.logic.parser;

import java.util.stream.Stream;

import tutoraid.logic.commands.AddStudentCommand;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.student.Student;
import tutoraid.commons.core.Messages;
import tutoraid.model.student.*;

/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_STUDENT_NAME, CliSyntax.PREFIX_STUDENT_PHONE,
                        CliSyntax.PREFIX_PARENT_NAME, CliSyntax.PREFIX_PARENT_PHONE);

        // Student name is a required fields (student phone, parent name and parent phone are optional)
        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_STUDENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        StudentName studentName = ParserUtil.parseStudentName(argMultimap.getValue(CliSyntax.PREFIX_STUDENT_NAME).get());
        Phone studentPhone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_STUDENT_PHONE).get());
        ParentName parentName = ParserUtil.parseParentName(argMultimap.getValue(CliSyntax.PREFIX_PARENT_NAME).get());
        Phone parentPhone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PARENT_PHONE).get());
        Progress progress = new Progress("No Progress");
        PaymentStatus paymentStatus = new PaymentStatus(false);

        Student student = new Student(studentName, studentPhone, parentName, parentPhone, progress, paymentStatus);

        return new AddStudentCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
