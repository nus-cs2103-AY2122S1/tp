package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Inventory;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyInventory;
import seedu.address.model.item.Item;
import seedu.address.testutil.ItemBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_itemAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
        Item validItem = new ItemBuilder().build();

        CommandResult commandResult = new AddCommand(validItem).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validItem), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validItem), modelStub.itemsAdded);
    }

    //    @Test
    //    public void execute_duplicateItem() throws CommandException {
    //        ModelStub modelStub = new ModelStub();
    //        Item validItem = new ItemBuilder().build();
    //        AddCommand addCommand = new AddCommand(validItem);
    //
    //        Item validItemDouble = new ItemBuilder().withCount("10").build();
    //        ModelStub modelStub2 = new ModelStubWithItem(validItemDouble);
    //
    //        addCommand.execute(modelStub);
    //    }
    // TODO: Guys pls help i have no idea how to do the test for this

    @Test
    public void equals() {
        Item bagel = new ItemBuilder().withName("Bagel").build();
        Item donut = new ItemBuilder().withName("Donut").build();
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
     * A Model stub that contains a single item.
     */
    private class ModelStubWithItem extends ModelStub {
        private final Item item;

        ModelStubWithItem(Item item) {
            requireNonNull(item);
            this.item = item;
        }

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return this.item.isSameItem(item);
        }
    }

    /**
     * A Model stub that always accept the item being added.
     */
    private class ModelStubAcceptingItemAdded extends ModelStub {
        final ArrayList<Item> itemsAdded = new ArrayList<>();

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
        public ReadOnlyInventory getInventory() {
            return new Inventory();
        }
    }

}
