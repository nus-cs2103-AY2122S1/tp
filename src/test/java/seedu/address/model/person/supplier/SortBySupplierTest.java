package seedu.address.model.person.supplier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_ADDRESS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_EMAIL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_BY_PHONE;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_SORT_BY_DELIVERY_DETAILS;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_SORT_BY_SUPPLY_TYPE;

import org.junit.jupiter.api.Test;

public class SortBySupplierTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortBySupplier(null));
    }

    @Test
    public void constructor_invalidSortBy_throwsIllegalArgumentException() {
        String invalidSortBy = "";
        assertThrows(IllegalArgumentException.class, () -> new SortBySupplier(invalidSortBy));
    }

    @Test
    public void isValidSortBy() {
        // null phone number
        assertThrows(NullPointerException.class, () -> SortBySupplier.isValidSortBy(null));

        // invalid sorting orders
        assertFalse(SortBySupplier.isValidSortBy("")); // empty string
        assertFalse(SortBySupplier.isValidSortBy(" ")); // spaces only
        assertFalse(SortBySupplier.isValidSortBy("123")); // not "n", "a", "e", "p", "st" or "dd
        assertFalse(SortBySupplier.isValidSortBy("hello")); // not "n", "a", "e", "p", "st" or "dd

        // valid sorting orders
        assertTrue(SortBySupplier.isValidSortBy("n")); // Sort by name
        assertTrue(SortBySupplier.isValidSortBy("a")); // Sort by address
        assertTrue(SortBySupplier.isValidSortBy("e")); // Sort by email
        assertTrue(SortBySupplier.isValidSortBy("p")); // Sort by phone number
        assertTrue(SortBySupplier.isValidSortBy("st")); // Sort by supply type
        assertTrue(SortBySupplier.isValidSortBy("dd")); // Sort by delivery details
    }

    @Test
    public void toStringTest() {
        SortBySupplier sortBy = new SortBySupplier(VALID_SORT_BY_ADDRESS);
        assertTrue(sortBy.toString().equals("address"));

        sortBy = new SortBySupplier(VALID_SORT_BY_EMAIL);
        assertTrue(sortBy.toString().equals("email"));

        sortBy = new SortBySupplier(VALID_SORT_BY_NAME);
        assertTrue(sortBy.toString().equals("name"));

        sortBy = new SortBySupplier(VALID_SORT_BY_SUPPLY_TYPE);
        assertTrue(sortBy.toString().equals("supply type"));

        sortBy = new SortBySupplier(VALID_SORT_BY_DELIVERY_DETAILS);
        assertTrue(sortBy.toString().equals("delivery details"));

        sortBy = new SortBySupplier(VALID_SORT_BY_PHONE);
        assertTrue(sortBy.toString().equals("phone"));

        String invalidSortBy = "1234abc";
        assertThrows(IllegalArgumentException.class, () -> new SortBySupplier(invalidSortBy).toString());
    }

    @Test
    public void equals() {
        SortBySupplier sortBy = new SortBySupplier(VALID_SORT_BY_DELIVERY_DETAILS);

        // same values -> returns true
        SortBySupplier toCopy = new SortBySupplier(VALID_SORT_BY_DELIVERY_DETAILS);
        assertTrue(sortBy.equals(toCopy));

        // same object -> returns true
        assertTrue(sortBy.equals(sortBy));

        // null -> returns false
        assertFalse(sortBy.equals(null));

        // different type -> returns false
        assertFalse(sortBy.equals(5));

        // different SortBy -> returns false
        SortBySupplier different = new SortBySupplier(VALID_SORT_BY_SUPPLY_TYPE);
        assertFalse(sortBy.equals(different));

        different = new SortBySupplier(VALID_SORT_BY_ADDRESS);
        assertFalse(sortBy.equals(different));

        different = new SortBySupplier(VALID_SORT_BY_NAME);
        assertFalse(sortBy.equals(different));

        different = new SortBySupplier(VALID_SORT_BY_EMAIL);
        assertFalse(sortBy.equals(different));

        different = new SortBySupplier(VALID_SORT_BY_PHONE);
        assertFalse(sortBy.equals(different));
    }
}
