package seedu.address.model.salesorder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.Date;

class SalesBookTest {
    private final SalesBook salesBook = new SalesBook();
    private final SalesOrder TESTORDER = new SalesOrder(new Customer("John"), new Date("20th august 2021"),
            new Amount("100.25"));

    @Test
    public void hasOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.hasOrder(null));
    }

    @Test
    public void hasOrder_orderInList_returnsTrue() {
        salesBook.add(TESTORDER);
        assertTrue(salesBook.hasOrder(TESTORDER));
    }

    @Test
    public void hasOrder_orderNotInList_returnsFalse() {
        assertFalse(salesBook.hasOrder(TESTORDER));
    }

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.add(null));
    }

    @Test
    public void remove_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.remove(null));
    }

    @Test
    public void remove_orderDoesNotExist_throwsOrderNotFoundException() {
        assertThrows(SalesOrderNotFoundException.class, () -> salesBook.remove(TESTORDER));
    }

    @Test
    public void remove_existingOrder_removesOrder() {
        salesBook.add(TESTORDER);
        salesBook.remove(TESTORDER);
        assertFalse(salesBook.hasOrder(TESTORDER));
    }

    @Test
    public void markComplete_existingOrder_marksOrder() {
        salesBook.add(TESTORDER);
        salesBook.markComplete(TESTORDER);
        assertTrue(TESTORDER.getIsComplete());
    }
}
