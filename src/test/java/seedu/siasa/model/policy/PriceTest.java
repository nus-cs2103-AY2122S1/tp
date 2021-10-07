package seedu.siasa.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.logic.commands.CommandTestUtil.INVALID_PRICE_NEGATIVE;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_PRICE_CRITICAL;
import static seedu.siasa.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PriceTest {

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Price(INVALID_PRICE_NEGATIVE));
    }

    @Test
    public void isValidPrice() {
        // invalid price
        assertFalse(Price.isValidPrice(INVALID_PRICE_NEGATIVE)); // empty string

        // valid prices
        assertTrue(Price.isValidPrice(VALID_POLICY_PRICE_CRITICAL));
    }
}
