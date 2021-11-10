package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Price(null));
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        String invalidPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null price
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // invalid price
        assertFalse(Price.isValidPrice("")); // empty string
        assertFalse(Price.isValidPrice(" ")); // spaces only
        assertFalse(Price.isValidPrice("price")); // contains non-numerical characters
        assertFalse(Price.isValidPrice(".11")); // invalid numerical input
        assertFalse(Price.isValidPrice("0.")); // incorrect price formatting
        assertFalse(Price.isValidPrice("123.334")); // too many decimal places

        // valid price
        assertTrue(Price.isValidPrice("500.12")); // numbers to two decimal places only
        assertTrue(Price.isValidPrice("14.03"));
    }
}

