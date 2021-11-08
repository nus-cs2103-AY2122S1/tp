package seedu.address.model.transaction;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the functionalities of
 * {@code Transaction}.
 */
public class TransactionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Billing(null));
    }

    @Test
    public void constructor_invalidTransactionName_throwsIllegalArgumentException() {
        String invalidTransactionAmount = "";
        assertThrows(IllegalArgumentException.class, () -> new Billing(invalidTransactionAmount));
    }

    @Test
    public void isValidTransactionName() {
        // null transaction amount
        assertThrows(NullPointerException.class, () -> Billing.isValidBilling(null));
    }

}
