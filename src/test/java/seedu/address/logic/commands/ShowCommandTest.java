package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.tag.Tag;

public class ShowCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_nullPrefix_nullPointerExceptionThrown() {

        assertThrows(NullPointerException.class, () -> new ShowCommand(null));
    }

    @Test
    public void execute_namePrefix_namesListed() {

        StringBuilder expectedMessageSB = new StringBuilder("Here are all the names present:\n");
        List<String> names = getTypicalPersons().stream().map(x -> x.getName().toString())
                .distinct().sorted().collect(Collectors.toList());
        for (String name: names) {
            expectedMessageSB.append(name);
            expectedMessageSB.append("\n");
        }

        String expectedMessage = expectedMessageSB.toString();
        ShowCommand command = new ShowCommand(CliSyntax.PREFIX_NAME);
        assertCommandSuccess(command, model, expectedMessage, model);
    }

    @Test
    public void execute_phonePrefix_phonesListed() {

        StringBuilder expectedMessageSB = new StringBuilder("Here are all the phone numbers present:\n");
        List<String> phones = getTypicalPersons().stream().map(x -> x.getPhone().toString())
                .distinct().sorted().collect(Collectors.toList());
        for (String name: phones) {
            expectedMessageSB.append(name);
            expectedMessageSB.append("\n");
        }

        String expectedMessage = expectedMessageSB.toString();
        ShowCommand command = new ShowCommand(CliSyntax.PREFIX_PHONE);
        assertCommandSuccess(command, model, expectedMessage, model);

    }

    @Test
    public void execute_emailPrefix_emailsListed() {

        StringBuilder expectedMessageSB = new StringBuilder("Here are all the emails present:\n");
        List<String> emails = getTypicalPersons().stream().map(x -> x.getEmail().toString())
                .distinct().sorted().collect(Collectors.toList());
        for (String email: emails) {
            expectedMessageSB.append(email);
            expectedMessageSB.append("\n");
        }

        String expectedMessage = expectedMessageSB.toString();
        ShowCommand command = new ShowCommand(CliSyntax.PREFIX_EMAIL);
        assertCommandSuccess(command, model, expectedMessage, model);

    }

    @Test
    public void execute_rolePrefix_rolesListed() {

        StringBuilder expectedMessageSB = new StringBuilder("Here are all the roles present:\n");
        List<String> roles = getTypicalPersons().stream().map(x -> x.getRole().toString())
                .distinct().sorted().collect(Collectors.toList());
        for (String role: roles) {
            expectedMessageSB.append(role);
            expectedMessageSB.append("\n");
        }

        String expectedMessage = expectedMessageSB.toString();
        ShowCommand command = new ShowCommand(CliSyntax.PREFIX_ROLE);
        assertCommandSuccess(command, model, expectedMessage, model);

    }

    @Test
    public void execute_employmentTypePrefix_employmentTypesListed() {

        StringBuilder expectedMessageSB = new StringBuilder("Here are all the employment types present:\n");
        List<String> employmentTypes = getTypicalPersons().stream().map(x -> x.getEmploymentType().toString())
                .distinct().sorted().collect(Collectors.toList());
        for (String employmentType: employmentTypes) {
            expectedMessageSB.append(employmentType);
            expectedMessageSB.append("\n");
        }

        String expectedMessage = expectedMessageSB.toString();
        ShowCommand command = new ShowCommand(CliSyntax.PREFIX_EMPLOYMENT_TYPE);
        assertCommandSuccess(command, model, expectedMessage, model);

    }

    @Test
    public void execute_expectedSalaryPrefix_expectedSalariesListed() {

        StringBuilder expectedMessageSB = new StringBuilder("Here are all the expected salaries present:\n");
        List<String> expectedSalaries = getTypicalPersons().stream().map(x -> x.getExpectedSalary().toString())
                .distinct().sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .collect(Collectors.toList());
        for (String expectedSalary: expectedSalaries) {
            expectedMessageSB.append(expectedSalary);
            expectedMessageSB.append("\n");
        }


        String expectedMessage = expectedMessageSB.toString();
        ShowCommand command = new ShowCommand(CliSyntax.PREFIX_EXPECTED_SALARY);
        assertCommandSuccess(command, model, expectedMessage, model);

    }

    @Test
    public void execute_levelOfEducationPrefix_levelsOfEducationListed() {

        StringBuilder expectedMessageSB = new StringBuilder("Here are all the levels of education present:\n");
        List<String> levelsOfEducation = getTypicalPersons().stream().map(x -> x.getLevelOfEducation().toString())
                .distinct().sorted().collect(Collectors.toList());
        for (String levelOfEducation: levelsOfEducation) {
            expectedMessageSB.append(levelOfEducation);
            expectedMessageSB.append("\n");
        }

        String expectedMessage = expectedMessageSB.toString();
        ShowCommand command = new ShowCommand(CliSyntax.PREFIX_LEVEL_OF_EDUCATION);
        assertCommandSuccess(command, model, expectedMessage, model);


    }

    @Test
    public void execute_experiencePrefix_experiencesListed() {

        StringBuilder expectedMessageSB = new StringBuilder("Here are all the years of experience present:\n");
        List<String> experiences = getTypicalPersons().stream().map(x -> x.getExperience().toString())
                .distinct().sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .collect(Collectors.toList());
        for (String experience: experiences) {
            expectedMessageSB.append(experience);
            expectedMessageSB.append("\n");
        }


        String expectedMessage = expectedMessageSB.toString();
        ShowCommand command = new ShowCommand(CliSyntax.PREFIX_EXPERIENCE);
        assertCommandSuccess(command, model, expectedMessage, model);

    }

    @Test
    public void execute_tagPrefix_tagsListed() {

        StringBuilder expectedMessageSB = new StringBuilder("Here are all the tags present:\n");
        List<String> tags = getTypicalPersons().stream()
                .flatMap(person -> person.getTags().stream().map(Tag::toShowString))
                .distinct().sorted().collect(Collectors.toList());
        for (String tag: tags) {
            expectedMessageSB.append(tag);
            expectedMessageSB.append("\n");
        }


        String expectedMessage = expectedMessageSB.toString();
        ShowCommand command = new ShowCommand(CliSyntax.PREFIX_TAG);
        assertCommandSuccess(command, model, expectedMessage, model);

    }

    @Test
    public void execute_interviewPrefix_interviewListed() {

        StringBuilder expectedMessageSB = new StringBuilder("Here are all the interviews present:\n");
        List<String> interviews = getTypicalPersons().stream()
                .map(x -> x.getInterview().orElse(Interview.EMPTY_INTERVIEW)).distinct().sorted(Interview::compareTo)
                .map(Interview::toString)
                .collect(Collectors.toList());
        for (String interview: interviews) {
            expectedMessageSB.append(interview);
            expectedMessageSB.append("\n");
        }


        String expectedMessage = expectedMessageSB.toString();
        ShowCommand command = new ShowCommand(CliSyntax.PREFIX_INTERVIEW);
        assertCommandSuccess(command, model, expectedMessage, model);

    }

}
