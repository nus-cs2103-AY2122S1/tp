package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.Date;

class OrderBookTest {
    private final OrderBook orderBook = new OrderBook();
    // project checkstyle blocks me from naming this in all caps as per normal convention
    private final Order testorder = new Order(new Customer("John"), new Date("20th august 2021"),
            new Amount("100.25"));

    @Test
    public void hasOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderBook.hasOrder(null));
    }

    @Test
    public void hasOrder_orderInList_returnsTrue() {
        orderBook.add(testorder);
        assertTrue(orderBook.hasOrder(testorder));
    }

    @Test
    public void hasOrder_orderNotInList_returnsFalse() {
        assertFalse(orderBook.hasOrder(testorder));
    }

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderBook.add(null));
    }

    @Test
    public void remove_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderBook.remove(null));
    }

    @Test
    public void remove_orderDoesNotExist_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> orderBook.remove(testorder));
    }

    @Test
    public void remove_existingOrder_removesOrder() {
        orderBook.add(testorder);
        orderBook.remove(testorder);
        assertFalse(orderBook.hasOrder(testorder));
    }

    @Test
    public void markComplete_existingOrder_marksOrder() {
        orderBook.add(testorder);
        orderBook.markComplete(testorder);
        assertTrue(testorder.getIsComplete());
    }
}
