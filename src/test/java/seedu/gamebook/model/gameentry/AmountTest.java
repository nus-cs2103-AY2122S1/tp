package seedu.gamebook.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.gamebook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {
    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        // 3 digits after decimal point
        assertThrows(IllegalArgumentException.class, () -> new Amount("12345.123"));

        // Other characters before string
        assertThrows(IllegalArgumentException.class, () -> new Amount("e20.34"));
        assertThrows(IllegalArgumentException.class, () -> new Amount("e2034"));

        // Other characters after string
        assertThrows(IllegalArgumentException.class, () -> new Amount("20.34e"));
        assertThrows(IllegalArgumentException.class, () -> new Amount("2034e"));

        // Other characters within string
        assertThrows(IllegalArgumentException.class, () -> new Amount("20.e34"));
        assertThrows(IllegalArgumentException.class, () -> new Amount("20e34"));

        // Only decimal
        assertThrows(IllegalArgumentException.class, () -> new Amount("."));
    }

    @Test
    public void constructor_validAmount() {
        Amount amount;
        amount = new Amount("12345");
        assertEquals(12345, amount.getAmount());

        // With white space
        amount = new Amount("      12345");
        assertEquals(12345, amount.getAmount());
        amount = new Amount("12345       ");
        assertEquals(12345, amount.getAmount());
        amount = new Amount("   12345       ");
        assertEquals(12345, amount.getAmount());

        // Negative values
        amount = new Amount("-12345");
        assertEquals(-12345, amount.getAmount());
        amount = new Amount("-12345.67");
        assertEquals(-12345.67, amount.getAmount());

        // Decimal points
        amount = new Amount("12345.67");
        assertEquals(12345.67, amount.getAmount());
        amount = new Amount("12345.6");
        assertEquals(12345.6, amount.getAmount());

        // No integer before decimal point
        amount = new Amount(".12");
        assertEquals(0.12, amount.getAmount());

        // Minus sign and no integer before decimal point
        amount = new Amount("-.12");
        assertEquals(-0.12, amount.getAmount());

        // Integer with trailing decimal point
        amount = new Amount("12.");
        assertEquals(12, amount.getAmount());

        // Leading zeros
        amount = new Amount("0012");
        assertEquals(12, amount.getAmount());
    }

    @Test
    public void difference_test() {
        Amount amount1 = new Amount("100");
        Amount amount2 = new Amount("100");
        assertEquals(0, amount1.minus(amount2).getAmount());

        amount2 = new Amount("12.34");
        assertEquals(
                amount1.minus(amount2).getAmount(),
                -amount2.minus(amount1).getAmount()
        );
    }

    @Test
    public void addCurrencySymbol_test() {
        Amount integerAmount = new Amount("1234");
        Amount floatAmount = new Amount("1234.56");
        assertEquals("+ $1234.00", integerAmount.addCurrencySymbol("$"));
        assertEquals("+ $1234.56", floatAmount.addCurrencySymbol("$"));
    }

    @Test
    public void equal_test() {
        // Same object
        Amount amount1 = new Amount("1234");
        assertTrue(amount1.equals(amount1));

        // Same amount
        Amount amount2 = new Amount("1234");
        assertTrue(amount1.equals(amount2));

        // Check reflexitivity
        assertTrue(amount2.equals(amount1));

        amount2 = new Amount("1234.00");
        assertTrue(amount1.equals(amount2));

        amount2 = new Amount("1234.01");
        assertFalse(amount1.equals(amount2));
    }


}
