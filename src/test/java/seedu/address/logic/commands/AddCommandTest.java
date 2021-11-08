package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COSTPRICE_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNT_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALESPRICE_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BAKED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POPULAR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.BAGEL;
import static seedu.address.testutil.TypicalItems.DONUT;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.BookKeeping;
import seedu.address.model.Inventory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.UserPrefs;
import seedu.address.model.display.DisplayMode;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.ItemDescriptorBuilder;
import seedu.address.testutil.TypicalBookkeeping;
import seedu.address.testutil.TypicalTransactions;

public class AddCommandTest {

    private ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
    private ModelManager model = new ModelManager(getTypicalInventory(), new UserPrefs(),
            TypicalTransactions.getTypicalTransactionList(), TypicalBookkeeping.getTypicalBookkeeping());

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_newItem_addSuccessful() throws Exception {
        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_BAGEL)
                .withTags(VALID_TAG_BAKED, VALID_TAG_POPULAR)
                .withCount(VALID_COUNT_BAGEL)
                .withSalesPrice(VALID_SALESPRICE_BAGEL)
                .withCostPrice(VALID_COSTPRICE_BAGEL)
                .build();

        Item validItem = new ItemBuilder(BAGEL)
                .withTags(VALID_TAG_BAKED, VALID_TAG_POPULAR)
                .build();

