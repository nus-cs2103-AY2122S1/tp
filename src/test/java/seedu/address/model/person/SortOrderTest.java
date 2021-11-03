package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_ORDER_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_ORDER_DESCENDING;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SortOrderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortOrder(null));
    }

    @Test
    public void constructor_invalidSortingOrder_throwsIllegalArgumentException() {
        String invalidSortingOrder = "";
        assertThrows(IllegalArgumentException.class, () -> new SortOrder(invalidSortingOrder));
    }

    @Test
    public void isValidSortBy() {
        // null phone number
        assertThrows(NullPointerException.class, () -> SortOrder.isValidSortBy(null));

        // invalid sorting orders
        assertFalse(SortOrder.isValidSortBy("")); // empty string
        assertFalse(SortOrder.isValidSortBy(" ")); // spaces only
        assertFalse(SortOrder.isValidSortBy("123")); // not "a" or "d"
        assertFalse(SortOrder.isValidSortBy("hello")); // not "a" or "d"

        // valid sorting orders
        assertTrue(SortOrder.isValidSortBy("a")); // sorting order ascending
        assertTrue(SortOrder.isValidSortBy("d")); // sorting order descending
    }

    @Test
    public void isAscendingTest() {
        SortOrder sortOrder = new SortOrder(VALID_SORT_ORDER_ASCENDING);
        assertTrue(sortOrder.isAscending());

        sortOrder = new SortOrder(VALID_SORT_ORDER_DESCENDING);
        assertFalse(sortOrder.isAscending());
    }

    @Test
    public void toStringTest() {
        SortOrder sortOrder = new SortOrder(VALID_SORT_ORDER_ASCENDING);
        assertTrue(sortOrder.toString().equals("ascending"));

        sortOrder = new SortOrder(VALID_SORT_ORDER_DESCENDING);
        assertTrue(sortOrder.toString().equals("descending"));

        String invalidSortingOrder = "randomString";
        assertThrows(IllegalArgumentException.class, () -> new SortOrder(invalidSortingOrder).toString());
    }

    @Test
    public void equals() {
        SortOrder sortOrder = new SortOrder(VALID_SORT_ORDER_ASCENDING);

        // same values -> returns true
        SortOrder toCopy = new SortOrder(VALID_SORT_ORDER_ASCENDING);
        assertTrue(sortOrder.equals(toCopy));

        // same object -> returns true
        assertTrue(sortOrder.equals(sortOrder));

        // null -> returns false
        assertFalse(sortOrder.equals(null));

        // different type -> returns false
        assertFalse(sortOrder.equals(5));

        // different Phone -> returns false
        SortOrder different = new SortOrder(VALID_SORT_ORDER_DESCENDING);
        assertFalse(sortOrder.equals(different));

    }
}
