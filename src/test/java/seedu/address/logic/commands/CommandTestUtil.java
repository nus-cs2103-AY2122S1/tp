package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Inventory;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditItemDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_BAGEL = "Bagel";
    public static final String VALID_NAME_DONUT = "Donut";
    public static final String VALID_ID_BAGEL = "123456";
    public static final String VALID_ID_DONUT = "789012";
    public static final String VALID_COUNT_BAGEL = "5";
    public static final String VALID_TAG_BAKED = "baked";
    public static final String VALID_TAG_POPULAR = "popular";

    public static final String NAME_DESC_BAGEL = " " + PREFIX_NAME + VALID_NAME_BAGEL;
    public static final String NAME_DESC_DONUT = " " + PREFIX_NAME + VALID_NAME_DONUT;
    public static final String ID_DESC_BAGEL = " " + PREFIX_ID + VALID_ID_BAGEL;
    public static final String ID_DESC_DONUT = " " + PREFIX_ID + VALID_ID_DONUT;
    public static final String COUNT_DESC_BAGEL = " " + PREFIX_COUNT + VALID_COUNT_BAGEL;
    public static final String TAG_DESC_BAKED = " " + PREFIX_TAG + VALID_TAG_BAKED;
    public static final String TAG_DESC_POPULAR = " " + PREFIX_TAG + VALID_TAG_POPULAR;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Cake&"; // '&' not allowed in names
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_ID_BAGEL = " " + PREFIX_ID + "231";
    public static final String INVALID_ID_BAGEL_2 = " " + PREFIX_ID + "-123232";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditItemDescriptor DESC_BAGEL;
    public static final EditCommand.EditItemDescriptor DESC_DONUT;

    static {
        DESC_BAGEL = new EditItemDescriptorBuilder().withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_BAGEL).withCount(VALID_COUNT_BAGEL).withTags(VALID_TAG_BAKED).build();
        DESC_DONUT = new EditItemDescriptorBuilder().withName(VALID_NAME_DONUT)
                .withId(VALID_ID_DONUT).withCount(VALID_COUNT_BAGEL)
                .withTags(VALID_TAG_BAKED, VALID_TAG_POPULAR).build();
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
     * - the inventory, filtered item list and selected item in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Inventory expectedInventory = new Inventory(actualModel.getInventory());
        List<Item> expectedFilteredList = new ArrayList<>(actualModel.getFilteredItemList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInventory, actualModel.getInventory());
        assertEquals(expectedFilteredList, actualModel.getFilteredItemList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the item at the given {@code targetIndex} in the
     * {@code model}'s inventory.
     */
    public static void showItemAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredItemList().size());

        Item person = model.getFilteredItemList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredItemList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredItemList().size());
    }

}
