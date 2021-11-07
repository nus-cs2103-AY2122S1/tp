package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUS_NETWORK_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.PartialKeyContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String NAMETYPE = "n/";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "e0000000@u.nus.edu";
    public static final String VALID_EMAIL_BOB = "e1111111@u.nus.edu";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friends";
    public static final String VALID_GITHUB_ID_AMY = "amy";
    public static final String VALID_GITHUB_ID_BOB = "bob";
    public static final String VALID_NUS_NETWORK_ID_AMY = "e0000000";
    public static final String VALID_NUS_NETWORK_ID_BOB = "e1111111";
    public static final String VALID_TYPE_AMY = "student";
    public static final String VALID_TYPE_BOB = "tutor";
    public static final String VALID_STUDENT_ID_AMY = "A0000000A";
    public static final String VALID_STUDENT_ID_BOB = "A1111111A";
    public static final String VALID_TUTORIAL_ID_AMY = "00";
    public static final String VALID_TUTORIAL_ID_BOB = "11";


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String GITHUB_ID_DESC_AMY = " " + PREFIX_GITHUB_ID + VALID_GITHUB_ID_AMY;
    public static final String GITHUB_ID_DESC_BOB = " " + PREFIX_GITHUB_ID + VALID_GITHUB_ID_BOB;
    public static final String NUS_NETWORK_ID_DESC_AMY = " " + PREFIX_NUS_NETWORK_ID + VALID_NUS_NETWORK_ID_AMY;
    public static final String NUS_NETWORK_ID_DESC_BOB = " " + PREFIX_NUS_NETWORK_ID + VALID_NUS_NETWORK_ID_BOB;
    public static final String TYPE_DESC_AMY = " " + PREFIX_TYPE + VALID_TYPE_AMY;
    public static final String TYPE_DESC_BOB = " " + PREFIX_TYPE + VALID_TYPE_BOB;
    public static final String STUDENT_ID_DESC_AMY = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_AMY;
    public static final String STUDENT_ID_DESC_BOB = " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_BOB;
    public static final String TUTORIAL_ID_DESC_AMY = " " + PREFIX_TUTORIAL_ID + VALID_TUTORIAL_ID_AMY;
    public static final String TUTORIAL_ID_DESC_BOB = " " + PREFIX_TUTORIAL_ID + VALID_TUTORIAL_ID_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_GITHUB_ID_DESC = " " + PREFIX_GITHUB_ID + "sid-"; // GitHub ID can't end with '-'
    public static final String INVALID_NUS_NETWORK_ID_DESC = " " + PREFIX_NUS_NETWORK_ID + "e000000"; // need 7 digits
    public static final String INVALID_TYPE_DESC = " " + PREFIX_TYPE + "teacher"; // accepts only student or tutor
    public static final String INVALID_STUDENT_ID_DESC = " " + PREFIX_STUDENT_ID + "A000000X"; // 7 digits in the middle
    public static final String INVALID_TUTORIAL_ID_DESC = " " + PREFIX_TUTORIAL_ID + "001"; // only 2 digit tutorial id


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND)
                .withGitHubId(VALID_GITHUB_ID_AMY)
                .withNusNetworkId(VALID_NUS_NETWORK_ID_AMY)
                .withType(VALID_TYPE_AMY)
                .withStudentId(VALID_STUDENT_ID_AMY)
                .withTutorialId(VALID_TUTORIAL_ID_AMY)
                .build();

        DESC_BOB = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withGitHubId(VALID_GITHUB_ID_BOB)
                .withNusNetworkId(VALID_NUS_NETWORK_ID_BOB)
                .withType(VALID_TYPE_BOB)
                .withStudentId(VALID_STUDENT_ID_BOB)
                .withTutorialId(VALID_TUTORIAL_ID_BOB)
                .build();
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
        } catch (CommandException | DataConversionException ce) {
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
        model.updateFilteredPersonList(new PartialKeyContainsKeywordsPredicate(Arrays.asList(splitName[0]), NAMETYPE));

        assertEquals(1, model.getFilteredPersonList().size());
    }
    /**
     * Updates {@code model}'s filtered list to show only the persons in {@code persons} in the
     * {@code model}'s address book.
     */
    public static void showPersons(Model model, Person... persons) {
        final String[] queries = new String[persons.length];

        for (int i = 0; i < persons.length; ++i) {
            queries[i] = persons[i].getName().fullName.split("\\s+")[0];
        }
        model.updateFilteredPersonList(new PartialKeyContainsKeywordsPredicate(Arrays.asList(queries), NAMETYPE));

        assertEquals(persons.length, model.getFilteredPersonList().size());
    }

}
