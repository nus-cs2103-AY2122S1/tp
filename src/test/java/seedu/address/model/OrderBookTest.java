package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrders.SALESORDER1;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.order.Order;

public class OrderBookTest {

    private final OrderBook orderBook = new OrderBook();

    @Test
    public void orderBookConstructor() {
        assertEquals(Collections.emptyList(), orderBook.getOrderList());
    }

    @Test
    public void orderBookResetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyOrderBook_replacesData() {
        OrderBook newData = getTypicalOrderBook();
        newData.resetData(newData);
        assertEquals(newData, orderBook);
    }


    @Test
    public void hasOrders_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderBook.hasOrder(null));
    }

    @Test
    public void hasOrder_orderNotInOrderBook_returnsFalse() {
        assertFalse(orderBook.hasOrder(SALESORDER1));
    }

    @Test
    public void hasOrder_ordersInOrderBook_returnsTrue() {
        orderBook.addOrder(SALESORDER1);
        assertTrue(orderBook.hasOrder(SALESORDER1));
    }


    @Test
    public void getOrderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> orderBook.getOrderList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskBook whose tasks list can violate interface constraints.
     */
    private static class OrderBookStub implements ReadOnlyOrderBook {
        private final ObservableList<Order> orders = FXCollections.observableArrayList();

        OrderBookStub(Collection<Order> orders) {
            this.orders.setAll(orders);
        }

        @Override
        public ObservableList<Order> getOrderList() {
            return orders;
        }
    }

}
