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
    public void isMaxCapacity() {
        Capacity capacity = new Capacity("5");
        // below max capacity
        assertFalse(capacity.isMaxCapacity(4));
        assertFalse(capacity.isMaxCapacity(0));

        // at max capacity
        assertTrue(capacity.isMaxCapacity(5));
        assertTrue(capacity.isMaxCapacity(6));
        assertTrue(capacity.isMaxCapacity(50));
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
