package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.RepoName;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;
import seedu.address.model.student.UserName;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentCommand
     * and returns an AddStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EMAIL, PREFIX_STUDENTNUMBER, PREFIX_USERNAME,
                        PREFIX_REPO, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EMAIL, PREFIX_STUDENTNUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        StudentNumber studentNumber = ParserUtil.parseStudentNumber(argMultimap.getValue(PREFIX_STUDENTNUMBER).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        UserName userName = null;
        RepoName repoName = null;

        if (argMultimap.getValue(PREFIX_USERNAME).isPresent()) {
            userName = ParserUtil.parseUserName(argMultimap.getValue(PREFIX_USERNAME).get());
        }

        if (argMultimap.getValue(PREFIX_REPO).isPresent()) {
            repoName = ParserUtil.parseRepo(argMultimap.getValue(PREFIX_REPO).get());
        }

        Student student = new Student(name, email, studentNumber, userName, repoName, tagList);

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
