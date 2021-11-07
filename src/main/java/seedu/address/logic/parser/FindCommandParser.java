package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DONE;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.done.Done;
import seedu.address.model.done.DoneContainsKeywordsPredicate;
import seedu.address.model.interview.InterviewContainsKeywordsPredicate;
import seedu.address.model.notes.NotesContainsKeywordsPredicate;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.EmploymentTypeContainsKeywordsPredicate;
import seedu.address.model.person.ExpectedSalary;
import seedu.address.model.person.ExpectedSalaryWithinRangePredicate;
import seedu.address.model.person.Experience;
import seedu.address.model.person.ExperienceContainsKeywordsPredicate;
import seedu.address.model.person.LevelOfEducation;
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
                        PREFIX_EXPECTED_SALARY, PREFIX_LEVEL_OF_EDUCATION, PREFIX_EXPERIENCE,
                        PREFIX_TAG, PREFIX_INTERVIEW, PREFIX_NOTES, PREFIX_DONE);

        // If find command has no prefix, it is invalid
        if (argMultimap.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        FindDescriptor findDescriptor = new FindDescriptor(argMultimap);

        return new FindCommand(findDescriptor.getPredicates());
    }

    /**
     * Parses keywords for each prefix into a list of predicates to be used for filtering.
     */
    public static class FindDescriptor {
        private ArrayList<Predicate<Person>> predicateList = new ArrayList<>();

        /**
         * Constructs a FindDescriptor.
         * FindDescriptors extracts user input for each Prefix and converts them into a list of Predicates.
         */
        FindDescriptor(ArgumentMultimap argMultimap) throws ParseException {

            assert !argMultimap.isEmpty() : "FindDescriptor should not be created with empty ArgumentMultimap";

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                addToPredicateList(extractNamePrefixInput(argMultimap));
            }

            if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
                addToPredicateList(extractPhonePrefixInput(argMultimap));
            }

            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                addToPredicateList(extractEmailPrefixInput(argMultimap));
            }

            if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
                addToPredicateList(extractRolePrefixInput(argMultimap));
            }

            if (argMultimap.getValue(PREFIX_EMPLOYMENT_TYPE).isPresent()) {
                addToPredicateList(extractEmploymentTypePrefixInput(argMultimap));
            }

            if (argMultimap.getValue(PREFIX_EXPECTED_SALARY).isPresent()) {
                addToPredicateList(extractExpectedSalaryPrefixInput(argMultimap));
            }

            if (argMultimap.getValue(PREFIX_LEVEL_OF_EDUCATION).isPresent()) {
                addToPredicateList(extractLevelOfEducationPrefixInput(argMultimap));
            }

            if (argMultimap.getValue(PREFIX_EXPERIENCE).isPresent()) {
                addToPredicateList(extractExperiencePrefixInput(argMultimap));
            }

            if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
                addToPredicateList(extractTagPrefixInput(argMultimap));
            }

            if (argMultimap.getValue(PREFIX_INTERVIEW).isPresent()) {
                addToPredicateList(extractInterviewPrefixInput(argMultimap));
            }

            if (argMultimap.getValue(PREFIX_NOTES).isPresent()) {
                addToPredicateList(extractNotesPrefixInput(argMultimap));
            }

            if (argMultimap.getValue(PREFIX_DONE).isPresent()) {
                addToPredicateList(extractDonePrefixInput(argMultimap));
            }
        }

        private void addToPredicateList(Predicate<Person> predicate) {
            if (predicate != null) {
                this.predicateList.add(predicate);
            }
        }

        private NameContainsKeywordsPredicate extractNamePrefixInput(ArgumentMultimap argMultimap)
                throws ParseException {
            assert argMultimap.getValue(PREFIX_NAME).isPresent() : "No inputs for Prefix Name exist.";
            String arg = argMultimap.getValue(PREFIX_NAME).get();
            String trimmedArg = arg.trim();
            if (!trimmedArg.isEmpty()) {
                String[] keywords = splitByWhiteSpace(trimmedArg);
                for (String keyword : keywords) {
                    if (!Name.isValidName(keyword)) {
                        throw new ParseException(Name.MESSAGE_CONSTRAINTS);
                    }
                }
                return new NameContainsKeywordsPredicate(Arrays.asList(keywords));
            }
            return null;
        }

        private PhoneContainsKeywordsPredicate extractPhonePrefixInput(ArgumentMultimap argMultimap)
                throws ParseException {
            assert argMultimap.getValue(PREFIX_PHONE).isPresent() : "No inputs for Prefix Phone exist.";
            String arg = argMultimap.getValue(PREFIX_PHONE).get();
            String trimmedArg = arg.trim();
            if (!trimmedArg.isEmpty()) {
                String[] keywords = splitByWhiteSpace(trimmedArg);
                for (String keyword : keywords) {
                    if (!Phone.isValidPhone(keyword)) {
                        throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
                    }
                }
                return new PhoneContainsKeywordsPredicate(Arrays.asList(keywords));
            }
            return null;
        }

        private EmailContainsKeywordsPredicate extractEmailPrefixInput(ArgumentMultimap argMultimap)
                throws ParseException {
            assert argMultimap.getValue(PREFIX_EMAIL).isPresent() : "No inputs for Prefix Email exist.";
            String arg = argMultimap.getValue(PREFIX_EMAIL).get();
            String trimmedArg = arg.trim();
            if (!trimmedArg.isEmpty()) {
                String[] keywords = splitByWhiteSpace(trimmedArg);
                for (String keyword : keywords) {
                    if (!Email.isValidEmail(keyword)) {
                        throw new ParseException(Email.MESSAGE_CONSTRAINTS);
                    }
                }
                return new EmailContainsKeywordsPredicate(Arrays.asList(keywords));
            }
            return null;
        }

        private RoleContainsKeywordsPredicate extractRolePrefixInput(ArgumentMultimap argMultimap)
                throws ParseException {
            assert argMultimap.getValue(PREFIX_ROLE).isPresent() : "No inputs for Prefix Role exist.";
            String arg = argMultimap.getValue(PREFIX_ROLE).get();
            String trimmedArg = arg.trim();
            if (!trimmedArg.isEmpty()) {
                String[] keywords = splitByWhiteSpace(trimmedArg);
                for (String keyword : keywords) {
                    if (!Role.isValidRole(keyword)) {
                        throw new ParseException(Role.MESSAGE_CONSTRAINTS);
                    }
                }
                return new RoleContainsKeywordsPredicate(Arrays.asList(keywords));
            }
            return null;
        }

        private EmploymentTypeContainsKeywordsPredicate extractEmploymentTypePrefixInput(ArgumentMultimap argMultimap)
                throws ParseException {
            assert argMultimap.getValue(PREFIX_EMPLOYMENT_TYPE).isPresent()
                    : "No inputs for Prefix Employment Type exists.";
            String arg = argMultimap.getValue(PREFIX_EMPLOYMENT_TYPE).get();
            String trimmedArg = arg.trim();
            if (!trimmedArg.isEmpty()) {
                List<String> keywords = new ArrayList<>();
                Pattern r = Pattern.compile(EmploymentType.Type.getRegex());
                Matcher m = r.matcher(trimmedArg);

                ArrayList<String> terms = EmploymentType.Type.getTerms();

                while (m.find()) {
                    if (!m.group().isEmpty()) {
                        boolean isTermContained = false;
                        for (String term: terms) {
                            if (term.toLowerCase().startsWith(m.group().toLowerCase())) {
                                isTermContained = true;
                                break;
                            }
                        }

                        if (!isTermContained) {
                            throw new ParseException(EmploymentType.FIND_MESSAGE_CONSTRAINTS);
                        } else {
                            keywords.add(m.group());
                        }
                    }
                }
                return new EmploymentTypeContainsKeywordsPredicate(keywords);
            }
            return null;
        }

        private ExpectedSalaryWithinRangePredicate extractExpectedSalaryPrefixInput(ArgumentMultimap argMultimap)
                throws ParseException {
            assert argMultimap.getValue(PREFIX_EXPECTED_SALARY).isPresent()
                    : "No inputs for Prefix Expected Salary exists.";
            String arg = argMultimap.getValue(PREFIX_EXPECTED_SALARY).get();
            String trimmedArg = arg.trim();
            if (!trimmedArg.isEmpty()) {
                String[] keywords = splitByWhiteSpace(trimmedArg);
                for (String keyword : keywords) {
                    if (!ExpectedSalary.isValidExpectedSalary(keyword)) {
                        throw new ParseException(ExpectedSalary.MESSAGE_CONSTRAINTS);
                    }
                }
                return new ExpectedSalaryWithinRangePredicate(Arrays.asList(keywords));
            }
            return null;
        }

        private LevelOfEducationContainsKeywordsPredicate
            extractLevelOfEducationPrefixInput(ArgumentMultimap argMultimap) throws ParseException {
            assert argMultimap.getValue(PREFIX_LEVEL_OF_EDUCATION).isPresent()
                    : "No inputs for Prefix Level Of Education exists.";
            String arg = argMultimap.getValue(PREFIX_LEVEL_OF_EDUCATION).get();
            String trimmedArg = arg.trim();
            if (!trimmedArg.isEmpty()) {
                List<String> keywords = new ArrayList<>();
                Pattern r = Pattern.compile(LevelOfEducation.Education.getRegex());
                Matcher m = r.matcher(trimmedArg);

                ArrayList<String> terms = LevelOfEducation.Education.getEducationLevels();

                while (m.find()) {
                    if (!m.group().isEmpty()) {
                        boolean isTermContained = false;
                        for (String term: terms) {
                            if (term.toLowerCase().startsWith(m.group().toLowerCase())) {
                                isTermContained = true;
                                break;
                            }
                        }

                        if (!isTermContained) {
                            throw new ParseException(LevelOfEducation.FIND_MESSAGE_CONSTRAINTS);
                        } else {
                            keywords.add(m.group());
                        }
                    }
                }
                return new LevelOfEducationContainsKeywordsPredicate(keywords);
            }
            return null;
        }

        private ExperienceContainsKeywordsPredicate extractExperiencePrefixInput(ArgumentMultimap argMultimap)
                throws ParseException {
            assert argMultimap.getValue(PREFIX_EXPERIENCE).isPresent()
                    : "No inputs for Prefix Experience exists.";
            String arg = argMultimap.getValue(PREFIX_EXPERIENCE).get();
            String trimmedArg = arg.trim();
            if (!trimmedArg.isEmpty()) {
                String[] keywords = splitByWhiteSpace(trimmedArg);
                for (String keyword : keywords) {
                    if (!Experience.isValidExperience(keyword)) {
                        throw new ParseException(Experience.MESSAGE_CONSTRAINTS);
                    }
                }
                return new ExperienceContainsKeywordsPredicate(Arrays.asList(keywords));
            }
            return null;
        }

        private TagContainsKeywordsPredicate extractTagPrefixInput(ArgumentMultimap argMultimap)
                throws ParseException {
            assert argMultimap.getValue(PREFIX_TAG).isPresent()
                    : "No inputs for Prefix Tag exists.";
            String arg = argMultimap.getValue(PREFIX_TAG).get();
            String trimmedArg = arg.trim();
            if (!trimmedArg.isEmpty()) {
                String[] keywords = splitByWhiteSpace(trimmedArg);
                for (String keyword : keywords) {
                    if (!Tag.isValidTagName(keyword)) {
                        throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
                    }
                }
                return new TagContainsKeywordsPredicate(Arrays.asList(keywords));
            }
            return null;
        }

        private InterviewContainsKeywordsPredicate extractInterviewPrefixInput(ArgumentMultimap argMultimap) {
            assert argMultimap.getValue(PREFIX_INTERVIEW).isPresent()
                    : "No inputs for Prefix Interview exists.";
            String arg = argMultimap.getValue(PREFIX_INTERVIEW).get();
            String trimmedArg = arg.trim();
            if (!trimmedArg.isEmpty()) {
                String[] keywords = splitByWhiteSpace(trimmedArg);
                return new InterviewContainsKeywordsPredicate(Arrays.asList(keywords));
            }
            return null;
        }

        private NotesContainsKeywordsPredicate extractNotesPrefixInput(ArgumentMultimap argMultimap) {
            assert argMultimap.getValue(PREFIX_NOTES).isPresent()
                    : "No inputs for Prefix Notes exists.";
            String arg = argMultimap.getValue(PREFIX_NOTES).get();
            if (!arg.isEmpty()) {
                return new NotesContainsKeywordsPredicate(arg);
            }
            return null;
        }

        private DoneContainsKeywordsPredicate extractDonePrefixInput(ArgumentMultimap argMultimap)
                throws ParseException {
            assert argMultimap.getValue(PREFIX_DONE).isPresent() : "No inputs for Prefix Role exist.";
            String arg = argMultimap.getValue(PREFIX_DONE).get();
            String trimmedArg = arg.trim();
            List<String> keywords = new ArrayList<>();
            if (!trimmedArg.isEmpty()) {
                if (trimmedArg.equalsIgnoreCase(Done.STATUS_DONE)) {
                    keywords.add(Done.STATUS_DONE);
                } else if (trimmedArg.equalsIgnoreCase(Done.STATUS_UNDONE)) {
                    keywords.add(Done.STATUS_UNDONE);
                } else {
                    throw new ParseException(Done.FIND_MESSAGE_CONSTRAINTS);
                }
                return new DoneContainsKeywordsPredicate(keywords);
            }
            return null;
        }

        public ArrayList<Predicate<Person>> getPredicates() {
            return predicateList;
        }

        private static String[] splitByWhiteSpace(String arg) {
            return arg.split("\\s+");
        }

    }
}
