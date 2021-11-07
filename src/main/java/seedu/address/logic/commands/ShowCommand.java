package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_EMAIL_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_EMPLOYMENT_TYPE_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_EXPECTED_SALARY_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_EXPERIENCE_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_INTERVIEW_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_LEVEL_OF_EDUCATION_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_NAME_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_PHONE_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_ROLE_SYNTAX;
import static seedu.address.logic.parser.PrefixSyntax.PREFIX_TAG_SYNTAX;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;


/**
 * Lists all persons in the address book to the user.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all inputs for a specific category, "
            + "based on the given prefix "
            + "and displays them as a list.\n"
            + "Parameters: PREFIX/KEYWORDS \n"
            + "Example: " + COMMAND_WORD + " r/";

    private final Prefix prefix;

    /**
     * Constructor for ShowCommand.
     * Prefix object passed as parameter cannot be null.
     *
     * @param prefix Category to get unique terms from.
     */
    public ShowCommand(Prefix prefix) {

        requireNonNull(prefix);

        this.prefix = prefix;
    }

    @Override
    public CommandResult execute(Model model) {

        requireNonNull(model);

        String message = getUniqueCategoryInputs(model);

        return new CommandResult(message);
    }

    private String getUniqueCategoryInputs(Model model) {

        assert prefix != null : "Prefix should not be null";

        // obtains an unmodifiable list of all applicants
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        ObservableList<Person> ol = addressBook.getPersonList();

        String prefixString = prefix.getPrefix();
        Pair<String, List<String>> results = parsePrefix(prefixString, ol);

        assert results.getKey() != null : "Prefix given should not be invalid";

        // temporary variables to hold unique search terms and part of UI message to user
        List<String> uniqueInputs = results.getValue();
        String userText = results.getKey();

        if (!uniqueInputs.isEmpty()) {
            StringBuilder sb = new StringBuilder("Here are all the " + userText + " present:\n");
            for (String uniqueInput: uniqueInputs) {
                sb.append(uniqueInput);
                sb.append("\n");
            }
            return sb.toString();
        } else {
            return "No search terms exist for " + userText;
        }
    }

    /**
     * Parses {@code prefixString} into a pair containing the user text for display as key and a list of
     * strings representing unique prefix values as value.
     *
     * @param prefixString prefix to be parsed
     * @param ol list of all applicants
     */
    private Pair<String, List<String>> parsePrefix(String prefixString, ObservableList<Person> ol) {
        switch (prefixString) {
        case PREFIX_NAME_SYNTAX:
            return new Pair<>("names", getUniqueNameInputs(ol));
        case PREFIX_PHONE_SYNTAX:
            return new Pair<>("phone numbers", getUniquePhoneInputs(ol));
        case PREFIX_EMAIL_SYNTAX:
            return new Pair<>("emails", getUniqueEmailInputs(ol));
        case PREFIX_ROLE_SYNTAX:
            return new Pair<>("roles", getUniqueRoleInputs(ol));
        case PREFIX_EMPLOYMENT_TYPE_SYNTAX:
            return new Pair<>("employment types", getUniqueEmploymentTypeInputs(ol));
        case PREFIX_EXPECTED_SALARY_SYNTAX:
            return new Pair<>("expected salaries", getUniqueExpectedSalaryInputs(ol));
        case PREFIX_LEVEL_OF_EDUCATION_SYNTAX:
            return new Pair<>("levels of education", getUniqueLevelOfEducationInputs(ol));
        case PREFIX_EXPERIENCE_SYNTAX:
            return new Pair<>("years of experience", getUniqueExperienceInputs(ol));
        case PREFIX_TAG_SYNTAX:
            return new Pair<>("tags", getUniqueTagInputs(ol));
        case PREFIX_INTERVIEW_SYNTAX:
            return new Pair<>("interviews", getUniqueInterviewInputs(ol));
        default:
            return new Pair<>(null, null);
        }
    }

    private List<String> getUniqueNameInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getName().toString()).distinct().sorted().collect(Collectors.toList());
    }

    private List<String> getUniquePhoneInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getPhone().toString()).distinct().sorted().collect(Collectors.toList());
    }

    private List<String> getUniqueEmailInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getEmail().toString()).distinct().sorted().collect(Collectors.toList());
    }

    private List<String> getUniqueRoleInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getRole().toString()).distinct().sorted().collect(Collectors.toList());
    }

    private List<String> getUniqueEmploymentTypeInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getEmploymentType().toString()).distinct().sorted().collect(Collectors.toList());
    }

    private List<String> getUniqueExpectedSalaryInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getExpectedSalary().toString()).distinct()
                .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .collect(Collectors.toList());
    }

    private List<String> getUniqueLevelOfEducationInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getLevelOfEducation().toString()).distinct().sorted().collect(Collectors.toList());
    }

    private List<String> getUniqueExperienceInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getExperience().toString()).distinct()
                .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .collect(Collectors.toList());
    }

    private List<String> getUniqueInterviewInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getInterview().orElse(Interview.EMPTY_INTERVIEW)).distinct().sorted(Interview::compareTo)
                .map(Interview::toString)
                .collect(Collectors.toList());
    }

    private List<String> getUniqueTagInputs(ObservableList<Person> ol) {
        return ol.stream()
                .flatMap(person -> person.getTags().stream().map(Tag::toShowString))
                .distinct().sorted().collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && prefix.equals(((ShowCommand) other).prefix)); // state check
    }

}
