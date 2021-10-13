package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POPULAR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE_PIE;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.Name;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.testutil.ItemBuilder;

public class InventoryTest {

    private final Inventory inventory = new Inventory();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), inventory.getItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inventory.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInventory_replacesData() {
        Inventory newData = getTypicalInventory();
        inventory.resetData(newData);
        assertEquals(newData, inventory);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Item editedPie = new ItemBuilder(APPLE_PIE).withTags(VALID_TAG_POPULAR).build();
        List<Item> newItems = Arrays.asList(APPLE_PIE, editedPie);
        InventoryStub newData = new InventoryStub(newItems);

        assertThrows(DuplicateItemException.class, () -> inventory.resetData(newData));
    }

    @Test
    public void hasItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inventory.hasItem((Item) null));
        assertThrows(NullPointerException.class, () -> inventory.hasItem((Name) null));
        assertThrows(NullPointerException.class, () -> inventory.hasItem((String) null));
    }

    @Test
    public void hasInventory_itemNotInInventory_returnsFalse() {
        assertFalse(inventory.hasItem(APPLE_PIE));
    }

    @Test
    public void hasItem_itemInInventory_returnsTrue() {
        inventory.addItem(APPLE_PIE);
        assertTrue(inventory.hasItem(APPLE_PIE));
    }

    @Test
    public void hasItem_itemWithSameIdentityFieldsInInventory_returnsTrue() {
        inventory.addItem(APPLE_PIE);

        // Search by item
        Item editedPie = new ItemBuilder(APPLE_PIE).withTags(VALID_TAG_POPULAR).build();
        assertTrue(inventory.hasItem(editedPie));

        // Search by name
        assertTrue(inventory.hasItem(APPLE_PIE.getName()));

        // Search by id
        assertTrue(inventory.hasItem(APPLE_PIE.getId()));
    }

    @Test
    public void removeItemByName_removeAll_success() {
        inventory.addItem(APPLE_PIE);

        assertEquals(APPLE_PIE, inventory.removeItem(APPLE_PIE.getName(), -1));
        assertFalse(inventory.hasItem(APPLE_PIE));
    }

    @Test
    public void removeItemById_removeAll_success() {
        inventory.addItem(APPLE_PIE);

        assertEquals(APPLE_PIE, inventory.removeItem(APPLE_PIE.getId(), -1));
        assertFalse(inventory.hasItem(APPLE_PIE));
    }

    @Test
    public void removeItemByName_someOfItem_success() {
        inventory.addItem(APPLE_PIE);

        int expectedCount = APPLE_PIE.getCount() - 1;
        Inventory expectedInventory = new Inventory();
        expectedInventory.addItem(APPLE_PIE.updateCount(expectedCount));

        assertEquals(APPLE_PIE.updateCount(1), inventory.removeItem(APPLE_PIE.getName(), 1));
        assertEquals(inventory, expectedInventory);
    }


    @Test
    public void removeItemById_someOfItem_success() {
        inventory.addItem(APPLE_PIE);

        int expectedCount = APPLE_PIE.getCount() - 1;
        Inventory expectedInventory = new Inventory();
        expectedInventory.addItem(APPLE_PIE.updateCount(expectedCount));

        assertEquals(APPLE_PIE.updateCount(1), inventory.removeItem(APPLE_PIE.getId(), 1));
        assertEquals(inventory, expectedInventory);
    }

    @Test
    public void removeItemByName_removeTooMuch_success() {
        inventory.addItem(APPLE_PIE);

        int amount = APPLE_PIE.getCount() + 1;

        assertEquals(APPLE_PIE, inventory.removeItem(APPLE_PIE.getName(), amount));
        assertFalse(inventory.hasItem(APPLE_PIE));
    }

    @Test
    public void removeItemById_removeTooMuch_success() {
        inventory.addItem(APPLE_PIE);

        int amount = APPLE_PIE.getCount() + 1;

        assertEquals(APPLE_PIE, inventory.removeItem(APPLE_PIE.getId(), amount));
        assertFalse(inventory.hasItem(APPLE_PIE));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> inventory.getItemList().remove(0));
    }

    /**
     * A stub ReadOnlyInventory whose item list can violate interface constraints.
     */
    private static class InventoryStub implements ReadOnlyInventory {
        private final ObservableList<Item> items = FXCollections.observableArrayList();

        InventoryStub(Collection<Item> items) {
            this.items.setAll(items);
        }

        @Override
        public ObservableList<Item> getItemList() {
            return items;
        }
    }

}
