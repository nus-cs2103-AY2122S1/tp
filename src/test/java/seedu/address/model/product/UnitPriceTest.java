package seedu.address.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UnitPriceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnitPrice(null));
    }

    @Test
    public void constructor_invalidUnitPrice_throwsIllegalArgumentException() {
        String invalidUnitPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new UnitPrice(invalidUnitPrice));
    }

    @Test
    public void isValidUnitPrice() {
        // null unit price
        assertThrows(NullPointerException.class, () -> UnitPrice.isValidUnitPrice(null));

        // invalid unit price
        assertFalse(UnitPrice.isValidUnitPrice("")); // empty string
        assertFalse(UnitPrice.isValidUnitPrice(" ")); // spaces only
        assertFalse(UnitPrice.isValidUnitPrice("phone")); // non-numeric
        assertFalse(UnitPrice.isValidUnitPrice("9011p041")); // alphabets within digits
        assertFalse(UnitPrice.isValidUnitPrice("9312 1534")); // spaces within digits
        assertFalse(UnitPrice.isValidUnitPrice("1.2")); // with 1 decimal place
        assertFalse(UnitPrice.isValidUnitPrice("1.255")); // with 3 decimal places
        assertFalse(UnitPrice.isValidUnitPrice("-100")); // negative number

        // valid unit price
        assertTrue(UnitPrice.isValidUnitPrice("91"));
        assertTrue(UnitPrice.isValidUnitPrice("911"));
        assertTrue(UnitPrice.isValidUnitPrice("93121534"));
        assertTrue(UnitPrice.isValidUnitPrice("124293842033123"));
        assertTrue(UnitPrice.isValidUnitPrice("1.20")); // with 2 decimal places
    }
}
