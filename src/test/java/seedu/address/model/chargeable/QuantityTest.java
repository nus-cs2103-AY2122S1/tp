package seedu.address.model.chargeable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuantityTest {
    @Test
    public void constructor_ok_throwsNoException() {
        assertDoesNotThrow(() -> new Quantity(2));
    }

    @Test
    public void constructor_allNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    public void invalidInput_negative_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(-1));
    }

    @Test
    public void invalidInput_decimal_throwsNoException() {
        assertDoesNotThrow(() -> new Quantity(Integer.MAX_VALUE));
    }

    @Test
    public void validInput_zero_throwsNoException() {
        assertDoesNotThrow(() -> new Quantity(0));
    }
}
