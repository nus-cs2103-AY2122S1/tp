package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNT_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BAKED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POPULAR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.BAGEL;
import static seedu.address.testutil.TypicalItems.DONUT;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Inventory;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.testutil.ItemDescriptorBuilder;
import seedu.address.testutil.ItemBuilder;

public class AddCommandTest {

    private ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();

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
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        assertThrows(CommandException.class, AddCommand.MESSAGE_INCOMPLETE_INFO, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_existingItemNameDescription_restockSuccessful() {
        modelStub.addItem(BAGEL.updateCount(5));

        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL)
                .withCount(5)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS_REPLENISH, 5, VALID_NAME_BAGEL);
        ModelStubAcceptingItemAdded expectedModel = new ModelStubAcceptingItemAdded();
        expectedModel.addItem(BAGEL);
        expectedModel.restockItem(BAGEL, 5);

        assertCommandSuccess(addCommand, modelStub, expectedMessage, expectedModel);
    }

    @Test
    public void execute_existingItemIdDescription_restockSuccessful() {
        modelStub.addItem(BAGEL);

        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withId(VALID_ID_BAGEL)
                .withCount(5)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        String expectedMessage = String.format(AddCommand.MESSAGE_SUCCESS_REPLENISH, 5, VALID_NAME_BAGEL);
        ModelStubAcceptingItemAdded expectedModel = new ModelStubAcceptingItemAdded();
        expectedModel.addItem(BAGEL);
        expectedModel.restockItem(BAGEL, 5);

        assertCommandSuccess(addCommand, modelStub, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleMatches_failure() {
        modelStub.addItem(BAGEL);
        modelStub.addItem(DONUT);

        ItemDescriptor validDescriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_DONUT)
                .withId(VALID_ID_BAGEL)
                .withCount(5)
                .build();

        AddCommand addCommand = new AddCommand(validDescriptor);

        assertThrows(CommandException.class, AddCommand.MESSAGE_MULTIPLE_MATCHES, () -> addCommand.execute(modelStub));
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
        public void updateFilteredItemList(Predicate<Item> predicate) {
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
    }

}
