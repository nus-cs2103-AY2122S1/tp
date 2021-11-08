package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COSTPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALESPRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Inventory;
import seedu.address.model.Model;
import seedu.address.model.display.Displayable;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.testutil.ItemDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_BAGEL = "Bagel";
    public static final String VALID_NAME_DONUT = "Donut";
    public static final String VALID_ID_BAGEL = "094021";
    public static final String VALID_ID_DONUT = "789013";
    public static final String VALID_COUNT_BAGEL = "5";
    public static final String VALID_COUNT_DONUT = "6";
    public static final String VALID_COSTPRICE_BAGEL = "5.0";
    public static final String VALID_COSTPRICE_DONUT = "6.0";
    public static final String VALID_SALESPRICE_BAGEL = "6.0";
    public static final String VALID_SALESPRICE_DONUT = "7.0";
    public static final String VALID_TAG_BAKED = "baked";
    public static final String VALID_TAG_POPULAR = "popular";

    public static final String NAME_DESC_BAGEL = " " + PREFIX_NAME + VALID_NAME_BAGEL;
    public static final String NAME_DESC_DONUT = " " + PREFIX_NAME + VALID_NAME_DONUT;
    public static final String ID_DESC_BAGEL = " " + PREFIX_ID + VALID_ID_BAGEL;
    public static final String ID_DESC_DONUT = " " + PREFIX_ID + VALID_ID_DONUT;
    public static final String COUNT_DESC_BAGEL = " " + PREFIX_COUNT + VALID_COUNT_BAGEL;
    public static final String COUNT_DESC_DONUT = " " + PREFIX_COUNT + VALID_COUNT_DONUT;
    public static final String SALESPRICE_DESC_BAGEL = " " + PREFIX_SALESPRICE + VALID_SALESPRICE_BAGEL;
    public static final String COSTPRICE_DESC_BAGEL = " " + PREFIX_COSTPRICE + VALID_COSTPRICE_BAGEL;
    public static final String TAG_DESC_BAKED = " " + PREFIX_TAG + VALID_TAG_BAKED;
    public static final String TAG_DESC_POPULAR = " " + PREFIX_TAG + VALID_TAG_POPULAR;

    public static final String INVALID_NAME_SPECIAL_CHAR = "Cake$";
    public static final String INVALID_NAME_DESC =
            " " + PREFIX_NAME + INVALID_NAME_SPECIAL_CHAR; // '&' not allowed in names
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_ID_LETTER = " " + PREFIX_ID + "231a";
    public static final String INVALID_ID_SPECIAL_CHAR = " " + PREFIX_ID + "231*";
    public static final String INVALID_ID_NEGATIVE_NUMBER = " " + PREFIX_ID + "-123232";
    public static final String INVALID_ID_SEVEN_DIGITS = " " + PREFIX_ID + "1234567";
    public static final String INVALID_COUNT_LETTER = " " + PREFIX_COUNT + "abc";
    public static final String INVALID_COUNT_ZERO = " " + PREFIX_COUNT + "0";
    public static final String INVALID_COUNT_NEGATIVE_VALUE = " " + PREFIX_COUNT + "-1";

    public static final ItemDescriptor DESC_BAGEL;
    public static final ItemDescriptor DESC_DONUT;

    static {
        DESC_BAGEL = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_BAGEL).withCount(VALID_COUNT_BAGEL)
                .withCostPrice(VALID_COSTPRICE_BAGEL).withSalesPrice(VALID_SALESPRICE_BAGEL)
                .withTags(VALID_TAG_BAKED).build();
        DESC_DONUT = new ItemDescriptorBuilder().withName(VALID_NAME_DONUT)
                .withId(VALID_ID_DONUT).withCount(VALID_COUNT_BAGEL)
                .withCostPrice(VALID_COSTPRICE_DONUT).withSalesPrice(VALID_SALESPRICE_DONUT)
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
        List<Displayable> expectedFilteredList = new ArrayList<>(actualModel.getFilteredDisplayList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInventory, actualModel.getInventory());
        assertEquals(expectedFilteredList, actualModel.getFilteredDisplayList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the inventory, filtered item list and selected item in {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandFailure(Command command, Model actualModel,
                                            Model expectedModel, String expectedMessage) {
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(actualModel.getInventory(), expectedModel.getInventory());
        assertEquals(expectedModel.getFilteredDisplayList(), actualModel.getFilteredDisplayList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the item at the given {@code targetIndex} in the
     * {@code model}'s inventory.
     */
    public static void showItemAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredDisplayList().size());

        Item item = (Item) model.getFilteredDisplayList().get(targetIndex.getZeroBased());
        final String[] splitName = item.getName().fullName.split("\\s+");
        model.updateFilteredItemList(DISPLAY_INVENTORY,
                new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredDisplayList().size());
    }

}
