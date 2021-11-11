package nustracker.logic.parser;

import static nustracker.commons.core.Messages.MESSAGE_COMMAND_EXTRANEOUS_SLASHES;
import static nustracker.logic.parser.CliSyntax.PREFIX_EMAIL;
import static nustracker.logic.parser.CliSyntax.PREFIX_MAJOR;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_PHONE;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static nustracker.logic.parser.CliSyntax.PREFIX_YEAR;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.AddCommand;
import nustracker.logic.parser.exceptions.ExtraSlashException;
import nustracker.logic.parser.exceptions.ParseException;
import nustracker.model.student.Email;
import nustracker.model.student.EnrolledEvents;
import nustracker.model.student.Major;
import nustracker.model.student.Name;
import nustracker.model.student.Phone;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.model.student.Year;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap;

        try {
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                            PREFIX_YEAR, PREFIX_MAJOR, PREFIX_STUDENTID);
        } catch (ExtraSlashException e) {
            throw new ParseException(String.format(MESSAGE_COMMAND_EXTRANEOUS_SLASHES, AddCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.arePrefixesPresent(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_YEAR, PREFIX_MAJOR, PREFIX_STUDENTID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Major major = ParserUtil.parseMajor(argMultimap.getValue(PREFIX_MAJOR).get());
        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get());
        Year year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());

        EnrolledEvents enrolledEvents = new EnrolledEvents();

        Student student = new Student(name, phone, email, year, major, studentId, enrolledEvents);

        return new AddCommand(student);
    }



}
