package seedu.siasa.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.logic.commands.CommandTestUtil.PAST_POLICY_EXPIRY_DATE_FIXED;
import static seedu.siasa.logic.commands.CommandTestUtil.PRESENT_POLICY_EXPIRY_DATE;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_CRITICAL;
import static seedu.siasa.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ExpiryDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpiryDate(null));
    }

    @Test
    public void isPastExpiryDate() {
        // valid addresses
        ExpiryDate validExpiryDate = new ExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL);
        assertTrue(validExpiryDate.isFutureExpiryDate());

        // invalid address
        ExpiryDate invalidExpiryDatePast = new ExpiryDate(PAST_POLICY_EXPIRY_DATE_FIXED);
        ExpiryDate invalidExpiryDateNow = new ExpiryDate(PRESENT_POLICY_EXPIRY_DATE);
        assertFalse(invalidExpiryDatePast.isFutureExpiryDate());
        assertFalse(invalidExpiryDateNow.isFutureExpiryDate());
    }
}
