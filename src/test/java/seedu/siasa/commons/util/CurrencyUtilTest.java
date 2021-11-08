package seedu.siasa.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.siasa.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CurrencyUtilTest {
    @Test
    public void centsToDollars_positiveInput_success() {
        assertEquals(CurrencyUtil.centsToDollars(1040), "$10.40");
    }

    @Test
    public void centsToDollars_zeroInput_success() {
        assertEquals(CurrencyUtil.centsToDollars(0), "$0.00");
    }

    @Test
    public void centsToDollars_largePositiveInput_success() {
        assertEquals(CurrencyUtil.centsToDollars(89028401), "$890284.01");
    }

    @Test
    public void centsToDollars_negativeInput_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> CurrencyUtil.centsToDollars(-1002));
    }
}
