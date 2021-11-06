package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BookKeepingTest {

    private BookKeeping bookKeeping = new BookKeeping();

    @Test
    public void addCost_overflow_costKeptAtLimit() {
        bookKeeping.addCost(BookKeeping.LIMIT, 2);

        assertEquals(bookKeeping.getCost(), BookKeeping.LIMIT);
    }

    @Test
    public void addRevenue_overflow_costKeptAtLimit() {
        bookKeeping.addRevenue(BookKeeping.LIMIT, 2);

        assertEquals(bookKeeping.getRevenue(), BookKeeping.LIMIT);
    }

    @Test
    public void getProfit() {
        bookKeeping.addCost(5.0, 5);
        bookKeeping.addRevenue(10.0, 5);

        // Profit = (10 * 5) - (5 * 5) = 25
        assertEquals(bookKeeping.getProfit(), 25);
    }

    @Test
    public void equals() {
        // same values -> returns true
        BookKeeping bookKeepingCopy = new BookKeeping();
        assertTrue(bookKeeping.equals(bookKeepingCopy));

        // same object -> returns true
        assertTrue(bookKeeping.equals(bookKeeping));

        // null -> returns false
        assertFalse(bookKeeping.equals(null));

        // different types -> returns false
        assertFalse(bookKeeping.equals(5));

        // different values -> returns false
        bookKeepingCopy.addCost(5.0, 2);
        assertFalse(bookKeeping.equals(bookKeepingCopy));
    }

}
