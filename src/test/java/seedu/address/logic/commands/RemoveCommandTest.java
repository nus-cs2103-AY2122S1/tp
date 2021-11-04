package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COSTPRICE_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNT_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BAKED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.model.display.DisplayMode.DISPLAY_INVENTORY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.BAGEL;
import static seedu.address.testutil.TypicalItems.DONUT;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import org.junit.jupiter.api.Test;

import seedu.address.model.BookKeeping;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TransactionList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.testutil.ItemDescriptorBuilder;

public class RemoveCommandTest {

    private ModelManager model = new ModelManager(getTypicalInventory(), new UserPrefs(),
            new TransactionList(), new BookKeeping());

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_removeExistingItemByName_success() {
        model.addItem(BAGEL);

        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withCount(1).build();
        RemoveCommand removeCommand = new RemoveCommand(bagelDescriptor);

        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.addItem(BAGEL.updateCount(BAGEL.getCount() - 1));

        String expectedMessage = String.format(RemoveCommand.MESSAGE_SUCCESS, 1, BAGEL.getName());

        CommandTestUtil.assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removeExistingItemById_success() {
        model.addItem(BAGEL);

        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withId(VALID_ID_BAGEL).withCount(1).build();
        RemoveCommand removeCommand = new RemoveCommand(bagelDescriptor);

        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.addItem(BAGEL.updateCount(BAGEL.getCount() - 1));

        String expectedMessage = String.format(RemoveCommand.MESSAGE_SUCCESS, 1, BAGEL.getName());

        CommandTestUtil.assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removeExistingItemByNameAndId_success() {
        model.addItem(BAGEL);

        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder().withId(VALID_ID_BAGEL)
                .withName(VALID_NAME_BAGEL).withCount(1).build();
        RemoveCommand removeCommand = new RemoveCommand(bagelDescriptor);

        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.addItem(BAGEL.updateCount(BAGEL.getCount() - 1));

        String expectedMessage = String.format(RemoveCommand.MESSAGE_SUCCESS, 1, BAGEL.getName());

        CommandTestUtil.assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_extraPriceFlags_success() {
        model.addItem(BAGEL);

        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder().withId(VALID_ID_BAGEL)
                .withName(VALID_NAME_BAGEL).withCount(1).withCostPrice(VALID_COSTPRICE_BAGEL).build();
        RemoveCommand removeCommand = new RemoveCommand(bagelDescriptor);

        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.addItem(BAGEL.updateCount(BAGEL.getCount() - 1));

        String message = String.format(RemoveCommand.MESSAGE_SUCCESS, 1, BAGEL.getName());
        String expectedMessage = message + "\n" + RemoveCommand.MESSAGE_EXTRA_PRICE_FLAGS;

        CommandTestUtil.assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_extraTagFlags_success() {
        model.addItem(BAGEL);

        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder().withId(VALID_ID_BAGEL)
                .withName(VALID_NAME_BAGEL).withCount(1).withTags(VALID_TAG_BAKED).build();
        RemoveCommand removeCommand = new RemoveCommand(bagelDescriptor);

        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.addItem(BAGEL.updateCount(BAGEL.getCount() - 1));

        String message = String.format(RemoveCommand.MESSAGE_SUCCESS, 1, BAGEL.getName());
        String expectedMessage = message + "\n" + RemoveCommand.MESSAGE_EXTRA_TAG_FLAGS;

        CommandTestUtil.assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_idExistNonexistentName_throwsCommandException() {
        model.addItem(BAGEL);
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName("boo").withId(VALID_ID_BAGEL).withCount(VALID_COUNT_BAGEL).build();

        RemoveCommand removeCommand = new RemoveCommand(bagelDescriptor);
        String expectedMessage = RemoveCommand.MESSAGE_NAME_NOT_FOUND;

        Model expectedModel = new ModelManager(model.getInventory(), model.getUserPrefs(),
                new TransactionList(), new BookKeeping());

        assertCommandFailure(removeCommand, model, expectedModel, expectedMessage);
    }

    @Test
    public void execute_nameExistNonexistentId_throwsCommandException() {
        model.addItem(BAGEL);
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId("182018").withCount(VALID_COUNT_BAGEL).build();

        RemoveCommand removeCommand = new RemoveCommand(bagelDescriptor);
        String expectedMessage = RemoveCommand.MESSAGE_ID_NOT_FOUND;

        Model expectedModel = new ModelManager(model.getInventory(), model.getUserPrefs(),
                new TransactionList(), new BookKeeping());

        assertCommandFailure(removeCommand, model, expectedModel, expectedMessage);
    }

    @Test
    public void execute_removeAllOfItem_success() {
        model.addItem(BAGEL);

        int count = BAGEL.getCount();
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder().withId(VALID_ID_BAGEL)
                .withName(VALID_NAME_BAGEL).withCount(BAGEL.getCount()).build();
        RemoveCommand removeCommand = new RemoveCommand(bagelDescriptor);

        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.addItem(BAGEL);
        expectedModel.setItem(BAGEL, BAGEL.updateCount(0));

        String expectedMessage = String.format(RemoveCommand.MESSAGE_SUCCESS, BAGEL.getCount(), BAGEL.getName());

        CommandTestUtil.assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonexistentItem_throwsCommandException() {
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL)
                .withCount(1).build();

        RemoveCommand removeCommand = new RemoveCommand(bagelDescriptor);
        String expectedMessage = RemoveCommand.MESSAGE_ITEM_NOT_FOUND;

        assertCommandFailure(removeCommand, model, expectedMessage);
    }

    @Test
    public void execute_multipleMatches_throwsCommandException() {
        model.addItem(BAGEL);
        model.addItem(DONUT);

        ItemDescriptor descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_DONUT)
                .withCount(1).build();

        RemoveCommand removeCommand = new RemoveCommand(descriptor);
        String expectedMessage = RemoveCommand.MESSAGE_MULTIPLE_MATCHES;

        Model expectedModel = new ModelManager(model.getInventory(), model.getUserPrefs(),
                new TransactionList(), new BookKeeping());
        expectedModel.updateFilteredItemList(DISPLAY_INVENTORY, x-> x.equals(BAGEL) || x.equals(DONUT));

        assertCommandFailure(removeCommand, model, expectedModel, expectedMessage);
    }

    @Test
    public void execute_removeTooMuch_failure() {
        model.addItem(BAGEL);

        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL)
                .withCount(BAGEL.getCount() + 1).build();
        RemoveCommand removeCommand = new RemoveCommand(bagelDescriptor);

        String expectedMessage = String.format(
                RemoveCommand.MESSAGE_INSUFFICIENT_ITEM, BAGEL.getCount(), BAGEL.getName());

        assertCommandFailure(removeCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        ItemDescriptor bagel = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).withCount(1).build();
        ItemDescriptor donut = new ItemDescriptorBuilder().withName(VALID_NAME_DONUT).withCount(1).build();
        RemoveCommand removeBagelCommand = new RemoveCommand(bagel);
        RemoveCommand removeDonutCommand = new RemoveCommand(donut);

        // same object -> returns true
        assertTrue(removeBagelCommand.equals(removeBagelCommand));

        // same values -> returns true
        RemoveCommand removeBagelCommandCopy = new RemoveCommand(bagel);
        assertTrue(removeBagelCommand.equals(removeBagelCommandCopy));

        // different types -> returns false
        assertFalse(removeBagelCommand.equals(1));

        // null -> returns false
        assertFalse(removeBagelCommand.equals(null));

        // different item -> returns false
        assertFalse(removeBagelCommand.equals(removeDonutCommand));
    }
}
