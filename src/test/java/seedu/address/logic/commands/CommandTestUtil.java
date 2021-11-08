package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.done.Done;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_DONE_AMY = Done.STATUS_UNDONE;
    public static final String VALID_DONE_BOB = Done.STATUS_UNDONE;
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NOTES_AMY = "She is a very good candidate for this job!";
    public static final String VALID_NOTES_BOB = "He is not a suitable candidate for this job based off his resume.";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ROLE_AMY = "Software Engineer";
    public static final String VALID_ROLE_BOB = "Software Tester";
    public static final String VALID_EMPLOYMENT_TYPE_AMY = "Full time";
    public static final String VALID_EMPLOYMENT_TYPE_BOB = "Part time";
    public static final String VALID_EXPECTED_SALARY_AMY = "3750";
    public static final String VALID_EXPECTED_SALARY_BOB = "2600";
    public static final String VALID_LEVEL_OF_EDUCATION_AMY = "PhD";
    public static final String VALID_LEVEL_OF_EDUCATION_BOB = "Masters";
    public static final String VALID_EXPERIENCE_AMY = "1";
    public static final String VALID_EXPERIENCE_BOB = "2";
    public static final String VALID_INTERVIEW_AMY = "2021-10-29, 10:30";
    public static final String VALID_INTERVIEW_BOB = "2021-11-01, 10:30";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NOTES_DESC_AMY = " " + PREFIX_NOTES + VALID_NOTES_AMY;
    public static final String NOTES_DESC_BOB = " " + PREFIX_NOTES + VALID_NOTES_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ROLE_DESC_AMY = " " + PREFIX_ROLE + VALID_ROLE_AMY;
    public static final String ROLE_DESC_BOB = " " + PREFIX_ROLE + VALID_ROLE_BOB;
    public static final String EMPLOYMENT_TYPE_DESC_AMY = " " + PREFIX_EMPLOYMENT_TYPE + VALID_EMPLOYMENT_TYPE_AMY;
    public static final String EMPLOYMENT_TYPE_DESC_BOB = " " + PREFIX_EMPLOYMENT_TYPE + VALID_EMPLOYMENT_TYPE_BOB;
    public static final String EXPECTED_SALARY_DESC_AMY = " " + PREFIX_EXPECTED_SALARY + VALID_EXPECTED_SALARY_AMY;
    public static final String EXPECTED_SALARY_DESC_BOB = " " + PREFIX_EXPECTED_SALARY + VALID_EXPECTED_SALARY_BOB;
    public static final String LEVEL_OF_EDUCATION_DESC_AMY =
            " " + PREFIX_LEVEL_OF_EDUCATION + VALID_LEVEL_OF_EDUCATION_AMY;
    public static final String LEVEL_OF_EDUCATION_DESC_BOB =
            " " + PREFIX_LEVEL_OF_EDUCATION + VALID_LEVEL_OF_EDUCATION_BOB;
    public static final String EXPERIENCE_DESC_AMY = " " + PREFIX_EXPERIENCE + VALID_EXPERIENCE_AMY;
    public static final String EXPERIENCE_DESC_BOB = " " + PREFIX_EXPERIENCE + VALID_EXPERIENCE_BOB;
    public static final String INTERVIEW_DESC_AMY = " " + PREFIX_INTERVIEW + VALID_INTERVIEW_AMY;
    public static final String INTERVIEW_DESC_BOB = " " + PREFIX_INTERVIEW + VALID_INTERVIEW_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE + "Softw@re Eng1n33r"; // '@' not allowed in roles
    public static final String INVALID_EMPLOYMENT_TYPE_DESC =
            " " + PREFIX_EMPLOYMENT_TYPE + "intern"; // "intern" not allowed as employment type
    public static final String INVALID_EXPECTED_SALARY_DESC = " "
            + PREFIX_EXPECTED_SALARY + "-120"; // '-' not allowed for expected salaries
    public static final String INVALID_LEVEL_OF_EDUCATION_DESC =
            " " + PREFIX_LEVEL_OF_EDUCATION + "Kindergarten"; // "Kindergarten" not allowed as level of education
    public static final String INVALID_EXPERIENCE_DESC =
            " " + PREFIX_EXPERIENCE + "-1"; // negative value not allowed for experience
    public static final String INVALID_INTERVIEW_DESC =
            " " + PREFIX_INTERVIEW + "2021.01.01"; // interview time not following the given format
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withRole(VALID_ROLE_AMY)
                .withEmploymentType(VALID_EMPLOYMENT_TYPE_AMY)
                .withExpectedSalary(VALID_EXPECTED_SALARY_AMY)
                .withLevelOfEducation(VALID_LEVEL_OF_EDUCATION_AMY)
                .withExperience(VALID_EXPERIENCE_AMY)
                .withTags(VALID_TAG_FRIEND)
                .withInterview(VALID_INTERVIEW_AMY)
                .withNotes(VALID_NOTES_AMY)
                .withDone(VALID_DONE_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withRole(VALID_ROLE_BOB)
                .withEmploymentType(VALID_EMPLOYMENT_TYPE_BOB)
                .withExpectedSalary(VALID_EXPECTED_SALARY_BOB)
                .withLevelOfEducation(VALID_LEVEL_OF_EDUCATION_BOB)
                .withExperience(VALID_EXPERIENCE_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withInterview(VALID_INTERVIEW_BOB)
                .withNotes(VALID_NOTES_BOB)
                .withDone(VALID_DONE_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Generates a duplicate applicant error message using {@code duplicates} as existing duplicate applicants
     * and {@code toCheck} as the applicant to be checked for duplicates.
     */
    public static String generateDuplicateErrorMessage(String message, Person toCheck, Person ... duplicates) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Person duplicate : duplicates) {
            stringBuilder.append(duplicate);
        }
        return message + toCheck
                + " shares either the same phone number or email as the following applicant(s):\n"
                + stringBuilder;
    }

}
