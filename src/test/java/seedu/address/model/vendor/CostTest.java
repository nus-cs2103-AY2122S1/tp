package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CostTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, (
        ) -> new Cost(null));
    }

    @Test
    public void constructor_invalidCost_throwsIllegalArgumentException() {
        Double invalidCost = -10.00;
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidCost));
    }

    @Test
    public void isValidCost() {
        // null cost
        assertThrows(NullPointerException.class, () -> Cost.isValidCost(null));

        // invalid cost
        assertFalse(Cost.isValidCost(-10.00)); // less than 0
        assertFalse(Cost.isValidCost(0.00)); // 0

        // valid cost
        assertTrue(Cost.isValidCost(10.00)); // greater than 0
    }

}
