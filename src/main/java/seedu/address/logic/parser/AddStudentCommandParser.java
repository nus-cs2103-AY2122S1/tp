package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ParentName;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
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

        Person person = new Person(studentName, studentPhone, parentName, parentPhone);

        return new AddStudentCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
