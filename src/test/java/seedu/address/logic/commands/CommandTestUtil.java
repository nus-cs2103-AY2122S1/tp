package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupNameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonNameContainsKeywordsPredicate;
import seedu.address.testutil.EditGroupDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CAROL = "Carol Peletier";
    public static final String VALID_NAME_DONALD = "Donald Zuramp";
    public static final String VALID_NAME_SWIMMING = "Swimming Club";
    public static final String VALID_NAME_TENNIS = "Tennis Club";
    public static final String VALID_NAME_VOLLEYBALL = "Volleyball club";
    public static final String VALID_NAME_QUIDDITCH = "Quidditch Club";
    public static final String VALID_DESCRIPTION_SPORTS = "Sports Club";
    public static final String VALID_DESCRIPTION_CSMODULE = "CS module";
    public static final String VALID_DESCRIPTION_FAMILY = "Family";
    public static final String VALID_DESCRIPTION_FRIENDS = "Friends";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_CAROL = "12321232";
    public static final String VALID_PHONE_DONALD = "78987898";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_CAROL = "carol@example.com";
    public static final String VALID_EMAIL_DONALD = "donald@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ADDRESS_DONALD = "Zuramp Tower, 7347 State Rd., Philadelphia, PA 19136";
    public static final String VALID_TAG_WIFE = "wife";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_COVFEFE = "covfefe";
    public static final String VALID_GROUP_NAME_CS2101 = "CS2101";
    public static final String VALID_DESCRIPTION_TASK_1 = "Prepare Pitch";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_CAROL = " " + PREFIX_NAME + VALID_NAME_CAROL;
    public static final String NAME_DESC_TENNIS = " " + PREFIX_NAME + VALID_NAME_TENNIS;
    public static final String NAME_DESC_SWIMMING = " " + PREFIX_NAME + VALID_NAME_SWIMMING;
    public static final String NAME_DESC_VOLLEYBALL = " " + PREFIX_NAME + VALID_NAME_VOLLEYBALL;
    public static final String DESCRIPTION_DESC_SPORTS = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_SPORTS;
    public static final String DESCRIPTION_DESC_TASK_1 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_TASK_1;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String PHONE_DESC_CAROL = " " + PREFIX_PHONE + VALID_PHONE_CAROL;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String EMAIL_DESC_CAROL = " " + PREFIX_EMAIL + VALID_EMAIL_CAROL;
    public static final String ADDRESS_DESC_EMPTY = " " + PREFIX_ADDRESS; // empty string for removing addresses in edit
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_WIFE = " " + PREFIX_TAG + VALID_TAG_WIFE;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String INDEX_DESC_PERSON1 = " " + PREFIX_PERSON + INDEX_FIRST.getOneBased();
    public static final String INDEX_DESC_GROUP1 = " " + PREFIX_GROUP + INDEX_FIRST.getOneBased();

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_DESCRIPTION_DESC = " "
            + PREFIX_DESCRIPTION + " "; //empty string not allowed for addresses

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final EditGroupCommand.EditGroupDescriptor DESC_TENNIS;
    public static final EditGroupCommand.EditGroupDescriptor DESC_VOLLEYBALL;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        DESC_TENNIS = new EditGroupDescriptorBuilder().withName(VALID_NAME_TENNIS)
                .withDescription(VALID_DESCRIPTION_SPORTS).build();

        DESC_VOLLEYBALL = new EditGroupDescriptorBuilder().withName(VALID_NAME_VOLLEYBALL)
                .withDescription(VALID_DESCRIPTION_SPORTS).build();
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
     * Undoes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedUndoResult} <br>
     * - the {@code actualModel} is the same as it was before execution
     */
    public static void assertUndoSuccess(UndoableCommand command, Model actualModel, CommandResult expectedUndoResult) {
        try {
            Model expectedModel = new ModelManager(actualModel.getAddressBook(), new UserPrefs());
            command.execute(actualModel);
            CommandResult actualUndoResult = command.undo(actualModel);
            assertEquals(expectedUndoResult, actualUndoResult);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Undo of command should not fail.", ce);
        }
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
        List<Person> expectedFilteredPersonList = new ArrayList<>(actualModel.getFilteredPersonList());
        List<Group> expectedFilteredGroupList = new ArrayList<>(actualModel.getFilteredGroupList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredPersonList, actualModel.getFilteredPersonList());
        assertEquals(expectedFilteredGroupList, actualModel.getFilteredGroupList());
    }
    /**
     * Updates {@code model}'s filtered person list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new PersonNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered group list to show only the group at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showGroupAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredGroupList().size());

        Group group = model.getFilteredGroupList().get(targetIndex.getZeroBased());
        final String[] splitName = group.getName().fullName.split("\\s+");
        model.updateFilteredGroupList(new GroupNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredGroupList().size());
    }
}
