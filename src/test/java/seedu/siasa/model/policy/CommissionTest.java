package seedu.siasa.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.logic.commands.CommandTestUtil.INVALID_POLICY_COMMISSION_AMOUNT_OVER;
import static seedu.siasa.logic.commands.CommandTestUtil.INVALID_POLICY_COMMISSION_AMOUNT_UNDER;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_COMMISSION_NUM_OF_PAYMENTS_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_COMMISSION_PERCENTAGE_CRITICAL;
import static seedu.siasa.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class CommissionTest {
    @Test
    public void constructor_invalidCommissionAmount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Commission(INVALID_POLICY_COMMISSION_AMOUNT_OVER,
                VALID_POLICY_COMMISSION_NUM_OF_PAYMENTS_CRITICAL));
    }

    @Test
    public void isValidCommission() {
        // invalid commission
        assertFalse(Commission.isValidCommission(INVALID_POLICY_COMMISSION_AMOUNT_OVER,
                VALID_POLICY_COMMISSION_NUM_OF_PAYMENTS_CRITICAL)); // negative commission
        assertFalse(Commission.isValidCommission(INVALID_POLICY_COMMISSION_AMOUNT_UNDER,
                VALID_POLICY_COMMISSION_NUM_OF_PAYMENTS_CRITICAL)); // negative commission

        // valid commission
        assertTrue(Commission.isValidCommission(VALID_POLICY_COMMISSION_PERCENTAGE_CRITICAL,
                VALID_POLICY_COMMISSION_NUM_OF_PAYMENTS_CRITICAL));
    }
}
