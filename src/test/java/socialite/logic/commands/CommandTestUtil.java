package socialite.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import socialite.commons.core.index.Index;
import socialite.logic.commands.exceptions.CommandException;
import socialite.logic.parser.CliSyntax;
import socialite.model.ContactList;
import socialite.model.Model;
import socialite.model.person.ContainsKeywordsPredicate;
import socialite.model.person.Person;
import socialite.testutil.Assert;
import socialite.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_REMARK_AMY = "Like skiing.";
    public static final String VALID_REMARK_BOB = "Favourite pastime: Eating";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_FACEBOOK_AMY = "amy.facebook";
    public static final String VALID_FACEBOOK_BOB = "bob.facebook";
    public static final String VALID_INSTAGRAM_AMY = "amy.insta";
    public static final String VALID_INSTAGRAM_BOB = "bob.insta";
    public static final String VALID_TELEGRAM_AMY = "amy_telegram";
    public static final String VALID_TELEGRAM_BOB = "bob_telegram";
    public static final String VALID_TIKTOK_AMY = "amy.bee";
    public static final String VALID_TIKTOK_BOB = "bob.choo";
    public static final String VALID_TWITTER_AMY = "amy_bee";
    public static final String VALID_TWITTER_BOB = "bob_chOo99";
    public static final String VALID_DATE_AMY = "bbbbday:1965-03-03";
    public static final String VALID_DATE_BOB = "bday:1999-03-03";


    public static final String NAME_DESC_AMY = " " + CliSyntax.PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + CliSyntax.PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String REMARK_DESC_AMY = " " + CliSyntax.PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + CliSyntax.PREFIX_REMARK + VALID_REMARK_BOB;
    public static final String TAG_DESC_FRIEND = " " + CliSyntax.PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + CliSyntax.PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String FACEBOOK_DESC_AMY = " " + CliSyntax.PREFIX_FACEBOOK + VALID_FACEBOOK_AMY;
    public static final String FACEBOOK_DESC_BOB = " " + CliSyntax.PREFIX_FACEBOOK + VALID_FACEBOOK_BOB;
    public static final String INSTAGRAM_DESC_AMY = " " + CliSyntax.PREFIX_INSTAGRAM + VALID_INSTAGRAM_AMY;
    public static final String INSTAGRAM_DESC_BOB = " " + CliSyntax.PREFIX_INSTAGRAM + VALID_INSTAGRAM_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + CliSyntax.PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + CliSyntax.PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String TIKTOK_DESC_AMY = " " + CliSyntax.PREFIX_TIKTOK + VALID_TIKTOK_AMY;
    public static final String TIKTOK_DESC_BOB = " " + CliSyntax.PREFIX_TIKTOK + VALID_TIKTOK_BOB;
    public static final String TWITTER_DESC_AMY = " " + CliSyntax.PREFIX_TWITTER + VALID_TWITTER_AMY;
    public static final String TWITTER_DESC_BOB = " " + CliSyntax.PREFIX_TWITTER + VALID_TWITTER_BOB;
    public static final String DATE_DESC_AMY = " " + CliSyntax.PREFIX_DATE + VALID_DATE_AMY;
    public static final String DATE_DESC_BOB = " " + CliSyntax.PREFIX_DATE + VALID_DATE_BOB;

    // '&' not allowed in names
    public static final String INVALID_NAME_DESC = " " + CliSyntax.PREFIX_NAME + "James&";
    // 'a' not allowed in phones
    public static final String INVALID_PHONE_DESC = " " + CliSyntax.PREFIX_PHONE + "911a";
    // '*' not allowed in tags
    public static final String INVALID_TAG_DESC = " " + CliSyntax.PREFIX_TAG + "hubby*";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
     * - the contact list, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ContactList expectedContactList = new ContactList(actualModel.getContactList());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        Assert.assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedContactList, actualModel.getContactList());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s contact list.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new ContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
