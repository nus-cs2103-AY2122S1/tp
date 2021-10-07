package seedu.siasa.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.logic.commands.CommandTestUtil.INVALID_POLICY_COMMISSION_OVER;
import static seedu.siasa.logic.commands.CommandTestUtil.INVALID_POLICY_COMMISSION_UNDER;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_COMMISSION_CRITICAL;
import static seedu.siasa.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class CommissionTest {
    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Commission(INVALID_POLICY_COMMISSION_OVER));
    }

    @Test
    public void isValidCommission() {
        // invalid commission
        assertFalse(Commission.isValidCommission(INVALID_POLICY_COMMISSION_OVER)); // negative commission
        assertFalse(Commission.isValidCommission(INVALID_POLICY_COMMISSION_UNDER)); // negative commission

        // valid commission
        assertTrue(Commission.isValidCommission(VALID_POLICY_COMMISSION_CRITICAL));
    }
}
