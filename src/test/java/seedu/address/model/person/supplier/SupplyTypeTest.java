package seedu.address.model.person.supplier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_SUPPLY_TYPE_BEEF;
import static seedu.address.logic.commands.SupplierCommandTestUtil.VALID_SUPPLY_TYPE_CHICKEN;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SupplyTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SupplyType(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidSupplyType = "";
        assertThrows(IllegalArgumentException.class, () -> new SupplyType(invalidSupplyType));
    }

    @Test
    public void isValidSupplyType() {
        // null name
        assertThrows(NullPointerException.class, () -> SupplyType.isValidSupplyType(null));

        // invalid supply type
        assertFalse(SupplyType.isValidSupplyType("")); // empty string
        assertFalse(SupplyType.isValidSupplyType(" ")); // spaces only
        assertFalse(SupplyType.isValidSupplyType("^")); // only non-alphanumeric characters
        assertFalse(SupplyType.isValidSupplyType("chicken&beef")); // contains non-alphanumeric characters

        // valid name
        assertTrue(SupplyType.isValidSupplyType("chicken and beef")); // alphabets only
        assertTrue(SupplyType.isValidSupplyType("12345")); // numbers only
        assertTrue(SupplyType.isValidSupplyType("beef 2nd supplier")); // alphanumeric characters
        assertTrue(SupplyType.isValidSupplyType("Beef and Chicken")); // with capital letters
        assertTrue(SupplyType.isValidSupplyType("Beef and Chicken and Fish the 2nd")); // long names
    }

    @Test
    public void equals() {
        SupplyType supplyType = new SupplyType(VALID_SUPPLY_TYPE_BEEF);

        // same values -> returns true
        SupplyType toCopy = new SupplyType(VALID_SUPPLY_TYPE_BEEF);
        assertTrue(supplyType.equals(toCopy));

        // same object -> returns true
        assertTrue(supplyType.equals(supplyType));

        // null -> returns false
        assertFalse(supplyType.equals(null));

        // different type -> returns false
        assertFalse(supplyType.equals(5));

        // different SupplyType -> returns false
        SupplyType different = new SupplyType(VALID_SUPPLY_TYPE_CHICKEN);
        assertFalse(supplyType.equals(different));

    }
}