        CommandResult commandResult = new AddCommand(validDescriptor).execute(modelStub);
        ModelStubAcceptingItemAdded expectedModel = new ModelStubAcceptingItemAdded();
        expectedModel.addItem(validItem);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS_NEW, validItem), commandResult.getFeedbackToUser());
        assertEquals(modelStub, expectedModel);
    }

    @Test
    public void execute_newItemNoId_incompleteInfofailure() {
        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withCount(VALID_COUNT_BAGEL)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        assertThrows(CommandException.class, AddCommand.MESSAGE_INCOMPLETE_INFO, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_newItemNoName_incompleteInfofailure() {
        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withCount(1)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        assertThrows(CommandException.class, AddCommand.MESSAGE_INCOMPLETE_INFO, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_newItemNoCostPrice_incompleteInfofailure() {
        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_BAGEL)
                .withCount(VALID_COUNT_BAGEL)
                .withSalesPrice(VALID_SALESPRICE_BAGEL)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        assertThrows(CommandException.class, AddCommand.MESSAGE_INCOMPLETE_INFO, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_newItemNoSalesPrice_incompleteInfofailure() {
        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withId(VALID_ID_BAGEL)
                .withCount(VALID_COUNT_BAGEL)
                .withCostPrice(VALID_COSTPRICE_BAGEL)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        assertThrows(CommandException.class, AddCommand.MESSAGE_INCOMPLETE_INFO, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_existingItemNameDescription_restockSuccessful() {
        model.addItem(BAGEL);
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL)
                .withCount(VALID_COUNT_BAGEL).build();

        AddCommand addCommand = new AddCommand(bagelDescriptor);
        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS_REPLENISH, 5, VALID_NAME_BAGEL);

        BookKeeping bookKeeping = TypicalBookkeeping.getTypicalBookkeeping();
        bookKeeping.addCost(BAGEL.getCostPrice(), 5);
        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(),
                TypicalTransactions.getTypicalTransactionList(), bookKeeping);
        expectedModel.addItem(BAGEL);
        expectedModel.restockItem(BAGEL, 5);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_existingItemIdDescription_restockSuccessful() {
        model.addItem(BAGEL);
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder().withId(VALID_ID_BAGEL)
                .withCount(VALID_COUNT_BAGEL).build();

        AddCommand addCommand = new AddCommand(bagelDescriptor);
        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS_REPLENISH, 5, VALID_NAME_BAGEL);

        BookKeeping bookKeeping = TypicalBookkeeping.getTypicalBookkeeping();
        bookKeeping.addCost(BAGEL.getCostPrice(), 5);
        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(),
                TypicalTransactions.getTypicalTransactionList(), bookKeeping);
        expectedModel.addItem(BAGEL);
        expectedModel.restockItem(BAGEL, 5);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_idExistNonexistentName_throwsCommandException() {
        model.addItem(BAGEL);
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName("boo").withId(VALID_ID_BAGEL).withCount(VALID_COUNT_BAGEL).build();

        AddCommand addCommand = new AddCommand(bagelDescriptor);
        String expectedMessage = AddCommand.MESSAGE_NAME_NOT_FOUND;

        Model expectedModel = new ModelManager(model.getInventory(), model.getUserPrefs(),
                model.getTransactions(), model.getBookKeeping());

        assertCommandFailure(addCommand, model, expectedModel, expectedMessage);
    }

    @Test
    public void execute_nameExistNonexistentId_throwsCommandException() {
        model.addItem(BAGEL);
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId("182018").withCount(VALID_COUNT_BAGEL).build();

        AddCommand addCommand = new AddCommand(bagelDescriptor);
        String expectedMessage = AddCommand.MESSAGE_ID_NOT_FOUND;

        Model expectedModel = new ModelManager(model.getInventory(), model.getUserPrefs(),
                model.getTransactions(), model.getBookKeeping());

        assertCommandFailure(addCommand, model, expectedModel, expectedMessage);
    }

    @Test
    public void execute_extraPriceFlags_restockSuccessful() {
        model.addItem(BAGEL);
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withCount(VALID_COUNT_BAGEL)
                .withSalesPrice(VALID_SALESPRICE_BAGEL)
                .withCostPrice(VALID_COSTPRICE_BAGEL)
                .build();

        AddCommand addCommand = new AddCommand(bagelDescriptor);
        String replenishMessage = String.format(AddCommand.MESSAGE_SUCCESS_REPLENISH, 5, VALID_NAME_BAGEL);
        String expectedMessage = replenishMessage + "\n" + AddCommand.MESSAGE_EXTRA_PRICE_FLAGS;

        BookKeeping bookKeeping = TypicalBookkeeping.getTypicalBookkeeping();
        bookKeeping.addCost(BAGEL.getCostPrice(), 5);
        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(),
                TypicalTransactions.getTypicalTransactionList(), bookKeeping);
        expectedModel.addItem(BAGEL);
        expectedModel.restockItem(BAGEL, 5);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_extraTagFlags_restockSuccessful() {
        model.addItem(BAGEL);
        ItemDescriptor bagelDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withCount(VALID_COUNT_BAGEL).withTags(VALID_TAG_BAKED).build();

        AddCommand addCommand = new AddCommand(bagelDescriptor);
        String replenishMessage = String.format(AddCommand.MESSAGE_SUCCESS_REPLENISH, 5, VALID_NAME_BAGEL);
        String expectedMessage = replenishMessage + "\n" + AddCommand.MESSAGE_EXTRA_TAG_FLAGS;

        BookKeeping bookKeeping = TypicalBookkeeping.getTypicalBookkeeping();
        bookKeeping.addCost(BAGEL.getCostPrice(), 5);
        Model expectedModel = new ModelManager(getTypicalInventory(), new UserPrefs(),
                TypicalTransactions.getTypicalTransactionList(), bookKeeping);
        expectedModel.addItem(BAGEL);
        expectedModel.restockItem(BAGEL, 5);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleMatches_failure() {
        model.addItem(BAGEL);
        model.addItem(DONUT);

        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withId(VALID_ID_BAGEL)
                .withCount(5)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        assertThrows(CommandException.class, AddCommand.MESSAGE_MULTIPLE_MATCHES, () -> addCommand.execute(model));
    }

    @Test
    public void equals() {
        ItemDescriptor bagel = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).build();
        ItemDescriptor donut = new ItemDescriptorBuilder().withName(VALID_NAME_DONUT).build();
        AddCommand addBagelCommand = new AddCommand(bagel);
        AddCommand addDonutCommand = new AddCommand(donut);

        // same object -> returns true
        assertTrue(addBagelCommand.equals(addBagelCommand));

        // same values -> returns true
        AddCommand addBagelCommandCopy = new AddCommand(bagel);
        assertTrue(addBagelCommand.equals(addBagelCommandCopy));

        // different types -> returns false
        assertFalse(addBagelCommand.equals(1));

        // null -> returns false
        assertFalse(addBagelCommand.equals(null));

        // different item -> returns false
        assertFalse(addBagelCommand.equals(addDonutCommand));
    }

    /**
     * A Model stub that always accept the item being added. Assumed to only be able to hold one item.
     * Naively supports restocking items and getting items.
     * When {@code getItems} is called, return entire list.
     */
    private class ModelStubAcceptingItemAdded extends ModelStub {
        final ArrayList<Item> itemsAdded = new ArrayList<>();

        private int addedAmount = 0;

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return itemsAdded.stream().anyMatch(item::isSameItem);
        }

        @Override
        public void addItem(Item item) {
            requireNonNull(item);
            itemsAdded.add(item);
        }

        @Override
        public void restockItem(Item item, int amount) {
            requireNonNull(item);
            addedAmount = amount;
        }

        @Override
        public List<Item> getItems(ItemDescriptor itemDescriptor) {
            return itemsAdded;
        }

        @Override
        public void updateFilteredItemList(DisplayMode mode, Predicate<Item> predicate) {
        }

        @Override
        public ReadOnlyInventory getInventory() {
            return new Inventory();
        }

        @Override
        public boolean equals(Object obj) {
            // short circuit if same object
            if (obj == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(obj instanceof ModelStubAcceptingItemAdded)) {
                return false;
            }

            // state check
            ModelStubAcceptingItemAdded other = (ModelStubAcceptingItemAdded) obj;
            return itemsAdded.equals(other.itemsAdded) && addedAmount == other.addedAmount;
        }

        @Override
        public void addCostBookKeeping(Double cost, int amount) {

        }

        @Override
        public void addRevenueBookKeeping(Double revenue, int amount) {

        }
    }

}
