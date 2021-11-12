package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.teachbook.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Set;

import seedu.teachbook.logic.commands.AddCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.student.Address;
import seedu.teachbook.model.student.Attendance;
import seedu.teachbook.model.student.Email;
import seedu.teachbook.model.student.Name;
import seedu.teachbook.model.student.Phone;
import seedu.teachbook.model.student.Remark;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object.
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand and returns an AddCommand object
     * for execution.
     *
     * @param args input arguments to be parsed.
     * @return {@code AddCommand} as a result of the parsing of the input arguments.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhoneForAdd(argMultimap.getValue(PREFIX_PHONE));
        Email email = ParserUtil.parseEmailForAdd(argMultimap.getValue(PREFIX_EMAIL));
        Address address = ParserUtil.parseAddressForAdd(argMultimap.getValue(PREFIX_ADDRESS));
        Remark remark = new Remark(""); // add command does not allow adding of remark straight away
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Attendance attendance = Attendance.getDefaultAttendance();
        Grade grade = new Grade(""); // add command does not allow adding of grade straight away

        Student student = new Student(name, phone, email, address, remark, tagList, attendance, grade);

        return new AddCommand(student);
    }

}
