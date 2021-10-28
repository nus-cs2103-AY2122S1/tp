package seedu.siasa.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.logic.commands.CommandTestUtil.INVALID_POLICY_EXPIRY_DATE_PAST;
import static seedu.siasa.logic.commands.CommandTestUtil.INVALID_POLICY_EXPIRY_DATE_TODAY;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_CRITICAL;
import static seedu.siasa.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class CoverageExpiryDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CoverageExpiryDate(null));
    }

    @Test
    public void constructor_invalidExpiryDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CoverageExpiryDate(INVALID_POLICY_EXPIRY_DATE_PAST));
        assertThrows(IllegalArgumentException.class, () -> new CoverageExpiryDate(INVALID_POLICY_EXPIRY_DATE_TODAY));
    }

    @Test
    public void isValidExpiryDate() {
        // null address
        assertThrows(NullPointerException.class, () -> CoverageExpiryDate.isValidExpiryDate(null));

        // invalid addresses
        assertFalse(CoverageExpiryDate.isValidExpiryDate(INVALID_POLICY_EXPIRY_DATE_PAST));
        assertFalse(CoverageExpiryDate.isValidExpiryDate(INVALID_POLICY_EXPIRY_DATE_TODAY));

        // valid addresses
        assertTrue(CoverageExpiryDate.isValidExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL));
    }
}
