package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.*;
import seedu.address.model.student.Student;

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
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_NAME, PREFIX_STUDENT_PHONE,
                        PREFIX_PARENT_NAME, PREFIX_PARENT_PHONE);

        // Student name is a required fields (student phone, parent name and parent phone are optional)
        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        StudentName studentName = ParserUtil.parseStudentName(argMultimap.getValue(PREFIX_STUDENT_NAME).get());
        Phone studentPhone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_STUDENT_PHONE).get());
        ParentName parentName = ParserUtil.parseParentName(argMultimap.getValue(PREFIX_PARENT_NAME).get());
        Phone parentPhone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PARENT_PHONE).get());
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
