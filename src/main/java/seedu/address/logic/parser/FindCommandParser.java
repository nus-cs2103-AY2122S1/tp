package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL_OF_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.EmploymentTypeContainsKeywordsPredicate;
import seedu.address.model.person.ExpectedSalary;
import seedu.address.model.person.ExpectedSalaryWithinRangePredicate;
import seedu.address.model.person.ExperienceContainsKeywordsPredicate;
import seedu.address.model.person.LevelOfEducationContainsKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.Role;
import seedu.address.model.person.RoleContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {

        // One whitespace required before first prefix.
        String trimmedArgs = " " + args.trim();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenizeWithoutPreamble(trimmedArgs, PREFIX_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE, PREFIX_EMPLOYMENT_TYPE,
                        PREFIX_EXPECTED_SALARY, PREFIX_LEVEL_OF_EDUCATION,
                        PREFIX_EXPERIENCE, PREFIX_TAG);

        // If find command has no prefix, it is invalid
        if (argMultimap.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        FindDescriptor findDescriptor = new FindDescriptor(argMultimap);

        return new FindCommand(findDescriptor.getPredicates());
    }

    public static class FindDescriptor {
        private ArrayList<Predicate<Person>> predicateList = new ArrayList<>();

        /**
         * Constructs a FindDescriptor.
         * FindDescriptors extracts user input for each Prefix and converts them into a list of Predicates.
         */
        FindDescriptor(ArgumentMultimap argMultimap) throws ParseException {

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_NAME).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    for (String keyword : keywords) {
                        if (!Name.isValidName(keyword)) {
                            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
                        }
                    }
                    predicateList.add(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }

            if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_PHONE).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    for (String keyword : keywords) {
                        if (!Phone.isValidPhone(keyword)) {
                            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
                        }
                    }
                    predicateList.add(new PhoneContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }

            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_EMAIL).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    for (String keyword : keywords) {
                        if (!Email.isValidEmail(keyword)) {
                            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
                        }
                    }
                    predicateList.add(new EmailContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }

            if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_ROLE).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    for (String keyword : keywords) {
                        if (!Role.isValidRole(keyword)) {
                            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
                        }
                    }
                    predicateList.add(new RoleContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }

            if (argMultimap.getValue(PREFIX_EMPLOYMENT_TYPE).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_EMPLOYMENT_TYPE).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    List<String> keywords = new ArrayList<>();
                    Pattern r = Pattern.compile(EmploymentType.Type.getRegex());
                    Matcher m = r.matcher(trimmedArg);
                    while (m.find()) {
                        keywords.add(m.group());
                    }
                    predicateList.add(new EmploymentTypeContainsKeywordsPredicate(keywords));
                }
            }

            if (argMultimap.getValue(PREFIX_EXPECTED_SALARY).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_EXPECTED_SALARY).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    for (String keyword : keywords) {
                        if (!ExpectedSalary.isValidExpectedSalary(keyword)) {
                            throw new ParseException(ExpectedSalary.MESSAGE_CONSTRAINTS);
                        }
                    }
                    predicateList.add(new ExpectedSalaryWithinRangePredicate(Arrays.asList(keywords)));
                }
            }

            if (argMultimap.getValue(PREFIX_LEVEL_OF_EDUCATION).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_LEVEL_OF_EDUCATION).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    predicateList.add(new LevelOfEducationContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }

            if (argMultimap.getValue(PREFIX_EXPERIENCE).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_EXPERIENCE).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    predicateList.add(new ExperienceContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }

            if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_TAG).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    for (String keyword : keywords) {
                        if (!Tag.isValidTagName(keyword)) {
                            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
                        }
                    }
                    predicateList.add(new TagContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }
        }

        public ArrayList<Predicate<Person>> getPredicates() {
            return predicateList;
        }

        private static String[] splitByWhiteSpace(String arg) {
            return arg.split("\\s+");
        }

    }
}
