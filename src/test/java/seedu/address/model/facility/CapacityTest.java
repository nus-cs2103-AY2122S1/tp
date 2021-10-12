package seedu.address.model.facility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CapacityTest {
    @Test
    public void constructor_null_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new Capacity(null));
    }

    @Test
    public void isWithinCapacity() {
        Capacity capacity = new Capacity("5");
        assertTrue(capacity.isWithinCapacity(5));
        assertTrue(capacity.isWithinCapacity(4));
        assertFalse(capacity.isWithinCapacity(6));
    }

    @Test
    public void equals() {
        Capacity capacity = new Capacity("5");
        Capacity capacityCopy = new Capacity("5");

        assertTrue(capacity.equals(capacityCopy));
        assertTrue(capacity.equals(capacity));

        assertFalse(capacity.equals(null));
        Capacity differentCapacity = new Capacity("10");
        assertFalse(capacity.equals(differentCapacity));

    }
}
