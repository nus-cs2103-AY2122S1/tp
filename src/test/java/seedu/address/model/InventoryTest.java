package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POPULAR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE_PIE;
import static seedu.address.testutil.TypicalItems.BAGEL;
import static seedu.address.testutil.TypicalItems.DONUT;
import static seedu.address.testutil.TypicalItems.getTypicalInventory;
import static seedu.address.testutil.TypicalOrders.getTypicalTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.model.item.exceptions.ItemNotFoundException;
import seedu.address.model.order.Order;
import seedu.address.model.order.TransactionRecord;
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.ItemDescriptorBuilder;
import seedu.address.testutil.TypicalItems;
import seedu.address.testutil.TypicalOrders;

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

        Item editedPie = new ItemBuilder(APPLE_PIE).withTags(VALID_TAG_POPULAR).build();
        assertTrue(inventory.hasItem(editedPie));
    }

    @Test
    public void getItem_itemInInventory_returnsItem() {
        inventory.addItem(BAGEL);

        // Search by name
        ItemDescriptor descriptor = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).build();
        assertEquals(inventory.getItems(descriptor), List.of(BAGEL));

        // Search by id
        descriptor = new ItemDescriptorBuilder().withId(VALID_ID_BAGEL).build();
        assertEquals(inventory.getItems(descriptor), List.of(BAGEL));

        // Search by name and id
        descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();
        assertEquals(inventory.getItems(descriptor), List.of(BAGEL));
    }

    @Test
    public void getItem_itemNotInInventory_returnEmptyList() {
        inventory.addItem(DONUT);

        // Search by name
        ItemDescriptor descriptor = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).build();
        assertEquals(inventory.getItems(descriptor), List.of());

        // Search by id
        descriptor = new ItemDescriptorBuilder().withId(VALID_ID_BAGEL).build();
        assertEquals(inventory.getItems(descriptor), List.of());

        // Search by name and id
        descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();
        assertEquals(inventory.getItems(descriptor), List.of());
    }

    @Test
    public void getItem_multipleMatches_returnMultiple() {
        inventory.addItem(DONUT);
        inventory.addItem(BAGEL);

        ItemDescriptor descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_DONUT).build();
        assertEquals(inventory.getItems(descriptor), List.of(DONUT, BAGEL));
    }

    @Test
    public void restockItem_success() {
        inventory.addItem(BAGEL.updateCount(5));
        inventory.restockItem(BAGEL, 5);

        Inventory expectedInventory = new Inventory();
        expectedInventory.addItem(BAGEL.updateCount(10));

        assertEquals(inventory, expectedInventory);
    }

    @Test
    public void addItem_moreThan1() {
        inventory.addItems(new ArrayList<Item>(Arrays.asList(DONUT, BAGEL)));

        Inventory expectedInventory = new Inventory();
        expectedInventory.addItem(DONUT);
        expectedInventory.addItem(BAGEL);

        assertEquals(inventory, expectedInventory);
    }

    @Test
    public void hashCodeTest() {
        inventory.addItems(new ArrayList<Item>(Arrays.asList(DONUT, BAGEL)));

        Inventory expectedInventory = new Inventory();
        expectedInventory.addItem(DONUT);
        expectedInventory.addItem(BAGEL);

        assertEquals(inventory.hashCode(), expectedInventory.hashCode());
    }

    @Test
    public void restockItem_itemNotInInventory_throwsException() {
        inventory.addItem(BAGEL);

        assertThrows(ItemNotFoundException.class, () -> inventory.restockItem(DONUT, 5));
    }

    @Test
    public void restockItem_nullItem_throwException() {
        assertThrows(NullPointerException.class, () -> inventory.restockItem(null, 0));
    }

    @Test
    public void removeItem_removeAll_success() {
        inventory.addItem(APPLE_PIE);
        inventory.removeItem(APPLE_PIE, APPLE_PIE.getCount());

        assertFalse(inventory.hasItem(APPLE_PIE));
    }

    @Test
    public void removeItem_removeSome_success() {
        inventory.addItem(APPLE_PIE);

        int expectedCount = APPLE_PIE.getCount() - 1;
        Inventory expectedInventory = new Inventory();
        expectedInventory.addItem(APPLE_PIE.updateCount(expectedCount));

        inventory.removeItem(APPLE_PIE, 1);
        assertEquals(inventory, expectedInventory);
    }

    @Test
    public void removeItemByName_removeTooMuch_failure() {
        inventory.addItem(APPLE_PIE);

        int amount = APPLE_PIE.getCount() + 1;
        assertThrows(IllegalArgumentException.class, () -> inventory.removeItem(APPLE_PIE, amount));
        assertTrue(inventory.hasItem(APPLE_PIE));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> inventory.getItemList().remove(0));
    }

    @Test
    public void transactOrder_nullOrder_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> inventory.transactOrder(null));
    }

    @Test
    public void transactOrder_orderWithUnexistingItem_inventoryUnchanged() {
        // count of items in order matches that in inventory
        Inventory typicalInventory = TypicalItems.getTypicalInventory();
        Order abnormalOrder = TypicalOrders.getOrderWithUnexistingItem();
        typicalInventory.transactOrder(abnormalOrder);

        Inventory expectedInventory = TypicalItems.getTypicalInventory();

        assertEquals(typicalInventory.getItemList(), expectedInventory.getItemList());
    }

    @Test
    public void transactOrder_typicalOrder_getRightTransactionRecord() {
        Inventory typicalInventory = TypicalItems.getTypicalInventory();
        Order order = TypicalOrders.getTypicalOrder();
        TransactionRecord transaction = typicalInventory.transactOrder(order);

        TransactionRecord expectedTransaction = getTypicalTransaction();

        // Test items are the same
        assertEquals(transaction.getOrderItems(), expectedTransaction.getOrderItems());
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
