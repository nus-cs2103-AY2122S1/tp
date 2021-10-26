package seedu.address.model.sort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SortOrderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortOrder(null));
    }

    @Test
    public void constructor_invalidSortOrder_throwsIllegalArgumentException() {
        String invalidSortOrder = "";
        assertThrows(IllegalArgumentException.class, () -> new SortOrder(invalidSortOrder));
    }

    @Test
    public void isValidSortOrder() {
        // null sort order
        assertThrows(NullPointerException.class, () -> SortOrder.isValidSortOrder(null));

        // invalid sort order
        assertFalse(SortOrder.isValidSortOrder("")); // empty string
        assertFalse(SortOrder.isValidSortOrder(" ")); // spaces only
        assertFalse(SortOrder.isValidSortOrder("foo")); // unknown sort order

        // valid sort order
        assertTrue(SortOrder.isValidSortOrder("name"));
        assertTrue(SortOrder.isValidSortOrder("NAME"));
        assertTrue(SortOrder.isValidSortOrder("NaMe"));
        assertTrue(SortOrder.isValidSortOrder("TaG"));
    }
}
