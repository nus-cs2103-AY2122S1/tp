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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
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

        // temporary variables to hold unique search terms and part of UI message to user
        List<String> uniqueInputs = new ArrayList<>();
        String userText = "";

        switch (prefixString) {
        case PREFIX_NAME_SYNTAX:
            userText = "names";
            uniqueInputs = getUniqueNameInputs(ol);
            break;
        case PREFIX_PHONE_SYNTAX:
            userText = "contact numbers";
            uniqueInputs = getUniquePhoneInputs(ol);
            break;
        case PREFIX_EMAIL_SYNTAX:
            userText = "emails";
            uniqueInputs = getUniqueEmailInputs(ol);
            break;
        case PREFIX_ROLE_SYNTAX:
            userText = "roles";
            uniqueInputs = getUniqueRoleInputs(ol);
            break;
        case PREFIX_EMPLOYMENT_TYPE_SYNTAX:
            userText = "employment types";
            uniqueInputs = getUniqueEmploymentTypeInputs(ol);
            break;
        case PREFIX_EXPECTED_SALARY_SYNTAX:
            userText = "expected salaries";
            uniqueInputs = getUniqueExpectedSalaryInputs(ol);
            break;
        case PREFIX_LEVEL_OF_EDUCATION_SYNTAX:
            userText = "levels of education";
            uniqueInputs = getUniqueLevelOfEducationInputs(ol);
            break;
        case PREFIX_EXPERIENCE_SYNTAX:
            userText = "years of experience";
            uniqueInputs = getUniqueExperienceInputs(ol);
            break;
        case PREFIX_TAG_SYNTAX:
            userText = "tags";
            uniqueInputs = getUniqueTagInputs(ol);
            break;
        case PREFIX_INTERVIEW_SYNTAX:
            userText = "interviews";
            uniqueInputs = getUniqueInterviewInputs(ol);
            break;
        default:
            return "No search terms exist for unknown prefix " + prefixString;
        }

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
                .flatMap(person -> person.getTags().stream().map(Tag::toString))
                .distinct().sorted().collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && prefix.equals(((ShowCommand) other).prefix)); // state check
    }

}
