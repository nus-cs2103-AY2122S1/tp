package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE_PIE;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.Item;
import seedu.address.model.order.Order;
import seedu.address.testutil.TypicalItems;
import seedu.address.testutil.TypicalOrders;

class OrderTest {

    private Order order = new Order();

    @Test
    public void constructor_emptyConstructor_emptyOrderCreated() {
        Order order = new Order();

        assertEquals(order.getOrderItems(), new ArrayList<Item>());
    }

    @Test
    public void constructor_listOfItemsConstructor_typicalOrderCreated() {
        Order order = new Order(TypicalItems.getTypicalItems());

        assertEquals(order.getOrderItems(), TypicalItems.getTypicalItems());
    }

    @Test
    public void equals_ordersWithSameInternalList_equal() {
        Order order1 = TypicalOrders.getTypicalOrder();
        Order order2 = new Order(TypicalItems.getTypicalItems());
        assertEquals(order1, order2);
    }

    @Test
    public void addItem_nullItem_throwNullPointerException() {
        Order order = new Order();
        assertThrows(NullPointerException.class, () -> order.addItem(null));
    }

    @Test
    public void addItem_newItem_itemAdded() {
        Order order = new Order();
        order.addItem(APPLE_PIE);

        assertEquals(order.getOrderItems(), List.of(APPLE_PIE));
    }

    @Test
    public void addItem_ExistingItem_itemCountIncreased() {
        Order order = new Order();
        order.addItem(APPLE_PIE.updateCount(1));
        order.addItem(APPLE_PIE.updateCount(2));

        assertEquals(order.getOrderItems(), List.of(APPLE_PIE.updateCount(3)));
    }

    @Test
    public void removeItem_nullItem_throwNullPointerException() {
        Order order = new Order();
        assertThrows(NullPointerException.class, () -> order.removeItem(null, 0));
    }

    @Test
    public void removeItem_negativeCount_throwAssertionError() {
        Order order = new Order();
        assertThrows(AssertionError.class, () -> order.removeItem(APPLE_PIE, -1));
    }

    @Test
    public void removeItem_someExistingItem_itemCountDecreased() {

        order.addItem(APPLE_PIE);
        order.removeItem(APPLE_PIE, 2);

        Order expectedOrder = new Order();
        Item expectedPie = APPLE_PIE.updateCount(APPLE_PIE.getCount() - 2);
        expectedOrder.addItem(expectedPie);

        assertEquals(order, expectedOrder);
    }

    @Test
    public void removeItem_allExistingItem_itemRemoved() {

        order.addItem(APPLE_PIE);
        order.removeItem(APPLE_PIE, APPLE_PIE.getCount());

        Order expectedOrder = new Order();

        assertEquals(order, expectedOrder);
    }

}
