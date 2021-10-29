package seedu.siasa.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.logic.commands.CommandTestUtil.INVALID_POLICY_PAYMENT_AMOUNT_NEGATIVE;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_NUMBER_OF_PAYMENTS_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_PAYMENT_AMOUNT_CRITICAL;
import static seedu.siasa.logic.commands.CommandTestUtil.VALID_POLICY_PAYMENT_FREQUENCY_CRITICAL;
import static seedu.siasa.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class PaymentStructureTest {

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new PaymentStructure(INVALID_POLICY_PAYMENT_AMOUNT_NEGATIVE,
                VALID_POLICY_PAYMENT_FREQUENCY_CRITICAL,
                VALID_POLICY_NUMBER_OF_PAYMENTS_CRITICAL));
    }

    @Test
    public void isValidPaymentStructureTest() {
        // invalid payment amount
        assertFalse(PaymentStructure.isValidPaymentStructure(INVALID_POLICY_PAYMENT_AMOUNT_NEGATIVE,
                VALID_POLICY_PAYMENT_FREQUENCY_CRITICAL,
                VALID_POLICY_NUMBER_OF_PAYMENTS_CRITICAL));

        // valid payment structure
        assertTrue(PaymentStructure.isValidPaymentStructure(VALID_POLICY_PAYMENT_AMOUNT_CRITICAL,
                VALID_POLICY_PAYMENT_FREQUENCY_CRITICAL,
                VALID_POLICY_NUMBER_OF_PAYMENTS_CRITICAL));
    }
}
