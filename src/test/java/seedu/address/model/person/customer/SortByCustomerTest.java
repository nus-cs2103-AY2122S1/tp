package seedu.address.model.person.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_EMAIL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_PHONE;
import static seedu.address.logic.commands.CustomerCommandTestUtil.VALID_SORT_BY_LOYALTY_POINTS;
import static seedu.address.logic.commands.EmployeeCommandTestUtil.INVALID_SORT_BY_TAG;

import org.junit.jupiter.api.Test;



public class SortByCustomerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortByCustomer(null));
    }

    @Test
    public void constructor_invalidSortBy_throwsIllegalArgumentException() {
        String invalidSortBy = "";
        assertThrows(IllegalArgumentException.class, () -> new SortByCustomer(invalidSortBy));
    }

    @Test
    public void isValidSortBy() {
        // null
        assertThrows(NullPointerException.class, () -> SortByCustomer.isValidSortBy(null));

        // invalid sorting orders
        assertFalse(SortByCustomer.isValidSortBy("")); // empty string
        assertFalse(SortByCustomer.isValidSortBy(" ")); // spaces only
        assertFalse(SortByCustomer.isValidSortBy("123")); // not valid prefix
        assertFalse(SortByCustomer.isValidSortBy("hello")); // not valid prefix
        assertFalse(SortByCustomer.isValidSortBy("t")); // cannot sort by tags
        assertFalse(SortByCustomer.isValidSortBy("sh")); // cannot sort by shifts

        // valid sorting orders
        assertTrue(SortByCustomer.isValidSortBy("n")); // Sort by name
        assertTrue(SortByCustomer.isValidSortBy("a")); // Sort by address
        assertTrue(SortByCustomer.isValidSortBy("e")); // Sort by email
        assertTrue(SortByCustomer.isValidSortBy("p")); // Sort by phone number
        assertTrue(SortByCustomer.isValidSortBy("lp")); // Sort by leaves
    }

    @Test
    public void toStringTest() {
        SortByCustomer sortBy = new SortByCustomer(VALID_SORT_BY_ADDRESS);
        assertTrue(sortBy.toString().equals("address"));

        sortBy = new SortByCustomer(VALID_SORT_BY_EMAIL);
        assertTrue(sortBy.toString().equals("email"));

        sortBy = new SortByCustomer(VALID_SORT_BY_PHONE);
        assertTrue(sortBy.toString().equals("phone"));

        sortBy = new SortByCustomer(VALID_SORT_BY_NAME);
        assertTrue(sortBy.toString().equals("name"));

        sortBy = new SortByCustomer(VALID_SORT_BY_LOYALTY_POINTS);
        assertTrue(sortBy.toString().equals("loyalty points"));

        String invalidSortBy = "1234abc";
        assertThrows(IllegalArgumentException.class, () -> new SortByCustomer(invalidSortBy).toString());

        // Sorting by tag is invalid
        assertThrows(IllegalArgumentException.class, () -> new SortByCustomer(INVALID_SORT_BY_TAG).toString());
    }

    @Test
    public void equals() {
        SortByCustomer sortBy = new SortByCustomer(VALID_SORT_BY_LOYALTY_POINTS);

        // same values -> returns true
        SortByCustomer toCopy = new SortByCustomer(VALID_SORT_BY_LOYALTY_POINTS);
        assertTrue(sortBy.equals(toCopy));

        // same object -> returns true
        assertTrue(sortBy.equals(sortBy));

        // null -> returns false
        assertFalse(sortBy.equals(null));

        // different type -> returns false
        assertFalse(sortBy.equals(5));

        // different SortBy -> returns false
        SortByCustomer different = new SortByCustomer(VALID_SORT_BY_ADDRESS);
        assertFalse(sortBy.equals(different));

        different = new SortByCustomer(VALID_SORT_BY_NAME);
        assertFalse(sortBy.equals(different));

        different = new SortByCustomer(VALID_SORT_BY_EMAIL);
        assertFalse(sortBy.equals(different));

        different = new SortByCustomer(VALID_SORT_BY_PHONE);
        assertFalse(sortBy.equals(different));
    }
}
