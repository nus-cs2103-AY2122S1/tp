package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL_OF_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.Interview;
import seedu.address.model.notes.Notes;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.ExpectedSalary;
import seedu.address.model.person.Experience;
import seedu.address.model.person.LevelOfEducation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static final String EMPTY = "";

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @param args {@code String} of arguments to be parsed
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE,
                        PREFIX_EMPLOYMENT_TYPE, PREFIX_EXPECTED_SALARY,
                        PREFIX_LEVEL_OF_EDUCATION, PREFIX_EXPERIENCE, PREFIX_TAG,
                        PREFIX_INTERVIEW, PREFIX_NOTES);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ROLE, PREFIX_EMPLOYMENT_TYPE,
                PREFIX_EXPECTED_SALARY, PREFIX_LEVEL_OF_EDUCATION, PREFIX_EXPERIENCE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());
        EmploymentType employmentType = ParserUtil.parseEmploymentType(
                argMultimap.getValue(PREFIX_EMPLOYMENT_TYPE).get());
        ExpectedSalary expectedSalary = ParserUtil.parseExpectedSalary(argMultimap
                .getValue(PREFIX_EXPECTED_SALARY).get());
        LevelOfEducation levelOfEducation = ParserUtil.parseLevelOfEducation(
                argMultimap.getValue(PREFIX_LEVEL_OF_EDUCATION).get());
        Experience experience = ParserUtil.parseExperience(argMultimap.getValue(PREFIX_EXPERIENCE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Optional<Interview> interview = ParserUtil.parseInterview(
                argMultimap.getValue(PREFIX_INTERVIEW).orElse(EMPTY));
        Optional<Notes> notes = ParserUtil.parseNotes(argMultimap.getValue(PREFIX_NOTES).orElse(EMPTY));

        Person person = new Person(name, phone, email, role, employmentType,
                expectedSalary, levelOfEducation, experience, tagList, interview, notes);
        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap {@code ArgumentMultimap} to be checked for prefixes
     * @param prefixes prefixes to be checked for in {@code argumentMultimap}
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
