package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE_PIE;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.item.Item;
import seedu.address.model.order.Order;
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.TypicalItems;
import seedu.address.testutil.TypicalOrders;

class OrderTest {

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
    public void addItem_normalItem_itemAdded() {
        Order order = new Order();
        order.addItem(APPLE_PIE);
        assertEquals(order.getOrderItems(), List.of(APPLE_PIE));
    }

    @Test
    public void removeItem_nullItem_throwNullPointerException() {
        Order order = new Order();
        assertThrows(NullPointerException.class, () -> order.removeItem(null));
    }

    @Test
    public void removeItem_normalItem_itemRemoved() {
        Order order = TypicalOrders.getTypicalOrder();
        List<Item> expectedItems = TypicalItems.getTypicalItems();
        order.removeItem(APPLE_PIE);
        expectedItems.remove(APPLE_PIE);

        assertEquals(order.getOrderItems(), expectedItems);
    }

    @Test
    public void removeItem_onlyNameMatches_itemRemoved() {
        Order order = TypicalOrders.getTypicalOrder();
        List<Item> expectedItems = TypicalItems.getTypicalItems();
        expectedItems.remove(APPLE_PIE);

        Item applePieWithOnlyName = new ItemBuilder()
                .withName("Apple Pie")
                .withId("416386")
                .withCount("5")
                .withTags("baked").build();

        order.removeItem(applePieWithOnlyName);

        assertEquals(order.getOrderItems(), expectedItems);
    }

    @Test
    public void removeItem_onlyIdMatches_itemRemoved() {
        Order order = TypicalOrders.getTypicalOrder();
        List<Item> expectedItems = TypicalItems.getTypicalItems();
        expectedItems.remove(APPLE_PIE);

        Item applePieWithOnlyId = new ItemBuilder()
                .withName(StringUtil.generateRandomString())
                .withId("222222")
                .withCount("5")
                .withTags("baked").build();

        order.removeItem(applePieWithOnlyId);

        assertEquals(order.getOrderItems(), expectedItems);
    }
}
