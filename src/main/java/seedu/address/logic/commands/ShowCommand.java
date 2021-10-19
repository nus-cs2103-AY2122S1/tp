package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL_OF_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
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

    public ShowCommand(Prefix prefix) {
        this.prefix = prefix;
    }

    @Override
    public CommandResult execute(Model model) {

        requireNonNull(model);

        String message = getUniqueCategoryInputs(model);

        return new CommandResult(message);
    }

    private String getUniqueCategoryInputs(Model model) {

        // obtains an unmodifiable list of all applicants
        ReadOnlyAddressBook addressBook = model.getAddressBook();
        ObservableList<Person> ol = addressBook.getPersonList();

        String prefixString = prefix.getPrefix();

        // temporary variables to hold unique search terms and part of UI message to user
        Set<String> uniqueInputs = new HashSet<>();
        String userText = "";

        if (prefixString.equals(PREFIX_NAME.getPrefix())) {
            userText = "names";
            uniqueInputs = getUniqueNameInputs(ol);
        }

        if (prefixString.equals(PREFIX_PHONE.getPrefix())) {
            userText = "contact numbers";
            uniqueInputs = getUniquePhoneInputs(ol);
        }

        if (prefixString.equals(PREFIX_EMAIL.getPrefix())) {
            userText = "emails";
            uniqueInputs = getUniqueEmailInputs(ol);
        }

        if (prefixString.equals(PREFIX_ROLE.getPrefix())) {
            userText = "roles";
            uniqueInputs = getUniqueRoleInputs(ol);
        }

        if (prefixString.equals(PREFIX_EMPLOYMENT_TYPE.getPrefix())) {
            userText = "employment types";
            uniqueInputs = getUniqueEmploymentTypeInputs(ol);
        }

        if (prefixString.equals(PREFIX_EXPECTED_SALARY.getPrefix())) {
            userText = "expected salaries";
            uniqueInputs = getUniqueExpectedSalaryInputs(ol);
        }

        if (prefixString.equals(PREFIX_LEVEL_OF_EDUCATION.getPrefix())) {
            userText = "levels of education";
            uniqueInputs = getUniqueLevelOfEducationInputs(ol);
        }

        if (prefixString.equals(PREFIX_EXPERIENCE.getPrefix())) {
            userText = "years of experience";
            uniqueInputs = getUniqueExperienceInputs(ol);
        }

        if (prefixString.equals(PREFIX_TAG.getPrefix())) {
            userText = "tags";
            uniqueInputs = getUniqueTagInputs(ol);
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

    private Set<String> getUniqueNameInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getName().toString()).collect(Collectors.toSet());
    }

    private Set<String> getUniquePhoneInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getPhone().toString()).collect(Collectors.toSet());
    }

    private Set<String> getUniqueEmailInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getEmail().toString()).collect(Collectors.toSet());
    }

    private Set<String> getUniqueRoleInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getRole().toString()).collect(Collectors.toSet());
    }

    private Set<String> getUniqueEmploymentTypeInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getEmploymentType().toString()).collect(Collectors.toSet());
    }

    private Set<String> getUniqueExpectedSalaryInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getExpectedSalary().toString()).collect(Collectors.toSet());
    }

    private Set<String> getUniqueLevelOfEducationInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getLevelOfEducation().toString()).collect(Collectors.toSet());
    }

    private Set<String> getUniqueExperienceInputs(ObservableList<Person> ol) {
        return ol.stream()
                .map(x -> x.getExperience().toString()).collect(Collectors.toSet());
    }

    private Set<String> getUniqueTagInputs(ObservableList<Person> ol) {
        return ol.stream()
                .flatMap(person -> person.getTags().stream().map(Tag::toString)).collect(Collectors.toSet());
    }

}
