package seedu.siasa.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.logic.commands.CommandTestUtil.INVALID_POLICY_EXPIRY_DATE_PAST;
import static seedu.siasa.logic.commands.CommandTestUtil.INVALID_POLICY_EXPIRY_DATE_TODAY;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_CRITICAL;
import static seedu.siasa.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ExpiryDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpiryDate(null));
    }

    /*
    @Test
    public void constructor_invalidExpiryDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate(INVALID_POLICY_EXPIRY_DATE_PAST));
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate(INVALID_POLICY_EXPIRY_DATE_TODAY));
    }
    */

    @Test
    public void isPastExpiryDate() {
        // valid addresses
        ExpiryDate validExpiryDate = new ExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL);
        assertTrue(validExpiryDate.isFutureExpiryDate());

        // invalid address
        ExpiryDate invalidExpiryDatePast = new ExpiryDate(INVALID_POLICY_EXPIRY_DATE_PAST);
        ExpiryDate invalidExpiryDateNow = new ExpiryDate(INVALID_POLICY_EXPIRY_DATE_TODAY);
        assertFalse(invalidExpiryDatePast.isFutureExpiryDate());
        assertFalse(invalidExpiryDateNow.isFutureExpiryDate());
    }
}
