package seedu.siasa.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.logic.commands.CommandTestUtil.POLICY_EXPIRY_DATE_PAST;
import static seedu.siasa.logic.commands.CommandTestUtil.POLICY_EXPIRY_DATE_TODAY;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_CRITICAL;
import static seedu.siasa.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class CoverageExpiryDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CoverageExpiryDate(null));
    }


    @Test
    public void isValidExpiryDate() {
        // null address
        assertThrows(NullPointerException.class, () -> CoverageExpiryDate.isFutureExpiryDate(null));

        // past and present addresses
        assertFalse(CoverageExpiryDate.isFutureExpiryDate(POLICY_EXPIRY_DATE_PAST));
        assertFalse(CoverageExpiryDate.isFutureExpiryDate(POLICY_EXPIRY_DATE_TODAY));

        // future addresses
        assertTrue(CoverageExpiryDate.isFutureExpiryDate(VALID_POLICY_EXPIRY_DATE_CRITICAL));
    }
}
