package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELE_HANDLE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.student.Email;
import seedu.address.model.module.student.Name;
import seedu.address.model.module.student.Student;
import seedu.address.model.module.student.StudentId;
import seedu.address.model.module.student.TeleHandle;

/**
 * Parses input arguments and creates a new AddStudentCommand object.
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {
    /**
     * Parses {@code String} into a command and returns it.
     *
     * @param args Args for adding a student.
     * @return AddStudentCommand object created from the user input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE_NAME, PREFIX_STUDENT_ID, PREFIX_NAME, PREFIX_TELE_HANDLE,
                        PREFIX_EMAIL);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_NAME, PREFIX_STUDENT_ID, PREFIX_NAME, PREFIX_TELE_HANDLE,
                PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }
        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        TeleHandle teleHandle = ParserUtil.parseTeleHandle(argMultimap.getValue(PREFIX_TELE_HANDLE).get());
        ModuleName moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_MODULE_NAME).get());

        Student student = new Student(studentId, name, teleHandle, email);

        return new AddStudentCommand(student, moduleName);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
