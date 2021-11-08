package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.order.exceptions.OrderNotFoundException;
import seedu.address.testutil.OrderBuilder;

class UniqueOrderListTest {
    private final UniqueOrderList uniqueOrderList = new UniqueOrderList();
    // project checkstyle blocks me from naming this in all caps as per normal convention
    private final Order testorder = new OrderBuilder().build();

    @Test
    public void hasOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.hasOrder(null));
    }

    @Test
    public void hasOrder_orderInList_returnsTrue() {
        uniqueOrderList.add(testorder);
        assertTrue(uniqueOrderList.hasOrder(testorder));
    }

    @Test
    public void hasOrder_orderNotInList_returnsFalse() {
        assertFalse(uniqueOrderList.hasOrder(testorder));
    }

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.add(null));
    }

    @Test
    public void remove_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.remove(null));
    }

    @Test
    public void remove_orderDoesNotExist_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> uniqueOrderList.remove(testorder));
    }

    @Test
    public void remove_existingOrder_removesOrder() {
        uniqueOrderList.add(testorder);
        uniqueOrderList.remove(testorder);
        assertFalse(uniqueOrderList.hasOrder(testorder));
    }

    @Test
    public void markComplete_existingOrder_marksOrder() {
        uniqueOrderList.add(testorder);
        uniqueOrderList.markComplete(testorder);
        assertTrue(testorder.getIsComplete());
    }
}
