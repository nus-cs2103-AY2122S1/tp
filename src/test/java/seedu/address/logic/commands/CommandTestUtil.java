package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALIAS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicate.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String TAG_COLOUR_DELIMITER = ":";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_GENDER_AMY = "F";
    public static final String VALID_GENDER_BOB = "M";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_NATIONALITY_AMY = "Singaporean";
    public static final String VALID_NATIONALITY_BOB = "Malaysian";
    public static final String VALID_TUTORIAL_GROUP_AMY = "T09";
    public static final String VALID_TUTORIAL_GROUP_BOB = "T28";
    public static final String VALID_REMARK_AMY = "Like skiing.";
    public static final String VALID_REMARK_BOB = "Favourite pastime: Eating";
    public static final String VALID_TAG_COLOUR = "#00EFFF";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_SOCIAL_HANDLE_AMY = "tg:amyb";
    public static final String VALID_SOCIAL_HANDLE_BOB = "tg:bobc";


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String NATIONALITY_DESC_AMY = " " + PREFIX_NATIONALITY + VALID_NATIONALITY_AMY;
    public static final String NATIONALITY_DESC_BOB = " " + PREFIX_NATIONALITY + VALID_NATIONALITY_BOB;
    public static final String TUTORIAL_GROUP_DESC_AMY = " " + PREFIX_TUTORIAL_GROUP + VALID_TUTORIAL_GROUP_AMY;
    public static final String TUTORIAL_GROUP_DESC_BOB = " " + PREFIX_TUTORIAL_GROUP + VALID_TUTORIAL_GROUP_BOB;
    public static final String SOCIAL_HANDLE_DESC_AMY = " " + PREFIX_SOCIAL_HANDLE + VALID_SOCIAL_HANDLE_AMY;
    public static final String SOCIAL_HANDLE_DESC_BOB = " " + PREFIX_SOCIAL_HANDLE + VALID_SOCIAL_HANDLE_BOB;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND + TAG_COLOUR_DELIMITER
            + VALID_TAG_COLOUR;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String VALID_ALIAS = "myalias";
    public static final String VALID_ADD_INPUT = "add n/" + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + NATIONALITY_DESC_BOB + TUTORIAL_GROUP_DESC_BOB + SOCIAL_HANDLE_DESC_BOB + GENDER_DESC_BOB
            + REMARK_DESC_BOB + TAG_DESC_FRIEND;
    public static final String VALID_STATISTICS_INPUT = "stat " + VALID_TUTORIAL_GROUP_AMY;
    public static final String UNKNOWN_LONG_COMMAND_INPUT = "asdhasdhashasdhs";

    public static final String ALIAS = " " + PREFIX_ALIAS + VALID_ALIAS;
    public static final String ADD_COMMAND = " " + PREFIX_COMMAND + VALID_ADD_INPUT;
    public static final String STATISTICS_COMMAND = " " + PREFIX_COMMAND + VALID_STATISTICS_INPUT;
    public static final String UNKNOWN_COMMAND = " " + PREFIX_COMMAND + UNKNOWN_LONG_COMMAND_INPUT;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "B"; // only 'M', 'F' and 'O' are allowed
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TUTORIAL_GROUP_DESC = " "
            + PREFIX_TUTORIAL_GROUP + "B"; // alphabets not allowed in tutorial groups
    public static final String INVALID_SOCIAL_HANDLE_DESC = " "
            + PREFIX_SOCIAL_HANDLE + "igjewfewij"; // missing ':' to delimit platform and value
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_TAG_COLOUR_DESC = " " + PREFIX_TAG + "hubby" + TAG_COLOUR_DELIMITER
            + "bubbleBlue"; // invalid colour code not allowed
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).withGender(VALID_GENDER_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withNationality(VALID_NATIONALITY_AMY)
                .withTutorialGroup(VALID_TUTORIAL_GROUP_AMY).withSocialHandles(VALID_SOCIAL_HANDLE_AMY)
                .withRemark(REMARK_DESC_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).withGender(VALID_GENDER_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withNationality(VALID_NATIONALITY_BOB)
                .withTutorialGroup(VALID_TUTORIAL_GROUP_BOB).withSocialHandles(VALID_SOCIAL_HANDLE_BOB)
                .withRemark(REMARK_DESC_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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

}
