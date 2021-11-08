package seedu.awe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_EXCLUDE;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.awe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.awe.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.awe.commons.core.index.Index;
import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Awe;
import seedu.awe.model.Model;
import seedu.awe.model.expense.DescriptionContainsKeywordsPredicate;
import seedu.awe.model.expense.Expense;
import seedu.awe.model.group.exceptions.DuplicateGroupException;
import seedu.awe.model.person.NameContainsKeywordsPredicate;
import seedu.awe.model.person.Person;
import seedu.awe.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_ALICE = "Alice Pauline";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_GROUPNAME_BALI = "Bali";
    public static final String VALID_GROUPNAME_OSLO = "Oslo";
    public static final String VALID_GROUP_TAG_FRIENDS = "friends";
    public static final String VALID_GROUP_TAG_FAMILY = "family";

    public static final String VALID_DESCRIPTION_BUFFET = "Buffet";
    public static final String VALID_DESCRIPTION_SOUVENIRS = "Souvenirs";
    public static final String VALID_DESCRIPTION_HOLIDAY = "Holiday";
    public static final String VALID_DESCRIPTION_SNACK = "Snack";
    public static final String VALID_COST_BUFFET = "300";
    public static final String VALID_COST_SOUVENIRS = "200";
    public static final String VALID_COST_HOLIDAY = "50";
    public static final String VALID_COST_SNACK = "2";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_ALICE = " " + PREFIX_NAME + VALID_NAME_ALICE;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String GROUPNAME_DESC_BALI = " " + PREFIX_GROUP_NAME + VALID_GROUPNAME_BALI;
    public static final String GROUPNAME_DESC_OSLO = " " + PREFIX_GROUP_NAME + VALID_GROUPNAME_OSLO;
    public static final String TAG_DESC_FRIENDS = " " + PREFIX_TAG + VALID_GROUP_TAG_FRIENDS;
    public static final String TAG_DESC_FAMILY = " " + PREFIX_TAG + VALID_GROUP_TAG_FAMILY;

    public static final String COST_DESC_FIFTY = " " + PREFIX_COST + VALID_COST_HOLIDAY;
    public static final String COST_DESC_TWO = " " + PREFIX_COST + VALID_COST_SNACK;

    public static final String DESCRIPTION_DESC_HOLIDAY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_HOLIDAY;
    public static final String DESCRIPTION_DESC_SNACK = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_SNACK;

    public static final String EXCLUDE_DESC_BOB = " " + PREFIX_EXCLUDE + VALID_NAME_BOB;
    public static final String EXCLUDE_DESC_ALICE = " " + PREFIX_EXCLUDE + VALID_NAME_ALICE;

    public static final String EXPENSE_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY + PREFIX_GROUP_NAME
            + VALID_GROUPNAME_BALI + PREFIX_COST + VALID_COST_HOLIDAY + PREFIX_DESCRIPTION + VALID_DESCRIPTION_HOLIDAY;
    public static final String EXPENSE_DESC_AMY_WITH_BOB_INDIVIDUAL_PAYMENT = " " + PREFIX_NAME + VALID_NAME_AMY
            + PREFIX_GROUP_NAME + VALID_GROUPNAME_BALI + PREFIX_COST + VALID_COST_BUFFET
            + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BUFFET + PREFIX_NAME + VALID_NAME_BOB + PREFIX_COST
            + VALID_COST_HOLIDAY;
    public static final String EXPENSE_DESC_AMY_WITH_BOB_EXCLUDED = " " + PREFIX_NAME + VALID_NAME_AMY
            + PREFIX_GROUP_NAME + VALID_GROUPNAME_BALI + PREFIX_COST + VALID_COST_BUFFET
            + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BUFFET + PREFIX_EXCLUDE + VALID_NAME_BOB;

    public static final String INVALID_NAME_JAMES = "James#";
    public static final String INVALID_NAME_JOHN = "Jo&hn*";
    public static final String INVALID_NAME_GARY = "G(ary&";
    public static final String INVALID_NAME_DESC_ONE = " "
            + PREFIX_NAME + INVALID_NAME_JAMES; // '&' not allowed in names
    public static final String INVALID_NAME_DESC_TWO = " "
            + PREFIX_NAME + INVALID_NAME_JOHN; // '&' not allowed in names
    public static final String INVALID_NAME_DESC_THREE = " " + PREFIX_NAME + "G(ary&"; // '&' not allowed in names
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_GROUP_NAME_DESC = " " + PREFIX_GROUP_NAME + "Bali&"; // '&' not allowed in names
    public static final String INVALID_GROUP_TAG_DESC = " " + PREFIX_TAG + "Family*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = " \t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = " NonEmptyPreamble";

    public static final EditContactCommand.EditPersonDescriptor DESC_AMY;
    public static final EditContactCommand.EditPersonDescriptor DESC_BOB;

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
        } catch (DuplicateGroupException dge) {
            throw new AssertionError("Execution of command should not fail.", dge);
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
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel, boolean showGroups, boolean showContacts,
                                            boolean showExpenses, boolean showTransactionSummary,
                                            boolean showPayments) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false,
                false, showGroups, showContacts, showExpenses, showTransactionSummary, showPayments);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the awe book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Awe expectedAwe = new Awe(actualModel.getAwe());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAwe, actualModel.getAwe());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s awe book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the expense at the given {@code targetIndex} in the
     * {@code model}'s awe book.
     */
    public static void showExpenseAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getExpenses().size());

        Expense expense = model.getExpenses().get(targetIndex.getZeroBased());
        final String[] splitDescription = expense.getDescription().getFullDescription().split("\\s+");
        model.updateFilteredExpenseList(new DescriptionContainsKeywordsPredicate(Arrays.asList(splitDescription[0])));
    }

}
