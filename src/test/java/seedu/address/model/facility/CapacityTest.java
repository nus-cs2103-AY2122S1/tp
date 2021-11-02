package seedu.address.model.facility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CapacityTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Capacity(null));
    }

    @Test
    public void constructor_invalidCapacity_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Capacity(""));
        // beyond upper boundary
        assertThrows(IllegalArgumentException.class, () -> new Capacity("51"));
        assertThrows(IllegalArgumentException.class, () -> new Capacity("500"));
        // beyond lower boundary
        assertThrows(IllegalArgumentException.class, () -> new Capacity("0"));
        assertThrows(IllegalArgumentException.class, () -> new Capacity("-1"));
    }

    @Test
    public void isValidCapacity() {
        // null capacity
        assertThrows(NullPointerException.class, () -> Capacity.isValidCapacity(null));

        // valid capacities
        assertTrue(Capacity.isValidCapacity("1")); // lower boundary
        assertTrue(Capacity.isValidCapacity("50")); // upper boundary
        assertTrue(Capacity.isValidCapacity("25"));

        // invalid capacities
        assertFalse(Capacity.isValidCapacity("")); // empty string
        assertFalse(Capacity.isValidCapacity(" ")); // spaces only
        assertFalse(Capacity.isValidCapacity("51")); // more than 50
        assertFalse(Capacity.isValidCapacity("0")); // less than 1
    }

    @Test
    public void isWithinCapacity() {
        Capacity capacity = new Capacity("5");
        // within max capacity
        assertTrue(capacity.isWithinCapacity(5));
        assertTrue(capacity.isWithinCapacity(4));
        assertTrue(capacity.isWithinCapacity(0));

        // beyond max capacity
        assertFalse(capacity.isWithinCapacity(6));
        assertFalse(capacity.isWithinCapacity(50));
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
