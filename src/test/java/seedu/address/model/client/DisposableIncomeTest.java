package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DisposableIncomeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DisposableIncome(null));
    }

    @Test
    public void constructor_invalidDisposableIncome_throwsIllegalArgumentException() {
        String invalidDisposableIncome = "-9";
        assertThrows(IllegalArgumentException.class, () -> new DisposableIncome(invalidDisposableIncome));
    }

    @Test
    public void isValidDisposableIncome() {
        // null disposable income
        assertThrows(NullPointerException.class, () -> RiskAppetite.isValidRiskAppetite(null));

        // invalid disposable incomes
        assertFalse(DisposableIncome.isValidDisposableIncome(" ")); // spaces only
        assertFalse(DisposableIncome.isValidDisposableIncome("-91")); // negative integer
        assertFalse(DisposableIncome.isValidDisposableIncome("-91.32")); // negative integer with decimal
        assertFalse(DisposableIncome.isValidDisposableIncome("risk")); // non-numeric
        assertFalse(DisposableIncome.isValidDisposableIncome("9011p041")); // alphabets within digits
        assertFalse(DisposableIncome.isValidDisposableIncome("9312 1534")); // spaces within digits
        assertFalse(DisposableIncome.isValidDisposableIncome("1234567890123456")); // >15 char

        // valid disposable incomes
        assertTrue(DisposableIncome.isValidDisposableIncome("0")); // zero
        assertTrue(DisposableIncome.isValidDisposableIncome("0.00")); // zero
        assertTrue(DisposableIncome.isValidDisposableIncome("2432"));
        assertTrue(DisposableIncome.isValidDisposableIncome("369.69")); // with decimal
        assertTrue(DisposableIncome.isValidDisposableIncome("50,0000.963")); // with comma
    }

    @Test
    public void equals() {
        DisposableIncome john = new DisposableIncome("5000.00");
        DisposableIncome otherJohn = new DisposableIncome("5000.00");
        DisposableIncome jane = new DisposableIncome("10000.00");

        // same object
        assertTrue(john.equals(john));

        // different object same DisposableIncome
        assertTrue(john.equals(otherJohn));
        assertEquals(john.hashCode(), otherJohn.hashCode());

        // different object different DisposableIncome
        assertFalse(john.equals(jane));

        // different type
        assertFalse(john.equals("5000.00"));

    }
}
